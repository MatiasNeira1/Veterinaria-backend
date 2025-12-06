/*package duoc.cl.cachupin.admin.controller;

import duoc.cl.cachupin.admin.model.ModelCita;
import duoc.cl.cachupin.admin.model.ModelProduct;
import duoc.cl.cachupin.admin.model.ModelUser;
import duoc.cl.cachupin.admin.service.CitaService;
import duoc.cl.cachupin.admin.service.ProductService;
import duoc.cl.cachupin.admin.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;
    private final UserService userService;
    private final CitaService citaService;

    // ====== PRODUCTOS ======

    @GetMapping("/products")
    public List<ModelProduct> getAllProducts() {
        return productService.listarTodos();
    }

    @PostMapping("/products")
    public ModelProduct createProduct(@RequestBody ModelProduct p) {
        return productService.crear(p);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ModelProduct> updateProduct(
            @PathVariable Long id,
            @RequestBody ModelProduct body) {
        try {
            ModelProduct actualizado = productService.actualizar(id, body);
            return ResponseEntity.ok(actualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ====== USUARIOS ======

    @GetMapping("/users")
    public List<ModelUser> getAllUsers() {
        return userService.listarTodos();
    }

    @PostMapping("/users")
    public ModelUser createUser(@RequestBody ModelUser u) {
        return userService.crear(u);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<ModelUser> updateUser(
            @PathVariable Long id,
            @RequestBody ModelUser body) {
        try {
            ModelUser actualizado = userService.actualizar(id, body);
            return ResponseEntity.ok(actualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ====== CITAS (ADMIN) ======

    @GetMapping("/citas")
    public List<ModelCita> getAllCitas() {
        return citaService.listarTodas();
    }

    @DeleteMapping("/citas/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        try {
            citaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
*/