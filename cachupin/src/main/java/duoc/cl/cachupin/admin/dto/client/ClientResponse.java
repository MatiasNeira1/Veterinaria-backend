package duoc.cl.cachupin.admin.dto.client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class ClientResponse {
    private Long idusuario;
    private String nombreUsuario;
    private String correo;
    private Long rolId;
}
