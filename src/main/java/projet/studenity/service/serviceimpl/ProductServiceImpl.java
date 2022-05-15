package projet.studenity.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.studenity.dao.ProductDao;
import projet.studenity.model.Product;
import projet.studenity.repository.ProductRepository;
import projet.studenity.repository.UserRepository;
import projet.studenity.service.ProductService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private UserRepository userRepo;

    public Product findProductById(int id) {

        Product product = productDao.findProductById(id);
        return product;
    }

    @Override
    public List<Product> findProductBy(String chaine) {
        List<Product> listProduct = productRepo.findAll();
        List<Product> listProductWithName = new ArrayList<>();
        for(Product product:listProduct){
            if(product.getName().equalsIgnoreCase(chaine)){
                listProductWithName.add(product);
            }else
            if(product.getDescription().equalsIgnoreCase(chaine)){
                listProductWithName.add(product);
            }else if(product.getStartDate().toString().equalsIgnoreCase(chaine)){
                listProductWithName.add(product);
            }
        }
        if(listProductWithName.isEmpty()) return null;
        return listProductWithName;
    }

    @Override
    public boolean reserveProduct(int id) {
        Product product = findProductById(id);
        if(product.getAvailability()!= 3){
            product.setAvailability(3);
        }
        try {
            productDao.updateProduct(product);
        }catch(Exception e){return false;}
        return true;
    }

    @Override
    public List<Product> findProductByCategory(int idCategory) {
        List<Product> listProduct = productRepo.findAll();
        List<Product> listProductByCategory = new ArrayList<>();
        for(Product product:listProduct){
            if(product.getCategoryCode()==idCategory){
                listProductByCategory.add(product);
            }
        }
        if(listProductByCategory.isEmpty()) return null;
        return listProductByCategory;
    }

    @Override
    public List<Product> listProductByUser(int idUser) {
        List<Product> products = productRepo.findAll();
        List<Product> productByUser = new ArrayList<>();
        for(Product product: products){
            if(product.getUserId()==idUser){
                productByUser.add(product);
            }
        }
        if(productByUser.isEmpty()) return null;
        return productByUser;
    }

    //A tester
    @Override
    public List<Product> findProductByFilter(int idCategory, int statusCode, int availability, int startDate) throws ParseException {
        List<Product> products = productRepo.findAll();
        List<Product> listProduct = new ArrayList<>();
        List<Product> listCategory = new ArrayList<>();
        List<Product> listStatus = new ArrayList<>();
        List<Product> listAvailability = new ArrayList<>();
        List<Product> listDate = new ArrayList<>();
        Date dateCurrent = new Date();

        if (idCategory != 0) {
            for (Product product : products) {
                if (product.getCategoryCode() == idCategory) {
                    listCategory.add(product);
                }
            }
        }
        if (idCategory == 0) {
            listCategory.addAll(products);
        }
        if (statusCode != 0) {
            for (Product p : listCategory) {
                if (p.getStatusCode() == statusCode) {
                    listStatus.add(p);
                }
            }
        }
        if (statusCode == 0) {
            listStatus.addAll(listCategory);
        }
        if (availability != 0) {
            for (Product p : listStatus) {
                if (p.getAvailability() == availability) {
                    listAvailability.add(p);
                }
            }
        }
        if (availability == 0) {
            listAvailability.addAll(listStatus);
        }
//              else if(startDate!=0){
//                for(Product p: listCategory) {
//                    if(startDate==1){
//                        if(p.getStartDate().compareTo(dateCurrent)==0){
//                            listDate.add(p);
//                    }
//                    if (startDate==2) {
//                        if(p.getStartDate().)
//                        listStatus.add(p);
//                    }
//                }
//            }
//            else if(startDate==0) {
//                listStatus.addAll(listCategory);
//            }
        listProduct.addAll(listAvailability);
        return listProduct;
    }

    @Override
    public boolean createProduct(Product product) {
        try {
            product.setUserId(4); //Pour tester, a faire en Front
            product.setAvailability(1);
            //Set Current Date
            long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);
            product.setStartDate(date);
            if(product.getCategoryCode()==1) product.setPrice(2); // si livre => 2 pts
            else if(product.getCategoryCode()==2) product.setPrice(3); // si equipements => 3 pts
            else if (product.getCategoryCode()==3) product.setPrice(1); // si autre => 1 pts
            productRepo.save(product);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateProduct(Product product) {
        productDao.updateProduct(product);
        return true;
    }

    @Override
    public boolean deleteProduct(int id) {
        try {
            productRepo.deleteById(id);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }
}
