package MobileShop.Dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.PriceHistory;

public interface PriceHistoryDAO extends JpaRepository<PriceHistory, Integer>{

	@Query("SELECT price FROM PriceHistory price WHERE price.product.code =?1")
	List<PriceHistory> search(String search, Sort sort);
	
}
