package MobileShop.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import MobileShop.Entity.Authority;
import MobileShop.Entity.City;

public interface CityDAO extends JpaRepository<City, Integer> {

}
