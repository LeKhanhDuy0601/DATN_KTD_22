package MobileShop.Entity;

import java.util.Date;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import MobileShop.Common.EGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String username;
	String fullname;
	String password;
	String phone;
	String email;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	EGender gender;
	Date birthday;
	Boolean activity;
	String address;
	String avatar;
	
	Integer deleted_by;
	Boolean deleted;

	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Authority> authorities;

	@ManyToOne
	@JoinColumn(name = "city_id")
	City city;

	@ManyToOne
	@JoinColumn(name = "district_id")
	District district;

	@ManyToOne
	@JoinColumn(name = "ward_id")
	Ward ward;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<Role>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Verification> verification;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<PriceHistory> price_histories;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Cart> carts;

	public Account(String username, String fullname, String password, String phone, String email, Date birthday,
			EGender gender, String address, Boolean activity, String avatar, Boolean deleted, City city,
			District district, Ward ward, Set<Role> roles) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
		this.activity = activity;
		this.avatar = avatar;
		this.deleted = deleted;
		this.city = city;
		this.district = district;
		this.ward = ward;
		this.roles = roles;
		
	}

	public Account() {
		super();
	}

	public Account(String username, String fullname, String password, String email, Boolean activity, Boolean deleted,
			Set<Role> roles) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.email = email;
		this.activity = activity;
		this.deleted = deleted;
		this.roles = roles;
	}

}
