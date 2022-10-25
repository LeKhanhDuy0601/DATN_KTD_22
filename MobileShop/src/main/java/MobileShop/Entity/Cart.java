package MobileShop.Entity;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "carts")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	String fullname;
	
	String phone;
	
	String email;
	
	String address;
	
	String description;
	
	Integer ship_fee;
	
	@ManyToOne 
	@JoinColumn(name = "account_id")
	Account account;
	
	@ManyToOne 
	@JoinColumn(name = "session_id")
	Session session;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	City city;
	
	@ManyToOne
	@JoinColumn(name = "district_id")
	District district;
	
	@ManyToOne
	@JoinColumn(name = "ward_id")
	Ward ward;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cart")
	List<CartDetail> cart_details;

	public Cart() {
		super();
	}

	public Cart(Session session) {
		super();
		this.session = session;
	}

	public Cart(Account account, Session session) {
		super();
		this.account = account;
		this.session = session;
	}
	
}
