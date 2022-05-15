package projet.studenity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.studenity.model.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer> {
}
