package MobileShop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MobileShop.Dao.ProductCategoryDAO;
import MobileShop.Entity.ProductCategory;
import MobileShop.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

	@Autowired
	ProductCategoryDAO productCategoryDAO;
	
	@Override
	public List<ProductCategory> findAllProductCategory() {
		// TODO Auto-generated method stub
		return productCategoryDAO.findAll();
	}

	@Override
	public ProductCategory create(ProductCategory productCategory) {
		// TODO Auto-generated method stub
		return productCategoryDAO.save(productCategory);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		productCategoryDAO.deleteById(id);
	}
	
}
