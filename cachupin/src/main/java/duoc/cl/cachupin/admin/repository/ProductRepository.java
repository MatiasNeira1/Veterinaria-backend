package duoc.cl.cachupin.admin.repository;

import duoc.cl.cachupin.admin.model.ModelProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ModelProduct, Long> {
}


