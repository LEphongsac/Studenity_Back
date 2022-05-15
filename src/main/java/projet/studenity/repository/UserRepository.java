package projet.studenity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.studenity.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
