package projet.studenity.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import projet.studenity.model.Product;
import projet.studenity.repository.ProductRepository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDao {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getProductCount() {
        return jdbcTemplate.queryForObject("select count(1) from Product", Integer.class);
    }

    public Product findProductById(int id) {
        return this.entityManager.find(Product.class, id);
    }

    public List<Product> findProductByName(String name) {
        return jdbcTemplate.query("select * from Product where name = ?", new ProductDao.ProductRowMapper(), new Object[]{name});
    }

    public List<Product> getAll() {
        return jdbcTemplate.query("select * from Product", new ProductDao.ProductRowMapper());
    }

    @Transactional
    public void updateProduct(Product product){
        entityManager.createNativeQuery("UPDATE product SET name=?, image=?, description=?, id_status=?, id_category=?, id_availability=?, Price=?, start_date=? WHERE id_product=?")
                .setParameter(1, product.getName())
                .setParameter(2, product.getImage())
                .setParameter(3, product.getDescription())
                .setParameter(4, product.getStatusCode())
                .setParameter(5, product.getCategoryCode())
                .setParameter(6, product.getAvailability())
                .setParameter(7, product.getPrice())
                .setParameter(8, product.getStartDate())
                .setParameter(9, product.getId())
                .executeUpdate();
    }

//    @Transactional
//    public void createProduct(Product product){
//        entityManager.createNativeQuery("INSERT INTO product (name,id_status,id_user,id_category) VALUES (?,?,?,?)")
//                .setParameter(1, product.getName())
//                .setParameter(2, product.getStatusCode())
//                .setParameter(3, product.getUserCode())
//                .setParameter(4, product.getCategoryCode())
//                .executeUpdate();
//    }

    private final class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getInt("id_product"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setCategoryCode(rs.getInt("id_category"));
            product.setStatusCode(rs.getInt("id_status"));
            product.setImage(rs.getString("image"));
            return product;
        }
    }
}
