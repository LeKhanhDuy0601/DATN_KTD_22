package MobileShop.service;

import java.util.List;

import MobileShop.Entity.ProductCategory;

public interface ProductCategoryService {

	List<ProductCategory> findAllProductCategory();

	ProductCategory create(ProductCategory productCategory);

	void delete(Integer id);
	
}
