package MobileShop.Validation;

import javax.validation.constraints.NotNull;

import MobileShop.Entity.AttributeGroup;
import lombok.Data;

@Data
public class AttributeUpdateValidation {
	@NotNull(message = "ID thuộc tính không được để trống.")
	Integer id;
	
	@NotNull(message = "Vui lòng nhập tên nhóm thuộc tính.")
	String name;
	
	@NotNull(message = "Vui lòng nhập mô tả nhóm thuộc tính.")
	String description;
	
	@NotNull(message = "Vui lòng nhập giá trị cho thuộc tính.")
	String value;
	
	@NotNull(message = "Vui lòng chọn trạng thái cho nhóm thuộc tính.")
	Boolean activity;
	
	@NotNull(message = "Vui lòng chọn nhóm thuộc tính.")
	AttributeGroup attribute_group;
}
