package MobileShop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MobileShop.Dao.WardDAO;
import MobileShop.Entity.Ward;
import MobileShop.service.WardService;

@Service
public class WardServiceImpl implements WardService {
	@Autowired
	WardDAO wardDAO;

	@Override
	public List<Ward> getListWardByDistrict(Integer districtID) {
		// TODO Auto-generated method stub
		return wardDAO.getListWardByDistrict(districtID);
	}
}
