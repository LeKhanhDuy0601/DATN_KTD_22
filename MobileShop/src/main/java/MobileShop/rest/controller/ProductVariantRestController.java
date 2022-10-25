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

import MobileShop.Entity.ProductVariant;
import MobileShop.Validation.GetVariantByAttributesValidation;
import MobileShop.Validation.ProductVariantCreateValidation;
import MobileShop.Validation.ProductVariantUpdateValidation;
import MobileShop.service.ProductVariantService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/product-variants")
public class ProductVariantRestController {

	@Autowired
	ProductVariantService productVariantService;
	
	@GetMapping("/variants-by-product/{product_id}")
	public List<ProductVariant> findVariantsByProduct(@PathVariable("product_id") Integer product_id){
		return productVariantService.findVariantsByProduct(product_id);
	}
	
	@PostMapping()
	public ResponseEntity<?> createVariant(@RequestBody @Valid ProductVariantCreateValidation productVariantCreateValidation){
		return productVariantService.createVariant(productVariantCreateValidation);
	}
	
	@PutMapping()
	public ResponseEntity<?> updateVariant(@RequestBody @Valid ProductVariantUpdateValidation productVariantUpdateValidation){
		return productVariantService.updateVariant(productVariantUpdateValidation);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> deleteVariant(@RequestBody @Valid ProductVariantUpdateValidation productVariantUpdateValidation){
		return productVariantService.deleteVariant(productVariantUpdateValidation);
	}
	
	@PostMapping("/get-variants")
	public ResponseEntity<?> getVariantsByAttributes(@RequestBody @Valid GetVariantByAttributesValidation getVariantByAttributesValidation){
		return productVariantService.getVariantsByAttributes(getVariantByAttributesValidation);
	}
	
}
