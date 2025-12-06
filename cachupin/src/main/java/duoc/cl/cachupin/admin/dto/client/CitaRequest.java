package duoc.cl.cachupin.admin.dto.client;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitaRequest {
    private String nombre;
    private String telefono;
    private String mascota;
    private String servicio;
    private String fecha;
    private String hora;
    private String comentarios;

}
