package MobileShop.Dao;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import MobileShop.Entity.Verification;

public interface VerificationDAO extends JpaRepository<Verification, Integer>{

	@Query("SELECT verifi FROM Verification verifi WHERE verifi.account.id=?1 AND verifi.type =?2 AND verifi.activity=?3")
	Verification findVerificationByAccountId(Integer id, Integer type, boolean activity);

	@Query("SELECT verifi FROM Verification verifi WHERE verifi.account.id=?1 AND verifi.type =?2 AND verifi.activity=?3")
	List<Verification> findByAccountId(Integer id, Integer type, boolean activity);
}
