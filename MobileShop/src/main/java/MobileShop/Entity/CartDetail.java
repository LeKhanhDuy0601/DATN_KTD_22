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
@Table(name = "cart_details")
public class CartDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	Integer qty;
	
	Integer price;
	
	Integer total;
	
	@ManyToOne 
	@JoinColumn(name = "cart_id")
	Cart cart;
	
	@ManyToOne 
	@JoinColumn(name = "product_id")
	Product product;
	
	@ManyToOne 
	@JoinColumn(name = "product_variant_id")
	ProductVariant product_variant;
	
	public CartDetail() {
		super();
	}

	public CartDetail(Integer qty, Integer price, Integer total, Cart cart, Product product,
			ProductVariant product_variant) {
		super();
		this.qty = qty;
		this.price = price;
		this.total = total;
		this.cart = cart;
		this.product = product;
		this.product_variant = product_variant;
	}
}
