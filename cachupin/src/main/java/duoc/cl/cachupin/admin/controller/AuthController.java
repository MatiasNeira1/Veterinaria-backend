package duoc.cl.cachupin.admin.controller;

import com.google.firebase.auth.FirebaseToken;
import duoc.cl.cachupin.admin.dto.client.ClientResponse;
import duoc.cl.cachupin.admin.dto.client.LoginRequest;
import duoc.cl.cachupin.admin.dto.client.RegisterRequest;
import duoc.cl.cachupin.admin.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(
        name = "Autenticación",
        description = "Endpoints para registro e inicio de sesión de usuarios"
)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "Registrar un nuevo usuario",
            description = "Crea un nuevo usuario cliente en el sistema a partir de los datos enviados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario registrado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o correo ya registrado")
    })
    @PostMapping("/register")
    public ResponseEntity<ClientResponse> register(@Valid @RequestBody RegisterRequest request) {
        ClientResponse user = authService.RegisterUser(request);
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Iniciar sesión con Firebase",
            description = "Valida el token de Firebase recibido en el contexto de autenticación y devuelve los datos del usuario."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso"),
            @ApiResponse(responseCode = "401", description = "Token inválido o no enviado")
    })
    @PostMapping("/login")
    public ResponseEntity<ClientResponse> Login(Authentication authentication) {
        if (authentication == null || authentication.getDetails() == null) {
            // aca no llega el token y no se valida
            return ResponseEntity.status(401).build();
        }
        FirebaseToken firebaseToken = (FirebaseToken) authentication.getDetails();
        ClientResponse user = authService.loginWithFirebase(firebaseToken);
        return ResponseEntity.ok(user);
    }

}
