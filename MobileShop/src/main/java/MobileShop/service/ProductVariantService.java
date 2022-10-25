package MobileShop.service;

import java.util.List;


import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import MobileShop.Entity.ProductVariant;
import MobileShop.Validation.GetVariantByAttributesValidation;
import MobileShop.Validation.ProductVariantCreateValidation;
import MobileShop.Validation.ProductVariantUpdateValidation;



public interface ProductVariantService {

	List<ProductVariant> findVariantsByProduct(Integer product_id);

	ResponseEntity<?> createVariant(ProductVariantCreateValidation productVariantCreateValidation);
	
	ResponseEntity<?> updateVariant(ProductVariantUpdateValidation productVariantUpdateValidation);

	ResponseEntity<?> deleteVariant(ProductVariantUpdateValidation productVariantUpdateValidation);

	ResponseEntity<?> getVariantsByAttributes(GetVariantByAttributesValidation getVariantByAttributesValidation);
}
