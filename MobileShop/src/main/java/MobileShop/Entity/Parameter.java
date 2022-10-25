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
@Table(name = "parameters")
public class Parameter {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer	id;
	
	String	name ;
	Integer	level; 
	String	description;
	Boolean	activity;
	Boolean	deleted;
	Integer	deleted_by;
	
	@ManyToOne
	@JoinColumn(name="parameter_group_id")
	ParameterGroup	parameter_group;
	
	@JsonIgnore
	@OneToMany(mappedBy = "parameter")
	List<ProductParameterDetail> parameter_details;

	public Parameter() {
		super();
	}

	public Parameter(String name, Integer level, String description, Boolean activity, Boolean deleted,
			Integer deleted_by, ParameterGroup parameter_group) {
		super();
		this.name = name;
		this.level = level;
		this.description = description;
		this.activity = activity;
		this.deleted = deleted;
		this.deleted_by = deleted_by;
		this.parameter_group = parameter_group;
	}

}
