package MobileShop.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import MobileShop.Common.ERole;
import MobileShop.Entity.Role;

public interface RoleDAO extends JpaRepository<Role, Integer> {
	Optional<Role> findByCode(ERole role);
}
