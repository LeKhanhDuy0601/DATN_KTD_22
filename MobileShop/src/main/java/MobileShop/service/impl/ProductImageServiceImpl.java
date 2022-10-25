package MobileShop.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MobileShop.Dao.ProductImageDAO;
import MobileShop.Entity.ProductImage;
import MobileShop.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService{

	@Autowired
	ProductImageDAO productImageDAO;
	
	@Override
	public List<ProductImage> findImagesByProduct(Integer product_id) {
		// TODO Auto-generated method stub
		return productImageDAO.findImagesByProduct(product_id);
	}

	@Override
	public void deleteImage(Integer id) {
		// TODO Auto-generated method stub
		productImageDAO.deleteById(id);
	}
	
}
