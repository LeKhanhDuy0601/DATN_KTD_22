package MobileShop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MobileShop.Entity.ProductImage;
import MobileShop.service.ProductImageService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/product-images")
public class ProductImageRestController {

	@Autowired
	ProductImageService productImageService;
	
	@GetMapping("/find-images-by-product/{product_id}")
	public List<ProductImage> findImagesByProduct(@PathVariable("product_id") Integer product_id){
		return productImageService.findImagesByProduct(product_id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		productImageService.deleteImage(id);
	}
	
}
