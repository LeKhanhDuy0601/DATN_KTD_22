package MobileShop.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import MobileShop.Entity.Attribute;
import MobileShop.Validation.AttributeCreateValidation;
import MobileShop.Validation.AttributeUpdateValidation;

public interface AttributeService {

	List<Attribute> findAllAttributes();

	List<Attribute> findAttributesByAttributeGroup(Integer group_id);

	ResponseEntity<?> createAttribute(@Valid AttributeCreateValidation attributeCreateValidation);

	ResponseEntity<?> updateAttribute(@Valid AttributeUpdateValidation attributeUpdateValidation);

	ResponseEntity<?> deleteAttribute(@Valid AttributeUpdateValidation attributeUpdateValidation);

	List<Attribute> search(String key_search);

}
