package duoc.cl.cachupin.admin.controller;

import duoc.cl.cachupin.admin.model.ModelProduct;
import duoc.cl.cachupin.admin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(
        name = "Productos",
        description = "Endpoints para la gestión de productos de la tienda"
)
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Operation(
            summary = "Listar todos los productos",
            description = "Obtiene el listado completo de productos registrados en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de productos obtenido correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<List<ModelProduct>> getAll() {
        List<ModelProduct> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

}
