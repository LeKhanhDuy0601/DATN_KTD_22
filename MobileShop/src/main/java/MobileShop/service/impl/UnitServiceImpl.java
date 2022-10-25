package MobileShop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MobileShop.Dao.UnitDAO;
import MobileShop.Entity.Unit;
import MobileShop.service.UnitService;

@Service
public class UnitServiceImpl implements UnitService{

	@Autowired
	UnitDAO unitDAO;

	@Override
	public List<Unit> findAll() {
		// TODO Auto-generated method stub
		return unitDAO.findAll();
	}
	
}
