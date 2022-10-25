package MobileShop.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MobileShop.Entity.CategoryGroup;
import MobileShop.Validation.CategoryGroupCreateValidation;
import MobileShop.Validation.CategoryGroupUpdateValidation;
import MobileShop.service.CategoryGroupService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/category-groups")
public class CategoryGroupRestController {
@Autowired
CategoryGroupService categoryGroupService;

@GetMapping()
public List<CategoryGroup> findAllCategoryGroup(){
	return categoryGroupService.findAllCategoryGroup();
}
@PostMapping()
public ResponseEntity<?> createCategoryGroup(@RequestBody @Valid CategoryGroupCreateValidation categoryGroupCreateValidation) {
	return categoryGroupService.create(categoryGroupCreateValidation);
}
@PutMapping()
public ResponseEntity<?> updateCategoryGroup(@RequestBody @Valid CategoryGroupUpdateValidation categoryGroupUpdateValidation) {
	return categoryGroupService.update(categoryGroupUpdateValidation);
}
@PutMapping("/{id}")
public ResponseEntity<?> deleteCategoryGroup(@RequestBody @Valid CategoryGroupUpdateValidation categoryGroupUpdateValidation) {
	return categoryGroupService.delete(categoryGroupUpdateValidation);
}
}
