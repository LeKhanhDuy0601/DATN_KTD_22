package MobileShop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MobileShop.Dao.DistrictDAO;
import MobileShop.Entity.District;
import MobileShop.service.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService {
	@Autowired
	DistrictDAO districtDAO;

	@Override
	public List<District> getListDistrictByCity(Integer cityID) {
		// TODO Auto-generated method stub
		return districtDAO.getListDistrictByCity(cityID);
	}
}
