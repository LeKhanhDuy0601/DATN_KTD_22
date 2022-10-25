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

import MobileShop.Common.ESizeUnit;
import MobileShop.Common.EWeightUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table(name = "product_variants")
public class ProductVariant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	String name;
	
	Integer price;
	
	Integer qty;
	
	Integer weight;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	EWeightUnit weight_unit;
	
	Integer height;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	ESizeUnit height_unit;
	
	Integer width;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	ESizeUnit width_unit;
	
	Integer sold_count;
	
	String image;
	
	Boolean activity;
	
	Integer created_by;
	
	Date create_date;
	
	Boolean deleted;
	
	Integer deleted_by;
	
	@ManyToOne 
	@JoinColumn(name = "product_id")
	Product product;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "product_attributes", joinColumns = @JoinColumn(name = "product_variant_id"), 
			inverseJoinColumns = @JoinColumn(name = "attribute_id"))
	Set<Attribute> attributes = new HashSet<Attribute>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "product_variant_attribute", fetch = FetchType.LAZY)
	List<ProductAttribute> product_attributes;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product_variant", fetch = FetchType.LAZY)
	List<PriceHistory> price_histories;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product_variant")
	List<CartDetail> cart_details;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product_variant")
	List<ProductParameter> product_parameters;

	public ProductVariant() {
		super();
	}

	public ProductVariant(String name, Integer price, Integer qty, Integer weight, EWeightUnit weight_unit,
			Integer height, ESizeUnit height_unit, Integer width, ESizeUnit width_unit, Integer sold_count,
			String image, Boolean activity, Integer created_by, Date create_date, Boolean deleted, Integer deleted_by,
			Product product) {
		super();
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.weight = weight;
		this.weight_unit = weight_unit;
		this.height = height;
		this.height_unit = height_unit;
		this.width = width;
		this.width_unit = width_unit;
		this.sold_count = sold_count;
		this.image = image;
		this.activity = activity;
		this.created_by = created_by;
		this.create_date = create_date;
		this.deleted = deleted;
		this.deleted_by = deleted_by;
		this.product = product;
	}

}
