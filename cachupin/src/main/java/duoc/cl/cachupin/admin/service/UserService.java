package duoc.cl.cachupin.admin.service;
import duoc.cl.cachupin.admin.model.ModelUser;
import duoc.cl.cachupin.admin.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<ModelUser> listarTodos() {
        return userRepository.findAll();
    }

    public ModelUser crear(ModelUser u) {
        u.setIdusuario(null);

        return userRepository.save(u);
    }

    public ModelUser actualizar(Long id, ModelUser data) {
        ModelUser existente = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id " + id));

        existente.setNombreUsuario(data.getNombreUsuario());
        existente.setTelefono(data.getTelefono());
        existente.setCorreo(data.getCorreo());
        existente.setRolId(data.getRolId());
        // normalmente la contraseña no se toca desde el panel admin (a menos que hagas algo especial)

        return userRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado con id " + id);
        }
        userRepository.deleteById(id);
    }
}
