package MobileShop.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.Attribute;

public interface AttributeDAO extends JpaRepository<Attribute, Integer>{
	@Query("SELECT ab FROM Attribute ab WHERE ab.deleted = false AND ab.deleted_by IS NULL")
	List<Attribute> findAllAttributes();

	@Query("SELECT ab FROM Attribute ab WHERE ab.name LIKE %?1% AND ab.deleted = false AND ab.deleted_by IS NULL")
	List<Attribute> search(String key_search);

	@Query("SELECT ab FROM Attribute ab WHERE ab.attribute_group.id =?1 AND ab.deleted = false AND ab.deleted_by IS NULL")
	List<Attribute> findAttributesByAttributeGroup(Integer group_id);
}
