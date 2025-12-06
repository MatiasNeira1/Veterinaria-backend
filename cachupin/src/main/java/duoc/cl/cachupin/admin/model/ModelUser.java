package duoc.cl.cachupin.admin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "USUARIO")
public class ModelUser {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUSUARIO")
    private Long idusuario;

    @Column(name = "NOMBREUSUARIO", length = 50, nullable = false)
    private String nombreUsuario;

    @Column(name="TELEFONO", length = 15)
    private String telefono;

    @Column(name = "CORREO", length = 100)
    private String correo;

    @Column(name = "CONTRASENA", nullable = true)
    private String contrasena;

    @Column(name = "ROL_ID", nullable = false)
    private Long rolId;

    @Column(name = "FECHAREGISTRO", updatable = false, insertable = false)
    private LocalDateTime fechaRegistro;
}
