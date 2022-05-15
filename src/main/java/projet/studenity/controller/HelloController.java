package projet.studenity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projet.studenity.dao.ProductDao;
import projet.studenity.repository.ProductRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class HelloController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDao productDao;
    String list[] = {"Hello word", "Emma", "Aniesse"};
    @RequestMapping("hello")
       public String[] getMessage(){
        // Hello m1= new Hello("hello");
        // m1.getMessage()

        return list;
    }


}
