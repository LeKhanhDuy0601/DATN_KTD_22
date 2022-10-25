package MobileShop.Entity;

import java.util.Date;


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
@Table(name = "price_histories")
public class PriceHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	Integer price_old;
	
	Integer price;
	
	Date created_at;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	Product product;
	
	@ManyToOne
	@JoinColumn(name = "product_variant_id")
	ProductVariant product_variant;
	
	@ManyToOne
	@JoinColumn(name = "created_by")
	Account account;

	public PriceHistory() {
		super();
	}

	public PriceHistory(Integer price_old, Integer price, Date created_at, Product product,
			ProductVariant product_variant, Account account) {
		super();
		this.price_old = price_old;
		this.price = price;
		this.created_at = created_at;
		this.product = product;
		this.product_variant = product_variant;
		this.account = account;
	}
}
