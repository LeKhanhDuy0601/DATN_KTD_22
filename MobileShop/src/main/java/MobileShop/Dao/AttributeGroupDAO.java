package MobileShop.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.AttributeGroup;

public interface AttributeGroupDAO extends JpaRepository<AttributeGroup, Integer>{

	
	@Query("SELECT gr FROM AttributeGroup gr WHERE gr.deleted = false AND gr.deleted_by IS NULL")
	List<AttributeGroup> findAllAttributeGroups();
	
	@Query("SELECT gr FROM AttributeGroup gr WHERE gr.name LIKE %?1% AND gr.deleted = false AND gr.deleted_by IS NULL")
	List<AttributeGroup> search(String key_search);
}
