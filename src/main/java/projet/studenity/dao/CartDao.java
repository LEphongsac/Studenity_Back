package projet.studenity.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import projet.studenity.model.Cart;
import projet.studenity.repository.CartRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class CartDao {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CartRepository cartRepo;


    @Transactional
    public void updateCart(Cart cart){
        entityManager.createNativeQuery("UPDATE cart SET id_user=?, id_product=? WHERE id_cart=?")
                .setParameter(1, cart.getIdUser())
                .setParameter(2, cart.getIdProduct())
                .setParameter(3, cart.getId())
                .executeUpdate();
    }
}
