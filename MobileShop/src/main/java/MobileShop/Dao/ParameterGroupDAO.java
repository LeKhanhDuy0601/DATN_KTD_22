package MobileShop.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.ParameterGroup;

public interface ParameterGroupDAO extends JpaRepository<ParameterGroup, Integer>{

	@Query("SELECT gr FROM ParameterGroup gr WHERE gr.deleted = false AND gr.deleted_by IS NULL")
	List<ParameterGroup> findAllGroups();

}
