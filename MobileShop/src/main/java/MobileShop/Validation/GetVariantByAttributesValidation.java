package MobileShop.Validation;

import java.util.List;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import MobileShop.Dto.AttributeRequest;
import lombok.Data;

@Data
public class GetVariantByAttributesValidation {

	@NotNull(message = "ID sản phẩm không được để trống.")
	Integer product_id;
	
	@NotNull(message = "Vui lòng chọn thuộc tính sản phẩm.")
	@Size(min = 1, message = "Vui lòng chọn thuộc tính sản phẩm.")
	List<AttributeRequest> attributes;
	
}
