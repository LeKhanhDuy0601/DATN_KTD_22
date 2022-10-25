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

import MobileShop.Entity.AttributeGroup;
import MobileShop.Validation.AttributeGroupCreateValidation;
import MobileShop.Validation.AttributeGroupUpdateValidation;
import MobileShop.service.AttributeGroupService;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/attribute-groups")
public class AttributeGroupRestController {
@Autowired
AttributeGroupService attributeGroupService;
@GetMapping()
public List<AttributeGroup> findAllAttributeGroup(){
	return attributeGroupService.findAllAttributeGroup();
}

@PostMapping
public ResponseEntity<?> createAttributeGroup(@RequestBody @Valid AttributeGroupCreateValidation attributeGroupCreateValidation){
	return attributeGroupService.createAttributeGroup(attributeGroupCreateValidation);
}

@PutMapping()
public ResponseEntity<?> updateAttributeGroup(@RequestBody @Valid AttributeGroupUpdateValidation attributeGroupUpdateValidation){
	return attributeGroupService.updateAttributeGroup(attributeGroupUpdateValidation);
}

@PutMapping("/{id}")
public ResponseEntity<?> deleteAttributeGroup(@RequestBody @Valid AttributeGroupUpdateValidation attributeGroupUpdateValidation){
	return attributeGroupService.deleteAttributeGroup(attributeGroupUpdateValidation);
}

@GetMapping("/{key_search}")
public List<AttributeGroup> search(@PathVariable("key_search") String key_search){
	return attributeGroupService.search(key_search);
}
}
