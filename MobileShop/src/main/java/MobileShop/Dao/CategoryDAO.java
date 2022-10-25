package MobileShop.Dao;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer>{
	@Query("SELECT ct FROM Category ct where ct.categoryGroup.id =?1 AND ct.deleted = false AND ct.deleted_by IS NULL")
	List<Category> findCategoriesByCategoryGroup(Integer group_id);


	@Query("SELECT ct FROM Category ct where ct.deleted = false AND ct.deleted_by IS NULL")
	List<Category> findAllCategory();


	boolean existsByCode( String code);

	@Query("SELECT ct FROM Category ct where ct.name LIKE %?1% AND ct.deleted = false AND ct.deleted_by IS NULL")
	List<Category> search(String key_search);
}
