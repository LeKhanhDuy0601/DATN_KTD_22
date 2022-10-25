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

import MobileShop.Entity.Parameter;
import MobileShop.Validation.ParameterCreateValidation;
import MobileShop.Validation.ParameterUpdateValidation;
import MobileShop.service.ParameterService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/parameters")
public class ParameterRestController {

	@Autowired
	ParameterService parameterService;
	
	@GetMapping()
	public List<Parameter> findAllParameters(){
		return parameterService.findAllParameters();
	}
	
	@PostMapping()
	public ResponseEntity<?> create(@RequestBody @Valid ParameterCreateValidation parameterCreateValidation){
		return parameterService.create(parameterCreateValidation);
	}
	
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody @Valid ParameterUpdateValidation parameterUpdateValidation){
		return parameterService.update(parameterUpdateValidation);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> delete(@RequestBody @Valid ParameterUpdateValidation parameterUpdateValidation){
		return parameterService.delete(parameterUpdateValidation);
	}
	
	@GetMapping("/{key_search}")
	public List<Parameter> search(@PathVariable("key_search") String key_search){
		return parameterService.search(key_search);
	}
}
