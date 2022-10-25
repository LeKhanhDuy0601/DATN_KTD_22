package MobileShop.Validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class ParameterGroupCreateValidation {

	@NotNull(message = "Vui lòng nhập tên nhóm thông số.")
	String name;
	
	@NotNull(message = "Vui lòng nhập cấp bậc nhóm thông số.")
	@Pattern(regexp = "[0-9]+", message = "Cấp bậc nhóm thông số không hợp lệ.")
	@Length(min = 1, max = 10, message = "Cấp bậc nhóm thông số phải từ 1 đến 10 số.")
	String level;
	
	@NotNull(message = "Vui lòng nhập mô tả nhóm thông số.")
	String description;
	
	@NotNull(message = "Vui lòng chọn trạng thái cho nhóm thông số.")
	Boolean activity;
	
}
