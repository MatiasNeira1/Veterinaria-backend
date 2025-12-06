package duoc.cl.cachupin.admin.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long idProducto;
    private String categoria;
    private String marca;
    private String nombre;
    private Integer precio;
    private Integer stock;

}
