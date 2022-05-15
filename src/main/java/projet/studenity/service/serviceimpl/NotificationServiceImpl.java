package projet.studenity.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.studenity.model.Notification;
import projet.studenity.repository.NotificationRepository;
import projet.studenity.service.NotificationService;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notiRepo;

    @Override
    public List<Notification> getListNotification(int idUser) {
        List<Notification> notis = notiRepo.findAll();
        List<Notification> listNoti = new ArrayList<>();
        for(Notification noti: notis){
            if(noti.getIdUser()==idUser){
                listNoti.add(noti);
            }
        }
        return listNoti;
    }

    @Override
    public Boolean deleteNotification(int idNoti) {
        List<Notification> listNoti = notiRepo.findAll();
        for(Notification noti: listNoti){
            if(noti.getId()==idNoti){
                notiRepo.delete(noti);
                return true;
            }
        }
        return false;
    }


}
