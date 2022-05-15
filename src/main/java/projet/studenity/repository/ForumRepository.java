package projet.studenity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.studenity.model.Forum;

public interface ForumRepository extends JpaRepository<Forum,Integer> {
}
