package projet.studenity.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projet.studenity.model.Product;
import projet.studenity.service.CartService;
import projet.studenity.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CartService cartService;

	@PostMapping(value="/listProductByUser")
	public List<Product> listProductByUser(@RequestBody int id){
		return productService.listProductByUser(id);
	}

	@GetMapping(value="/all")
	public List<Product> getAllProducts() {
		return productService.getProducts();
	}

	@PostMapping(value="/searchProduct")
	public List<Product> findProductBy(@RequestBody String chaine) {
		List<Product> listProduct = productService.findProductBy(chaine);
		return listProduct;
	}

	@PostMapping(value="/delete")
	public boolean deleteProductById(@RequestBody int id){
		return productService.deleteProduct(id);
	}

	@PostMapping(value="/create")
	public boolean createProduct(@RequestBody Product product){
		return productService.createProduct(product);
	}

	@PostMapping(value="/update")
	public boolean updateProduct(@RequestBody Product product){
		return productService.updateProduct(product);
	}

	@PostMapping(value="/reserve")
	public boolean reserveProduct(@RequestBody int id){
		return productService.reserveProduct(id);
	}

	@PostMapping(value="/searchByCategory")
	public List<Product> findProductByCategory(@RequestBody int idCategory){
		return productService.findProductByCategory(idCategory);
	}

	@GetMapping(value="/search/{idC}/{idS}/{idA}/{date}")
	public List<Product> filterProduct(@PathVariable("idC") int idCategory, @PathVariable("idS") int idStatus, @PathVariable("idA") int availability, @PathVariable("date") int startDate) throws ParseException {
		return productService.findProductByFilter(idCategory,idStatus,availability,startDate);
	}

	@GetMapping(value="/test")
	public
	List<Product> test() {
//		Product product = new Product();
//		product.setName("Clavier");
//		product.setImage("image");
//		product.setDescription("description");
//		product.setStatusCode(1);
//		product.setCategoryCode(2);
//		product.setAvailability(2);
//		productService.createProduct(product);
		return productService.getProducts();
	}
}