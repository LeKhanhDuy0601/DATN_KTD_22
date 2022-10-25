package MobileShop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MobileShop.Entity.District;
import MobileShop.service.DistrictService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/districts")
public class DistrictRestController {
	@Autowired
	DistrictService districtService;

	@GetMapping("/{cityID}")
	public List<District> getListDistrictByCity(@PathVariable("cityID") Integer cityID) {
		return districtService.getListDistrictByCity(cityID);
	}

}
