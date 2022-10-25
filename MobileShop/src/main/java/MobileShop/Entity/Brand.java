package MobileShop.Entity;


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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "brands")
public class Brand {
@Id	
@GeneratedValue(strategy = GenerationType.IDENTITY)
Integer id;
String code;

String name;

String short_description;

String description;

String avatar;

Boolean activity;

Boolean deleted;

Integer deleted_by;
@JsonIgnore
@OneToMany(mappedBy = "brand")
List<Product> products;

public Brand(String code, String name, String short_description, String description, String avatar,
		Boolean activity, Boolean deleted) {
	super();
	this.code = code;
	this.name = name;
	this.short_description = short_description;
	this.description = description;
	this.avatar = avatar;
	this.activity = activity;
	this.deleted = deleted;
}
}
