package projet.studenity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projet.studenity.model.Notification;
import projet.studenity.service.NotificationService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/noti")
public class NotificationController {
    @Autowired
    private NotificationService notiService;

    @GetMapping(value="/all/{idUser}")
    public List<Notification> getNoti(@PathVariable("idUser")int idUser){
        return notiService.getListNotification(idUser);
    }

    @GetMapping(value="/delete/{idNoti}")
    public boolean deleteNoti(@PathVariable("idNoti")int idNoti){
        return notiService.deleteNotification(idNoti);
    }

    @GetMapping(value="/test")
    public List<Notification> test() {
        return notiService.getListNotification(4);

    }
}