package MobileShop.Dao;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.CategoryGroup;

public interface CategoryGroupDAO extends JpaRepository<CategoryGroup, Integer>{
	@Query("SELECT gr FROM CategoryGroup gr WHERE gr.deleted = false AND gr.deleted_by IS NULL")
	List<CategoryGroup> findAllCategoryGroup(Sort sort);

	boolean existsByCode( String code);
}
