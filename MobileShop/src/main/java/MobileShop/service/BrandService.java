package MobileShop.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import MobileShop.Entity.Brand;
import MobileShop.Validation.BrandCreateValidation;
import MobileShop.Validation.BrandUpdateValidation;

public interface BrandService {

	ResponseEntity<?> createBrand(@Valid BrandCreateValidation brandCreateValidation);

	List<Brand> findAll();

	ResponseEntity<?> updateBrand(@Valid BrandUpdateValidation brandUpdateValidation);

	ResponseEntity<?> deleteBrand(@Valid BrandUpdateValidation brandUpdateValidation);

	List<Brand> search(String key_search);

}
