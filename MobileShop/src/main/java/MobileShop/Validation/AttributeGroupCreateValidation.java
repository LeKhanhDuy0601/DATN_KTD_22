package MobileShop.Validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class AttributeGroupCreateValidation {
	
	
	@NotNull(message = "Vui lòng nhập tên nhóm thuộc tính.")
	String name;
	
	
	@NotNull(message = "Vui lòng nhập cấp bậc nhóm thuộc tính.")
	@Pattern(regexp = "[0-9]+", message = "Cấp bậc nhóm thuộc tính không hợp lệ.")
	@Length(min = 1, max = 10, message = "Cấp bậc nhóm thuộc tính phải từ 1 đến 10 số.")
	String level;
	
	@NotNull(message = "Vui lòng nhập mô tả nhóm thuộc tính.")
	String description;
	
	@NotNull(message = "Vui lòng chọn trạng thái cho nhóm thuộc tính.")
	Boolean activity;
}
