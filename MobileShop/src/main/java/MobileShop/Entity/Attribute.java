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
@Table(name = "attributes")
public class Attribute {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
Integer	id;

@ManyToOne
@JoinColumn(name = "attribute_group_id")
AttributeGroup	attribute_group;

String	name;
String	description;
String	value;
Boolean	activity;
Boolean	deleted;
Integer	deleted_by;

@JsonIgnore
@OneToMany(mappedBy = "attribute")
List<ProductAttribute> product_attributes;
public Attribute() {
	super();
}

public Attribute(String name, String description, String value, Boolean activity, Boolean deleted,
		AttributeGroup attribute_group) {
	super();
	this.name = name;
	this.description = description;
	this.value = value;
	this.activity = activity;
	this.deleted = deleted;
	this.attribute_group = attribute_group;
}

public Attribute(Integer id, String name, String value) {
	super();
	this.id = id;
	this.name = name;
	this.value = value;
}
}
