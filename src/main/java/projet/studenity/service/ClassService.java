package projet.studenity.service;


import projet.studenity.model.Class;
import projet.studenity.model.ClassUser;

import java.util.List;

public interface ClassService {
    public Class findClassById(int idClass);
    public boolean addClass(Class c);
    public boolean deleteUserFromClass(ClassUser classUser);
    public boolean deleteClass(int idClass);
    public boolean updateClass(Class c);
    public boolean addUserToClass(ClassUser classUser);
    public List<Class> listClass();
    public List<Class> listClassByUser(int idUser);
    public List<Class> listClassByUserCreator(int idUser);
    public int participant(int idClass);
    public List<String> listNameUserByClass(int idClass);
}
