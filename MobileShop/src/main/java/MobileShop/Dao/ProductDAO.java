package MobileShop.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.Product;


public interface ProductDAO extends JpaRepository<Product, Integer>{

	@Query("SELECT pr FROM Product pr WHERE pr.deleted = false AND pr.deleted_by IS NULL")
	List<Product> findAllProducts();
	
	Boolean existsByCode(String code);

	@Query("SELECT pr FROM Product pr WHERE pr.code =?1 AND pr.deleted = false AND pr.deleted_by IS NULL")
	List<Product> search(String search);

//	@Query("SELECT pr FROM Product pr WHERE pr.deleted = false AND pr.deleted_by IS NULL")
//	List<Product> findNewProducts(Sort by);

	@Query(value ="SELECT * FROM products pr WHERE pr.deleted = false AND pr.deleted_by IS NULL ORDER BY pr.created_at DESC LIMIT 0, 4", nativeQuery = true)
	List<Product> findNewProducts();

	@Query("SELECT pr FROM Product pr WHERE pr.id =?1 AND pr.slug LIKE %?2% AND pr.deleted = false AND pr.deleted_by IS NULL")
	Product productDetail(Integer proId, String slug);

}
