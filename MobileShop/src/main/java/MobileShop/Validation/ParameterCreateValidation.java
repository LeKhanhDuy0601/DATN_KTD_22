package MobileShop.Validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import MobileShop.Entity.ParameterGroup;
import lombok.Data;

@Data
public class ParameterCreateValidation {

	@NotNull(message = "Vui lòng nhập tên thông số kỹ thuật.")
	String name;
	
	@NotNull(message = "Vui lòng nhập cấp bậc thông số kỹ thuật.")
	@Pattern(regexp = "[0-9]+", message = "Cấp bậc thông số kỹ thuật không hợp lệ.")
	@Length(min = 1, max = 10, message = "Cấp bậc thông số kỹ thuật phải từ 1 đến 10 số.")
	String level;
	
	@NotNull(message = "Vui lòng nhập mô tả thông số kỹ thuật.")
	String description;
	
	@NotNull(message = "Vui lòng chọn trạng thái cho thông số kỹ thuật.")
	Boolean activity;
	
	@NotNull(message = "Vui lòng chọn nhóm thông số.")
	ParameterGroup parameter_group;
	
}
