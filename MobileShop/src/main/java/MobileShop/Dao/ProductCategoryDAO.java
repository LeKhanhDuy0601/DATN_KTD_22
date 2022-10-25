package MobileShop.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import MobileShop.Entity.ProductCategory;

public interface ProductCategoryDAO extends JpaRepository<ProductCategory, Integer>{

}
