package MobileShop.Validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import MobileShop.Entity.CategoryGroup;
import lombok.Data;

@Data
public class CategoryUpdateValidation {
	@NotNull(message = "ID nhóm danh mục không được để trống.")
	Integer id;

	@NotNull(message = "Vui lòng nhập mã danh mục.")
	String code;
	
	@NotNull(message = "Vui lòng nhập tên danh mục.")
	String name;
	
	@NotNull(message = "Vui lòng nhập cấp bậc danh mục.")
	@Pattern(regexp = "[0-9]+", message = "Cấp bậc danh mục không hợp lệ.")
	@Length(min = 1, max = 10, message = "Cấp bậc danh mục phải từ 1 đến 10 số.")
	String level;
	
	@NotNull(message = "Vui lòng chọn hiển thị cho danh mục.")
	Boolean view;
	
	@NotNull(message = "Vui lòng chọn nhóm danh mục.")
	CategoryGroup categoryGroup;
	
	@NotNull(message = "Vui lòng chọn trạng thái cho danh mục.")
	Boolean activity;
}
