package projet.studenity.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.studenity.dao.CartDao;
import projet.studenity.model.Cart;
import projet.studenity.model.Product;
import projet.studenity.model.User;
import projet.studenity.repository.CartRepository;
import projet.studenity.repository.ProductRepository;
import projet.studenity.service.CartService;
import projet.studenity.service.ProductService;
import projet.studenity.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepo;

    @Autowired
    UserService userService;

    @Autowired
    ProductRepository productRepo;
    @Autowired
    ProductService productService;
    @Autowired
    CartDao cartDao;

    @Override
    public List<Product> listProduct(int idUser){
        List<Product> listProduct = new ArrayList<>();
        List<Cart> listCart = cartRepo.findAll();
        try {
            for (Cart cart : listCart) {
                if (cart.getIdUser() == idUser) {
                    //chercher le produit de cette utilisateur
                    Product product = productService.findProductById(cart.getIdProduct());
                    listProduct.add(product);
                }
            }
        }catch(Exception e){
            return null;
        }
        if(listProduct.isEmpty()) return null;
        return listProduct;
    }

    @Override
    public boolean addToCart(Cart cart) {
        try {
            List<Cart> listCart = cartRepo.findAll();
            Product product = productService.findProductById(cart.getIdProduct());
            //S'il est pas disponible, return false
            if(product.getAvailability() != 1) {return false;}
            product.setAvailability(3);
            productService.updateProduct(product);
            cartRepo.save(cart);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteFromCart(Cart cart) {
        try {
            Product product = productService.findProductById(cart.getIdProduct());
            User user = userService.findUserById(cart.getIdUser());
            if(product.getAvailability()!=1) product.setAvailability(1); //Passer la disponibilite a Available
            productService.updateProduct(product);
            List<Cart> cartList = cartRepo.findAll();
            for(Cart c: cartList){
                //Find ID CART pour supprimer
                if(c.getIdUser()==cart.getIdUser() && c.getIdProduct()==c.getIdProduct()){
                    cart.setId(c.getId());
                }
            }
            cartRepo.delete(cart);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean payCart(int idUser){
        List<Cart> listCart = cartRepo.findAll();
        try {
            for (Cart cart : listCart) {
                if (cart.getIdUser() == idUser) {
                    Product product = productService.findProductById(cart.getIdProduct()); //chercher le produit de cette utilisateur
                    product.setAvailability(2); //Passer la disponibilite a Unavailable
                    productService.updateProduct(product);
                    cartRepo.delete(cart);
                }
            }
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAllFromCart(int idUser) {
        List<Cart> listCart = cartRepo.findAll();
        User user = userService.findUserById(idUser);
        try {
            for (Cart cart : listCart) {
                if (cart.getIdUser() == idUser) {
                    Product product = productService.findProductById(cart.getIdProduct());
                    product.setAvailability(1); //Passer la disponibilite a Available
                    productService.updateProduct(product);
                    cartRepo.delete(cart);
                }
            }
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Integer totalPrice(int idUser) {
        List<Cart> listCart = cartRepo.findAll();
        int price=0;
        try {
            for (Cart cart : listCart) {
                if (cart.getIdUser() == idUser) {
                    //chercher le produit de cette utilisateur
                    Product product = productService.findProductById(cart.getIdProduct());
                    price +=product.getPrice();
                }
            }
        }catch(Exception e){
            return null;
        }
        return price;
    }
}
