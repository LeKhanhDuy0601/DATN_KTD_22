package MobileShop.Entity;

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
@Table(name = "product_parameter_details")
public class ProductParameterDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id; 
	
	@ManyToOne
	@JoinColumn(name="product_parameter_id")
	ProductParameter product_parameter;
	
	@ManyToOne
	@JoinColumn(name="parameter_id")
	Parameter parameter; 
	
	String value;
}
