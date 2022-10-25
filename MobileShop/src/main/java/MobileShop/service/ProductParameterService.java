package MobileShop.service;

import java.util.List;

import MobileShop.Entity.ProductParameter;

public interface ProductParameterService {

	List<ProductParameter> findByProID(Integer proID);

}
