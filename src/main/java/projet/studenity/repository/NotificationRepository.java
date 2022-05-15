package projet.studenity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import projet.studenity.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
