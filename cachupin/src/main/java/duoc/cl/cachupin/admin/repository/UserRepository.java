package duoc.cl.cachupin.admin.repository;

import duoc.cl.cachupin.admin.model.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ModelUser, Long> {
    Optional<ModelUser> findByCorreo(String correo);
}
