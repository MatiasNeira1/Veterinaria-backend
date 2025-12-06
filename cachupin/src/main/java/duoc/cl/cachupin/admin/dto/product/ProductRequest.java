package duoc.cl.cachupin.admin.dto.product;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
@Getter
@Setter
public class ProductRequest {
    @NotNull
    private Long idproducto;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotBlank
    @Size(max = 50)
    private String categoria;

    @NotNull
    @Positive              // precio > 0
    private Integer precio;

    @NotNull
    @PositiveOrZero        // stock >= 0
    private Integer stock;

    @NotBlank
    @Size(max = 50)
    private String marca;
}
