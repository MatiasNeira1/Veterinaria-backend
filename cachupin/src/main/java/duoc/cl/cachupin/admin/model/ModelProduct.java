package duoc.cl.cachupin.admin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="PRODUCTO")
@Getter
@Setter
public class ModelProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IDPRODUCTO")
    private Long idproducto;

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name="CATEGORIA")
    private String categoria;

    @Column(name="PRECIO")
    private Integer precio;

    @Column(name="STOCK")
    private Integer stock;

    @Column(name = "MARCA")
    private String marca;
}
