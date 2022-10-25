package MobileShop.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import MobileShop.Entity.Product;
import MobileShop.Validation.ProductCreateValidation;
import MobileShop.Validation.ProductUpdateValidation;

public interface ProductService {

	List<Product> findAllProducts();

	ResponseEntity<?> createProduct(ProductCreateValidation productCreateValidation);
	
	ResponseEntity<?> updateProduct(ProductUpdateValidation productUpdateValidation);

	ResponseEntity<?> deleteProduct(ProductUpdateValidation productUpdateValidation);

	List<Product> search(String search);
	
	List<Product> findNewProducts();

	Product productDetail(Integer proId, String slug);

}
