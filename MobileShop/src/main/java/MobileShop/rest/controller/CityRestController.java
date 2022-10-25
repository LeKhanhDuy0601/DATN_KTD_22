package MobileShop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MobileShop.Entity.City;
import MobileShop.service.CityService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/cities")
public class CityRestController {
	@Autowired 
	CityService cityService;
	
	@GetMapping()
	public List<City> getListCities() {
		return cityService.getListCities();
	}
}
