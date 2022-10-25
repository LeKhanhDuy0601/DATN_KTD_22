package MobileShop.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.District;

public interface DistrictDAO extends JpaRepository<District, Integer> {
	@Query("select dt from District dt where dt.city.id=?1")
	List<District> getListDistrictByCity(Integer cityID);

}
