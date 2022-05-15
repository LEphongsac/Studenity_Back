package projet.studenity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.studenity.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
