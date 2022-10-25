package MobileShop.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import MobileShop.Entity.CartDetail;


public interface CartDetailDAO extends JpaRepository<CartDetail, Integer>{

}
