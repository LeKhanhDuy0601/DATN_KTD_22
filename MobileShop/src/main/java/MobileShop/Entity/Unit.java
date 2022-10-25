package MobileShop.Entity;

import java.util.Date;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table(name = "units")
public class Unit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "unit")
	List<Product> products;

	public Unit() {
		super();
	}

	public Unit(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
