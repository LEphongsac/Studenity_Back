package projet.studenity.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.studenity.model.Class;
import projet.studenity.model.ClassUser;
import projet.studenity.model.Notification;
import projet.studenity.model.User;
import projet.studenity.repository.ClassRepository;
import projet.studenity.repository.ClassUserRepository;
import projet.studenity.repository.NotificationRepository;
import projet.studenity.service.ClassService;
import projet.studenity.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    ClassRepository classRepo;

    @Autowired
    ClassUserRepository classUserRepo;

    @Autowired
    NotificationRepository notiRepo;

    @Autowired
    UserService userService;

    @Override
    public Class findClassById(int idClass) {
        List<Class> classes = classRepo.findAll();
        for(Class c: classes){
            if(c.getId() == idClass){
                return c;
            }
        }
        return null;
    }

    @Override
    public boolean addClass(Class c) {
        c.setUserId(4); //Pour tester
        //Set Current Date
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        c.setStartDate(date);
        //Create new Class
        classRepo.save(c);

        //Comparer interet de l'utilisateur avec la nouvelle classe
        String[] nameClass = c.getName().split(" ");
        List<User> userList = userService.getUsers();
        for(User user: userList){
            String[] interestUser = user.getInterest().split(",");
            for(int i=0;i<nameClass.length;i++){
                for(int j=0;j<interestUser.length;j++) {
                    if (nameClass[i].equalsIgnoreCase(interestUser[j]) && user.getId()!=c.getUserId()) {
                        Notification noti = new Notification();
                        noti.setIdUser(user.getId());
                        noti.setTypeNoti(1);
                        noti.setMessage("Vous avez un cours de " + nameClass[i] + " qui pourrais vous intéresser");
                        //id of new Class
                        noti.setIdTempo(c.getId());
                        notiRepo.save(noti);
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean deleteUserFromClass(ClassUser classUser) {
        List<ClassUser> classeUsers = classUserRepo.findAll();
        for(ClassUser c: classeUsers){
            if(c.getClassId() == classUser.getClassId() && c.getUserId()==classUser.getUserId()){
                classUserRepo.delete(c);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteClass(int idClass) {
        classRepo.deleteById(idClass);
        return true;
    }


    @Override
    public boolean updateClass(Class c) {
        classRepo.save(c);
        return true;
    }

    @Override
    public boolean addUserToClass(ClassUser classUser) {
        List<ClassUser> classUsers = classUserRepo.findAll();
        for(ClassUser cU: classUsers){
            if(cU.getClassId() == classUser.getClassId() && cU.getUserId()==classUser.getUserId()){
                return false;
            }
        }
        classUserRepo.save(classUser);
        return true;
    }

    @Override
    public List<Class> listClass() {
        try {
            return classRepo.findAll();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Class> listClassByUser(int idUser) {
        List<ClassUser> classUsers = classUserRepo.findAll();
        List<Class> classes = new ArrayList<>();
        for(ClassUser classUser: classUsers){
            if(classUser.getUserId() == idUser){
                classes.add(findClassById(classUser.getClassId()));
            }
        }
        if(classes.isEmpty()) return null;
        return classes;
    }
    @Override
    public List<Class> listClassByUserCreator(int idUser) {
        List<Class> listClasses = classRepo.findAll();
        List<Class> classes = new ArrayList<>();
        for(Class classUserCreator: listClasses){
            if(classUserCreator.getUserId() == idUser){
                classes.add(classUserCreator);
            }
        }
        if(classes.isEmpty()) return null;
        return classes;
    }

    @Override
    public int participant(int idClass) {
        int count=0;
        List<ClassUser> classes = classUserRepo.findAll();
        for(ClassUser classUser: classes){
            if(classUser.getClassId() == idClass){
                count++;
            }
        }
        return count;
    }

    @Override
    public List<String> listNameUserByClass(int idClass) {
        List<ClassUser> listClassUser = classUserRepo.findAll();
        List<String> listNameUser = new ArrayList<>();
        for(ClassUser classUser: listClassUser){
            if(classUser.getClassId()==idClass){
                listNameUser.add(userService.findUserById(classUser.getUserId()).getFirstName()+" "+userService.findUserById(classUser.getUserId()).getFirstName());
            }
        }
        return listNameUser;
    }
}
