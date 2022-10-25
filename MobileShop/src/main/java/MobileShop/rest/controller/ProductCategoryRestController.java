package MobileShop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MobileShop.Entity.ProductCategory;
import MobileShop.service.ProductCategoryService;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/product-categories")
public class ProductCategoryRestController {
	
	@Autowired
	ProductCategoryService productCategoryService;

	@GetMapping()
	public List<ProductCategory> findAllProductCategory(){
		return productCategoryService.findAllProductCategory();
	}
	
	@PostMapping()
	public ProductCategory create(@RequestBody ProductCategory productCategory) {
		return productCategoryService.create(productCategory);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		productCategoryService.delete(id);
	}
	
}
