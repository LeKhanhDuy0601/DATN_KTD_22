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
@Table(name = "category_groups")
public class CategoryGroup {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
Integer	id;
String	code;
String	name;
Integer	level;
String	icon;
Boolean	activity;
Boolean	deleted;
Integer	deleted_by;

@JsonIgnore
@OneToMany(mappedBy = "categoryGroup")
List<Category> categories;

public CategoryGroup(String code, String name, Integer level, String icon, Boolean activity, Boolean deleted) {
	super();
	this.code = code;
	this.name = name;
	this.level = level;
	this.icon = icon;
	this.activity = activity;
	this.deleted = deleted;
}



}
