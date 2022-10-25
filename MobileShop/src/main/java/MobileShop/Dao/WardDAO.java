package MobileShop.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.Ward;

public interface WardDAO extends JpaRepository<Ward, Integer> {
	@Query("select wd from Ward wd where wd.district.id=?1")
	List<Ward> getListWardByDistrict(Integer districtID);

}
