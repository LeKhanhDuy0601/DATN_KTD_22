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

import MobileShop.Entity.Brand;
import MobileShop.Validation.BrandCreateValidation;
import MobileShop.Validation.BrandUpdateValidation;
import MobileShop.service.BrandService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/brands")
public class BrandRestController {
	@Autowired
	BrandService brandService;
	@GetMapping()
	public List<Brand> findAll(){
		return brandService.findAll();
	}
	
	@PostMapping()
	public ResponseEntity<?> createBrand(@RequestBody @Valid BrandCreateValidation brandCreateValidation){
		return brandService.createBrand(brandCreateValidation);
	}
	
	@PutMapping()
	public ResponseEntity<?> updateBrand(@RequestBody @Valid BrandUpdateValidation brandUpdateValidation){
		return brandService.updateBrand(brandUpdateValidation);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> deleteBrand(@RequestBody @Valid BrandUpdateValidation brandUpdateValidation){
		return brandService.deleteBrand(brandUpdateValidation);
	}
	
	@GetMapping("/{key_search}")
	public List<Brand> search(@PathVariable("key_search") String key_search){
		System.out.println(key_search);
		return brandService.search(key_search);
	}

}
