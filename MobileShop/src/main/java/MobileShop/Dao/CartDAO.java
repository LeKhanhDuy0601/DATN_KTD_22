package MobileShop.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import MobileShop.Entity.Cart;

public interface CartDAO extends JpaRepository<Cart, Integer>{

}
