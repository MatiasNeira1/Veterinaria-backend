package duoc.cl.cachupin.admin.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import duoc.cl.cachupin.admin.dto.client.ClientResponse;
import duoc.cl.cachupin.admin.dto.client.LoginRequest;
import duoc.cl.cachupin.admin.dto.client.RegisterRequest;
import duoc.cl.cachupin.admin.model.ModelUser;
import duoc.cl.cachupin.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final FirebaseAuth firebaseAuth;





    public ClientResponse RegisterUser(RegisterRequest registerRequest) {

        if (userRepository.findByCorreo(registerRequest.getCorreo()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        String password = registerRequest.getPassword();
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
        }


        try {
            UserRecord.CreateRequest fbRequest = new UserRecord.CreateRequest()
                    .setEmail(registerRequest.getCorreo())
                    .setPassword(registerRequest.getPassword())   // aqui se consigue la contraseña desde el request
                    .setDisplayName(registerRequest.getNombre());

            UserRecord firebaseUser = firebaseAuth.createUser(fbRequest);
            System.out.println("Usuario creado en Firebase con uid: " + firebaseUser.getUid());

        } catch (FirebaseAuthException e) {
            // Si falla firebase no sigue
            throw new IllegalArgumentException("No se pudo registrar el usuario en Firebase: " + e.getMessage());
        }

        ModelUser user = new ModelUser();
        user.setNombreUsuario(registerRequest.getNombre());
        user.setCorreo(registerRequest.getCorreo());
        user.setTelefono(registerRequest.getTelefono());
        user.setContrasena(null);
        user.setRolId(2L);
        user.setFechaRegistro( LocalDateTime.now());

        ModelUser UserGuardado = userRepository.save(user);

        return new ClientResponse(
                UserGuardado.getIdusuario(),
                UserGuardado.getNombreUsuario(),
                UserGuardado.getCorreo(),
                UserGuardado.getRolId()
        );


    }

    public ClientResponse loginWithFirebase(FirebaseToken token) {


        String email = token.getEmail();
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El token de Firebase no contiene un correo válido.");
        }


        ModelUser user = userRepository.findByCorreo(email)
                .orElseGet(() -> {

                    ModelUser newuser = new ModelUser();

                    String nombre = token.getName();
                    if (nombre == null || nombre.isBlank()) {
                        nombre = email;
                    }

                    newuser.setNombreUsuario(nombre);
                    newuser.setCorreo(email);

                    newuser.setContrasena("");
                    newuser.setRolId(4L);  // por ejemplo rol de cliente
                    newuser.setFechaRegistro(LocalDateTime.now());

                    return userRepository.save(newuser);
                });


        return new ClientResponse(
                user.getIdusuario(),
                user.getNombreUsuario(),
                user.getCorreo(),
                user.getRolId()
        );
}
}

