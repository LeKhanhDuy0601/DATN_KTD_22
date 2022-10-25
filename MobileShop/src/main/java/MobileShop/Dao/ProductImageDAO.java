package MobileShop.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.ProductImage;

public interface ProductImageDAO extends JpaRepository<ProductImage, Integer>{

	@Query("SELECT image FROM ProductImage image WHERE image.product.id =?1")
	List<ProductImage> findImagesByProduct(Integer product_id);
	
}
