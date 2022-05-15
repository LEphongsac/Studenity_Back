package projet.studenity.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.studenity.dao.ProductDao;
import projet.studenity.service.HomePageService;

@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    ProductDao productDao;


}
