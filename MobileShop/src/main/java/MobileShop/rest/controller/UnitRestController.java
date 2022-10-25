package MobileShop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MobileShop.Entity.Unit;
import MobileShop.service.UnitService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/units")
public class UnitRestController {

	@Autowired
	UnitService unitService;
	
	@GetMapping()
	public List<Unit> findAll(){
		return unitService.findAll();
	}
	
}
