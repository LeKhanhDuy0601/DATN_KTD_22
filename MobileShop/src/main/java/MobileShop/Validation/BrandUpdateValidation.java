package MobileShop.Validation;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BrandUpdateValidation {
	@NotNull(message = "ID thương hiệu không được để trống.")
	Integer id;

	@NotNull(message = "Vui lòng nhập mã thương hiệu.")
	String code;
	
	@NotNull(message = "Vui lòng nhập tên thương hiệu.")
	String name;
	
	@NotNull(message = "Vui lòng nhập mô tả ngắn thương hiệu.")
	String short_description;
	
	@NotNull(message = "Vui lòng nhập mô tả thương hiệu.")
	String description;
	
	@NotNull(message = "Vui lòng chọn ảnh đại diện thương hiệu.")
	String avatar;
	
	@NotNull(message = "Vui lòng chọn trạng thái cho thương hiệu.")
	Boolean activity;
}
