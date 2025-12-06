package duoc.cl.cachupin.admin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CITA")
@Getter
@Setter
public class ModelCita {

    @Id
    @Column(name = "IDCITA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CITA_SEQ")
    @SequenceGenerator(name = "CITA_SEQ", sequenceName = "SEQ_CITA", allocationSize = 1)
    private Long idCita;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "TELEFONO", nullable = false, length = 20)
    private String telefono;

    @Column(name = "MASCOTA", nullable = false, length = 100)
    private String mascota;

    @Column(name = "SERVICIO", nullable = false, length = 50)
    private String servicio;


    @Column(name = "FECHA_CITA", nullable = false, length = 10)
    private String fecha;


    @Column(name = "HORA_CITA", nullable = false, length = 5)
    private String hora;

    @Column(name = "COMENTARIOS", length = 500)
    private String comentarios;


    @Column(name = "CORREO_USUARIO", length = 150)
    private String correoUsuario;

    @Column(name = "UID_USUARIO", length = 100)
    private String uidUsuario;

}
