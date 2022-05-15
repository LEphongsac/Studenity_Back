package projet.studenity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.studenity.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
}
