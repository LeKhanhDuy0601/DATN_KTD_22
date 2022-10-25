package MobileShop.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.Parameter;

public interface ParameterDAO extends JpaRepository<Parameter, Integer>{

	@Query("SELECT parameter FROM Parameter parameter WHERE parameter.deleted = false AND parameter.deleted_by IS NULL")
	List<Parameter> findAllParameters();

	@Query("SELECT parameter FROM Parameter parameter WHERE parameter.name LIKE %?1% AND parameter.deleted = false AND parameter.deleted_by IS NULL")
	List<Parameter> search(String key_search);

}
