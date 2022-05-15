package projet.studenity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projet.studenity.model.Class;
import projet.studenity.model.ClassUser;
import projet.studenity.service.ClassService;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping(value="/listClassByUser/{idUser}")
    public List<Class> listClassByUser(@PathVariable ("idUser")  int idUser ){
        return classService.listClassByUser(idUser);
    }
    @GetMapping(value="/listClassByUserCreator/{idUser}")
    public List<Class> listClassByUserCreator(@PathVariable ("idUser") int idUser){
        return classService.listClassByUserCreator(idUser);
    }
    @GetMapping(value="/classById/{idClass}")
    public Class findClassById(@PathVariable ("idClass") int idClass){return classService.findClassById(idClass);}

    @PostMapping(value="/deleteUserFromClass")
    public boolean deleteUserFromClass(@RequestBody ClassUser classUser){
        return classService.deleteUserFromClass(classUser);
    }

    @PostMapping(value="/create")
    public boolean addClass(@RequestBody Class c){
        return classService.addClass(c);
    }

    @PostMapping(value="/update")
    public boolean updateClass(@RequestBody Class c){
        return classService.updateClass(c);
    }

    @PostMapping(value="/addUserToClass")
    public boolean addUserToClass(@RequestBody ClassUser classUser){
        return classService.addUserToClass(classUser);
    }

    @PostMapping(value="/deleteClass")
    public boolean deleteClass(@RequestBody int idClass){
        return classService.deleteClass(idClass);}

    @GetMapping(value="/list")
    public List<Class> listClass(){
        return classService.listClass();
    }

    @PostMapping(value="/participant")
    public int participant(@RequestBody int idClass){
        return classService.participant(idClass);}

    @PostMapping(value="/userByClass")
    public List<String> listUserByClass(@RequestBody int idClass){
        return classService.listNameUserByClass(idClass);}


    @GetMapping(value="/test")
    public boolean test() {
        Class classes = new Class();
        classes.setDescription("CC prepare");
        classes.setName("Un cours de Programmation et Backend");
        return classService.addClass(classes);
    }
}