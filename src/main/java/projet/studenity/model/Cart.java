package projet.studenity.model;

import javax.persistence.*;

@Entity
@Table(name="CART")
public class Cart {
    @Id
    @Column(name="id_Cart")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="id_User")
    private int idUser;
    @Column(name="id_Product")
    private int idProduct;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}
