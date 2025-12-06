package duoc.cl.cachupin.admin.repository;

import duoc.cl.cachupin.admin.model.ModelCita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<ModelCita, Long> {

    List<ModelCita> findByCorreoUsuario(String correoUsuario);
}
