package MobileShop.Validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class PriceHistoryVariantCreateValidation {

	@NotNull(message = "ID biến thể không được để trống.")
	Integer id;
	
	@NotNull(message = "Vui lòng nhập giá biến thể.")
	@Pattern(regexp = "[0-9]+", message = "Giá biến thể không hợp lệ.")
	@Length(min = 1, message = "Giá biến thể phải từ 1 số.")
	String price;
	
}
