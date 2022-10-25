package MobileShop.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.ProductParameter;

public interface ProductParameterDAO extends JpaRepository<ProductParameter, Integer>{

	@Query("SELECT pr FROM ProductParameter pr WHERE pr.product.id=?1")
	List<ProductParameter> findByProID(Integer proID);

}
