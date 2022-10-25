package MobileShop.Validation;

import java.util.List;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import MobileShop.Common.ESizeUnit;
import MobileShop.Common.EWeightUnit;
import MobileShop.Entity.Attribute;
import MobileShop.Entity.Product;
import lombok.Data;

@Data
public class ProductVariantCreateValidation {
	
	@NotNull(message = "Vui lòng nhập tên biến thể.")
	String name;
	
	@NotNull(message = "Vui lòng nhập giá biến thể.")
	@Pattern(regexp = "[0-9]+", message = "Giá biến thể không hợp lệ.")
	@Length(min = 1, message = "Giá biến thể phải từ 1 số.")
	String price;
	
	@NotNull(message = "Vui lòng nhập trọng lượng biến thể.")
	@Pattern(regexp = "[0-9]+", message = "Trọng lượng biến thể không hợp lệ.")
	@Length(min = 1, message = "Trọng lượng biến thể phải từ 1 số.")
	String weight;
	
	@NotNull(message = "Vui lòng chọn đơn vị trọng lượng cho biến thể.")
	EWeightUnit weight_unit;
	
	@NotNull(message = "Vui lòng nhập chiều cao biến thể.")
	@Pattern(regexp = "[0-9]+", message = "Chiều cao biến thể không hợp lệ.")
	@Length(min = 1, message = "Chiều cao biến thể phải từ 1 số.")
	String height;
	
	@NotNull(message = "Vui lòng chọn đơn vị chiều cao cho biến thể.")
	ESizeUnit height_unit;
	
	@NotNull(message = "Vui lòng nhập chiều ngang biến thể.")
	@Pattern(regexp = "[0-9]+", message = "Chiều ngang biến thể không hợp lệ.")
	@Length(min = 1, message = "Chiều ngang biến thể phải từ 1 số.")
	String width;
	
	@NotNull(message = "Vui lòng chọn đơn vị chiều ngang cho biến thể.")
	ESizeUnit width_unit;
	
	@NotNull(message = "Vui lòng chọn hình ảnh cho biến thể.")
	String image;
	
	@NotNull(message = "Vui lòng chọn trạng thái cho biến thể.")
	Boolean activity;
	
	@NotNull(message = "Vui lòng chọn sản phẩm để tạo biến thể.")
	Product product;
	
	@NotNull(message = "Vui lòng chọn thuộc tính cho biến thể.")
	@Size(min = 1, message = "Vui lòng chọn thuộc tính cho biến thể.")
	List<Attribute> attributes;
	
}
