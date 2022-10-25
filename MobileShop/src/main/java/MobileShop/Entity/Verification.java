package MobileShop.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "verifications")
public class Verification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String password;
	String code;
	Date expiry;
	Integer type;
	boolean activity;

	@ManyToOne
	@JoinColumn(name = "account_id")
	Account account;

	public Verification(String password, String code, Date expiry, Integer type, boolean activity, Account account) {
		super();
		this.password = password;
		this.code = code;
		this.expiry = expiry;
		this.type = type;
		this.activity = activity;
		this.account = account;
	}
	
	
}
