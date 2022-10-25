package MobileShop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MobileShop.Dao.ProductParameterDAO;
import MobileShop.Entity.ProductParameter;
import MobileShop.service.ProductParameterService;

@Service
public class ProductParameterServiceImpl implements ProductParameterService{

	@Autowired
	ProductParameterDAO productParameterDAO;

	@Override
	public List<ProductParameter> findByProID(Integer proID) {
		// TODO Auto-generated method stub
		return productParameterDAO.findByProID(proID);
	}
	
}
