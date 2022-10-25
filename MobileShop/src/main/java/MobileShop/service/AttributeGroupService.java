package MobileShop.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import MobileShop.Entity.AttributeGroup;
import MobileShop.Validation.AttributeGroupCreateValidation;
import MobileShop.Validation.AttributeGroupUpdateValidation;

public interface AttributeGroupService {

	List<AttributeGroup> findAllAttributeGroup();

	ResponseEntity<?> createAttributeGroup(@Valid AttributeGroupCreateValidation attributeGroupCreateValidation);

	List<AttributeGroup> search(String key_search);

	ResponseEntity<?> updateAttributeGroup(@Valid AttributeGroupUpdateValidation attributeGroupUpdateValidation);

	ResponseEntity<?> deleteAttributeGroup(@Valid AttributeGroupUpdateValidation attributeGroupUpdateValidation);
	
}
