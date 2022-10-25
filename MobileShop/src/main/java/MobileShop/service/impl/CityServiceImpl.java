package MobileShop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MobileShop.Dao.CityDAO;
import MobileShop.Entity.City;
import MobileShop.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	CityDAO cityDAO;

	@Override
	public List<City> getListCities() {
		// TODO Auto-generated method stub
		return cityDAO.findAll();
	}

}
