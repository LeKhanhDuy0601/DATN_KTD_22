package MobileShop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MobileShop.Entity.ProductParameter;
import MobileShop.service.ProductParameterService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/product-parameters")
public class ProductParameterRestController {

	@Autowired
	ProductParameterService productParameterService;
	
	@GetMapping("/{proID}")
	public List<ProductParameter> findByProID(@PathVariable("proID") Integer proID){
		return productParameterService.findByProID(proID);
	}
	
}
