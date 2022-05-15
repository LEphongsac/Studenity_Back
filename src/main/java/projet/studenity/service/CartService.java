package projet.studenity.service;

import projet.studenity.model.Cart;
import projet.studenity.model.Product;

import java.util.List;

public interface CartService {
    public boolean addToCart(Cart cart);
    public boolean deleteFromCart(Cart cart);
    public boolean payCart(int idUser);
    public boolean deleteAllFromCart(int idUser);
    public Integer totalPrice(int idUser);
    public List<Product> listProduct(int idUser);
    //hijh
}
