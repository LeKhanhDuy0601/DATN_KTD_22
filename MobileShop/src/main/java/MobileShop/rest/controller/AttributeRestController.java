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

import MobileShop.Entity.Attribute;
import MobileShop.Validation.AttributeCreateValidation;
import MobileShop.Validation.AttributeUpdateValidation;
import MobileShop.service.AttributeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/attributes")
public class AttributeRestController {
	@Autowired
	AttributeService attributeService;
	
	@GetMapping()
	public List<Attribute> findAllAttributes(){
		return attributeService.findAllAttributes();
	}
	
	@GetMapping("/find-attributes-by-attribute-group/{group_id}")
	public List<Attribute> findAttributesByAttributeGroup(@PathVariable("group_id") Integer group_id){
		return attributeService.findAttributesByAttributeGroup(group_id);
	}
	
	
	@PostMapping()
	public ResponseEntity<?> createAttribute(@RequestBody @Valid AttributeCreateValidation attributeCreateValidation){
		return attributeService.createAttribute(attributeCreateValidation);
	}
	
	@PutMapping()
	public ResponseEntity<?> updateAttribute(@RequestBody @Valid AttributeUpdateValidation attributeUpdateValidation){
		return attributeService.updateAttribute(attributeUpdateValidation);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> deleteAttribute(@RequestBody @Valid AttributeUpdateValidation attributeUpdateValidation){
		return attributeService.deleteAttribute(attributeUpdateValidation);
	}
	
	@GetMapping("/{key_search}")
	List<Attribute> search(@PathVariable("key_search") String key_search){
		return attributeService.search(key_search);
	}
}
