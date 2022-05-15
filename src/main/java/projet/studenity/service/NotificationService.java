package projet.studenity.service;


import projet.studenity.model.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getListNotification(int idUser);
    Boolean deleteNotification(int idNoti);
}
