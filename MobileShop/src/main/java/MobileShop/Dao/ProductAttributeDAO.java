package MobileShop.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.Attribute;
import MobileShop.Entity.ProductAttribute;
import MobileShop.Entity.ProductVariant;

public interface ProductAttributeDAO extends JpaRepository<ProductAttribute, Integer>{

	@Query("SELECT pr FROM ProductAttribute pr WHERE pr.product_variant_attribute.id=?1")
	List<ProductAttribute> findProductAttributesByVariant(Integer id);

	@Query("SELECT DISTINCT  pr.attribute FROM ProductAttribute pr WHERE pr.product_variant_attribute.product.id=?1 AND pr.level=?2")
	List<MobileShop.Entity.Attribute> getListAtrributesByProduct(Integer id, Integer level);
	
	@Query("SELECT pr.product_variant_attribute FROM ProductAttribute pr WHERE pr.product_variant_attribute.product.id=?1 AND pr.attribute.id=?2 AND pr.level=?3")
	List<ProductVariant> getVariantsByAttribute(Integer proID, Integer attID, Integer level);
	
	@Query("SELECT pr.attribute FROM ProductAttribute pr WHERE pr.product_variant_attribute.id=?1 AND pr.level=?2")
	List<Attribute> getListAttributesByVariant(Integer variant_id, Integer level);
	
	@Query("SELECT pr FROM ProductAttribute pr WHERE pr.product_variant_attribute.id=?1 AND pr.level=?2")
	ProductAttribute getProductAttributeByVariant(Integer variant_id, Integer level);

	@Query("SELECT pr.product_variant_attribute FROM ProductAttribute pr WHERE pr.product_variant_attribute.product.id=?1 "
			+ "AND pr.attribute.id=?2 AND pr.level=?3")
	List<ProductVariant> findVariantsByAttribute(Integer product_id, Integer attribute_id, Integer level);

	@Query("SELECT pr FROM ProductAttribute pr WHERE pr.product_variant_attribute.id=?1")
	List<ProductAttribute> findByVariantID(Integer variant_id);

	@Query("SELECT pr.attribute FROM ProductAttribute pr WHERE pr.product_variant_attribute.id=?1 AND pr.level=?2")
	Attribute findByVariantIDAndLevel(Integer id, Integer level);
	
}
