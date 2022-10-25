package MobileShop.rest.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MobileShop.Entity.Product;
import MobileShop.Validation.ProductCreateValidation;
import MobileShop.Validation.ProductUpdateValidation;
import MobileShop.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

	@Autowired
	ProductService productService;
	
	@GetMapping()
	public List<Product> findAllProduct(){
		return productService.findAllProducts();
	}
	
	@PostMapping()
	public ResponseEntity<?> createProduct(@RequestBody @Valid ProductCreateValidation productCreateValidation){
		return productService.createProduct(productCreateValidation);
	}
	
	@PutMapping()
	public ResponseEntity<?> updateProduct(@RequestBody @Valid ProductUpdateValidation productUpdateValidation){
		return productService.updateProduct(productUpdateValidation);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@RequestBody @Valid ProductUpdateValidation productUpdateValidation){
		return productService.deleteProduct(productUpdateValidation);
	}
	
	@GetMapping("/{search}")
	public List<Product> search(@PathVariable("search") String search){
		return productService.search(search);
	}
	
}
