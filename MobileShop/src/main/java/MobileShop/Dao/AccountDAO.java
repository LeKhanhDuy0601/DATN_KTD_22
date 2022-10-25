package MobileShop.Dao;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.Account;

public interface AccountDAO extends JpaRepository<Account, Integer> {

	boolean existsByUsername(String username);

	boolean existsByPhone(String phone);

	boolean existsByEmail(String email);

	@Query("SELECT ac FROM Account ac where ac.username LIKE %?1%")
	List<Account> search(String search);

	Optional<Account> findByUsername(String username);
	
	@Query("SELECT ac FROM Account ac where ac.username =?1")
	Account findAccountByUsername(String username);
}
