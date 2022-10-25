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
import MobileShop.Common.ETypeProduct;
import MobileShop.Common.EWeightUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	String code;
	
	String name;
	
	String slug;
	
	Integer qty;
	
	Integer price;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	ETypeProduct type;
	
	String short_description;
	
	String description;
	
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
	
	Integer warranty_period;
	
	Boolean activity;
	
	Integer created_by;
	
	Date created_date;
	
	Boolean deleted;
	
	Integer deleted_by;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "unit_id")
	Unit unit;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "product_categories", joinColumns = @JoinColumn(name = "product_id"), 
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	Set<Category> categories = new HashSet<Category>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<ProductCategory> product_categories;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	List<ProductImage> product_images;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	List<ProductVariant> product_variants;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	List<PriceHistory> product_histories;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<CartDetail> cart_details;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<ProductParameter> product_parameters;

	public Product(String code, String name, String slug, Integer qty, Integer price, ETypeProduct type,
			String short_description, String description, Integer weight, EWeightUnit weight_unit, Integer height,
			ESizeUnit height_unit, Integer width, ESizeUnit width_unit, Integer sold_count, Integer warranty_period,
			Boolean activity, Integer created_by, Date created_date, Boolean deleted, Integer deleted_by, Brand brand,
			Unit unit, Set<Category> categories) {
		super();
		this.code = code;
		this.name = name;
		this.slug = slug;
		this.qty = qty;
		this.price = price;
		this.type = type;
		this.short_description = short_description;
		this.description = description;
		this.weight = weight;
		this.weight_unit = weight_unit;
		this.height = height;
		this.height_unit = height_unit;
		this.width = width;
		this.width_unit = width_unit;
		this.sold_count = sold_count;
		this.warranty_period = warranty_period;
		this.activity = activity;
		this.created_by = created_by;
		this.created_date = created_date;
		this.deleted = deleted;
		this.deleted_by = deleted_by;
		this.brand = brand;
		this.unit = unit;
		this.categories = categories;
	}



	public Product() {
		super();
	}
}
