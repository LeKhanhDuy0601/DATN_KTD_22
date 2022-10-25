package MobileShop.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import MobileShop.Entity.CategoryGroup;
import MobileShop.Validation.CategoryGroupCreateValidation;
import MobileShop.Validation.CategoryGroupUpdateValidation;

public interface CategoryGroupService {

	List<CategoryGroup> findAllCategoryGroup();

	ResponseEntity<?> create(@Valid CategoryGroupCreateValidation categoryGroupCreateValidation);

	ResponseEntity<?> update(@Valid CategoryGroupUpdateValidation categoryGroupUpdateValidation);

	ResponseEntity<?> delete(@Valid CategoryGroupUpdateValidation categoryGroupUpdateValidation);

}
