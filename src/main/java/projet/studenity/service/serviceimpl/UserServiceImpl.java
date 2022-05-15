package projet.studenity.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.studenity.dao.UserDao;
import projet.studenity.model.User;
import projet.studenity.repository.UserRepository;
import projet.studenity.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRepository userRepo;

    public User findUserById(int id) {

        User user = userDao.findById(id);
        return user;
    }

    @Override
    public boolean deleteUser(int id) {
        try {
            userRepo.deleteById(id);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<User> findUserByFirstName(String firstName) {
        List<User> listUser = userRepo.findAll();
        List<User> listUserByFirstName = new ArrayList<>();
        for(User user:listUser){
            if(user.getFirstName().equalsIgnoreCase(firstName)){
                listUserByFirstName.add(user);
            }
        }
        if(listUserByFirstName.isEmpty()) return null;
        return listUser;
    }
    @Override
    public User findUserByEmail(String email) {
        List<User> listUser = userRepo.findAll();
        for(User user:listUser){
            if(user.getEmail().equalsIgnoreCase(email)){
                return user;
            }
        }
        return null;

    }
    @Override
    public boolean createUser(User user) {
        List<User> userList = userRepo.findAll();
        for(User userTemp : userList){
            if(userTemp.getEmail().equalsIgnoreCase(user.getEmail())){
                return false;
            }
        }
        userRepo.save(user);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            userRepo.save(user);
        }catch(Exception e){return false;}
        return true;
    }


    @Override
    public boolean login(String username, String password){
        List<User> listUser = userRepo.findAll();
        for(User user:listUser){
            if(user.getEmail().equalsIgnoreCase(username) && user.getPassword().equals(password)){
                return true;
            }
        }
       return false;
    }
}
