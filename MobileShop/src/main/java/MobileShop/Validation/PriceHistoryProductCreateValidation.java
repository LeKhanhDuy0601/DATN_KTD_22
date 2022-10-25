package MobileShop.Validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class PriceHistoryProductCreateValidation {

	@NotNull(message = "ID sản phẩm không được để trống.")
	Integer id;
	
	@NotNull(message = "Vui lòng nhập giá sản phẩm.")
	@Pattern(regexp = "[0-9]+", message = "Giá sản phẩm không hợp lệ.")
	@Length(min = 1, message = "Giá sản phẩm phải từ 1 số.")
	String price;
	
}
