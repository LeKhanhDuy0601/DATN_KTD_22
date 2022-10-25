package MobileShop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MobileShop.Entity.Ward;
import MobileShop.service.WardService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/wards")
public class WardRestController {
	@Autowired
	WardService wardService;
	
	@GetMapping("/{districtID}")
	public List<Ward> getListWardByDistrict(@PathVariable("districtID") Integer districtID) {
		return wardService.getListWardByDistrict(districtID);
	}
	
}
