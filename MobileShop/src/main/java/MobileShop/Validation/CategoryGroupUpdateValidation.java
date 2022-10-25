package MobileShop.Validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class CategoryGroupUpdateValidation {
	@NotNull(message = "ID nhóm danh mục không được để trống.")
	Integer id;

	@NotNull(message = "Vui lòng nhập mã nhóm danh mục.")
	String code;
	
	@NotNull(message = "Vui lòng nhập tên nhóm danh mục.")
	String name;
	
	@NotNull(message = "Vui lòng nhập cấp bậc nhóm danh mục.")
	@Pattern(regexp = "[0-9]+", message = "Cấp bậc nhóm danh mục không hợp lệ.")
	@Length(min = 1, max = 10, message = "Cấp bậc nhóm danh mục phải từ 1 đến 10 số.")
	String level;
	
	@NotNull(message = "Vui lòng chọn icon cho nhóm danh mục.")
	String icon;
	
	@NotNull(message = "Vui lòng chọn trạng thái cho nhóm danh mục.")
	Boolean activity;
}
