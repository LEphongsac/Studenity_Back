package projet.studenity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.studenity.model.Class;

public interface ClassRepository extends JpaRepository<Class,Integer> {
}
