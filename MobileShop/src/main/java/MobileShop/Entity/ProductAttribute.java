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
@Entity
@Table(name = "product_attributes")
public class ProductAttribute {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	Integer level;
	
	@ManyToOne 
	@JoinColumn(name = "product_variant_id")
	ProductVariant product_variant_attribute;
	
	@ManyToOne 
	@JoinColumn(name = "attribute_id")
	Attribute attribute;
	
	public ProductAttribute(Integer level, ProductVariant product_variant_attribute, Attribute attribute) {
		super();
		this.level = level;
		this.product_variant_attribute = product_variant_attribute;
		this.attribute = attribute;
	}

	public ProductAttribute() {
		super();
	}
}
