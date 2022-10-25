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

import MobileShop.Entity.ParameterGroup;
import MobileShop.Validation.ParameterGroupCreateValidation;
import MobileShop.Validation.ParameterGroupUpdateValidation;
import MobileShop.service.ParameterGroupService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/parameter-groups")
public class ParameterGroupRestController {

	@Autowired
	ParameterGroupService parameterGroupService;
	
	@GetMapping()
	public List<ParameterGroup> findAll(){
		return parameterGroupService.findAll();
	}
	
	@PostMapping()
	public ResponseEntity<?> create(@RequestBody @Valid ParameterGroupCreateValidation parameterGroupCreateValidation){
		return parameterGroupService.create(parameterGroupCreateValidation);
	}
	
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody @Valid ParameterGroupUpdateValidation parameterGroupUpdateValidation){
		return parameterGroupService.update(parameterGroupUpdateValidation);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> delete(@RequestBody @Valid ParameterGroupUpdateValidation parameterGroupUpdateValidation){
		return parameterGroupService.delete(parameterGroupUpdateValidation);
	}
}
