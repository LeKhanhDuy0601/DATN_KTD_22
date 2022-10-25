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
@Entity
@Table(name = "attribute_groups")
public class AttributeGroup {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
Integer	id;
String	name;
Integer	level;
String	description;
Boolean	activity;
Boolean	deleted;
Integer	deleted_by;

@JsonIgnore
@OneToMany(mappedBy = "attribute_group")
List<Attribute> attributes;
public AttributeGroup() {
	super();
}

public AttributeGroup( String name,  Integer level,String description, Boolean activity, Boolean deleted) {
	super();

	this.name = name;
	this.level = level;
	this.description = description;
	this.activity = activity;
	this.deleted = deleted;
}
}
