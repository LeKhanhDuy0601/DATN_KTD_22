package MobileShop.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.Brand;

public interface BrandDAO extends JpaRepository<Brand, Integer>{
Boolean existsByCode(String code);
	
	@Query("SELECT br FROM Brand br WHERE br.deleted = false AND br.deleted_by IS NULL")
	List<Brand> findAllBrands();

	@Query("SELECT br FROM Brand br WHERE br.name LIKE %?1% AND br.deleted = false AND br.deleted_by IS NULL")
	List<Brand> search(String key_search);
}
