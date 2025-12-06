package duoc.cl.cachupin.admin.controller;

import com.google.firebase.auth.FirebaseToken;
import duoc.cl.cachupin.admin.dto.client.CitaRequest;
import duoc.cl.cachupin.admin.model.ModelCita;
import duoc.cl.cachupin.admin.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/citas")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@Tag(
        name = "Citas",
        description = "Endpoints para gestionar las citas de los clientes en la veterinaria"
)
public class CitaController {

    private final CitaService citaService;

    @Operation(
            summary = "Agendar una nueva cita",
            description = "Permite a un usuario autenticado agendar una cita asociada a su cuenta de Firebase."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cita agendada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de la cita inválidos"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })
    @PostMapping("/agendar")
    public ResponseEntity<ModelCita> agendar(
            Authentication authentication,
            @RequestBody CitaRequest request
    ) {

        FirebaseToken token = (FirebaseToken) authentication.getDetails();
        String emailUsuario = token.getEmail();
        String uidUsuario = token.getUid();

        System.out.println("Agendando cita para usuario: " + emailUsuario);

        ModelCita cita = citaService.agendarCita(request, emailUsuario, uidUsuario);
        return ResponseEntity.ok(cita);
    }

    @Operation(
            summary = "Listar mis citas",
            description = "Obtiene todas las citas asociadas al usuario autenticado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de citas del usuario obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado")
    })
    @GetMapping("/mis")
    public ResponseEntity<List<ModelCita>> listarMisCitas(Authentication authentication) {
        FirebaseToken token = (FirebaseToken) authentication.getDetails();
        String emailUsuario = token.getEmail();

        List<ModelCita> citas = citaService.listarCitasPorCorreo(emailUsuario);
        return ResponseEntity.ok(citas);
    }

    @Operation(
            summary = "Listar todas las citas",
            description = "Obtiene todas las citas registradas en el sistema. Usualmente para perfil administrador."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de todas las citas obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado o sin permisos")
    })
    @GetMapping("/all")
    public ResponseEntity<List<ModelCita>> listarTodas() {
        List<ModelCita> citas = citaService.listarTodas();
        return ResponseEntity.ok(citas);
    }

}
