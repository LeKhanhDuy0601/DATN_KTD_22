package MobileShop.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.ProductVariant;

public interface ProductVariantDAO extends JpaRepository<ProductVariant, Integer>{

	@Query("SELECT vr FROM ProductVariant vr WHERE vr.product.id =?1 AND vr.deleted = FALSE AND vr.deleted_by IS NULL")
	List<ProductVariant> findVariantsByProduct(Integer product_id);
	
	@Query("SELECT vr FROM ProductVariant vr WHERE vr.product.id=?1 AND vr.id=?2 AND vr.deleted = FALSE AND vr.deleted_by IS NULL")
	ProductVariant findVariantByIDAndProductID(Integer proID, Integer variant_id);
	
	@Query("SELECT SUM(vr.id) FROM ProductVariant vr WHERE vr.product.id=?1 AND vr.deleted = FALSE AND vr.deleted_by IS NULL")
	Integer sumVariantByProduct(Integer id);
	
}
