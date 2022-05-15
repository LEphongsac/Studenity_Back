package projet.studenity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.studenity.model.ClassUser;

public interface ClassUserRepository extends JpaRepository<ClassUser,Integer> {
}
