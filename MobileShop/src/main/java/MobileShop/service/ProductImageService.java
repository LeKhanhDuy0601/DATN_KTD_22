package MobileShop.service;

import java.util.List;

import MobileShop.Entity.ProductImage;

public interface ProductImageService {

	List<ProductImage> findImagesByProduct(Integer product_id);

	void deleteImage(Integer id);

}
