package MobileShop.Validation;

import java.util.List;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import MobileShop.Common.ESizeUnit;
import MobileShop.Common.ETypeProduct;
import MobileShop.Common.EWeightUnit;
import MobileShop.Entity.Brand;
import MobileShop.Entity.Category;
import MobileShop.Entity.ProductImage;
import MobileShop.Entity.Unit;
import lombok.Data;

@Data
public class ProductCreateValidation {

	@NotNull(message = "Vui lòng nhập mã sản phẩm.")
	String code;
	
	@NotNull(message = "Vui lòng nhập tên sản phẩm.")
	String name;
	
	@NotNull(message = "Vui lòng chọn loại cho sản phẩm.")
	ETypeProduct type;
	
	
	@NotNull(message = "Vui lòng nhập giá sản phẩm.")
	@Pattern(regexp = "[0-9]+", message = "Giá sản phẩm không hợp lệ.")
	@Length(min = 1, message = "Giá sản phẩm phải từ 1 số.")
	String price;
	
	@NotNull(message = "Vui lòng nhập mô tả ngắn sản phẩm.")
	String short_description;
	
	@NotNull(message = "Vui lòng nhập mô tả sản phẩm.")
	String description;
	
	@NotNull(message = "Vui lòng nhập trọng lượng sản phẩm.")
	@Pattern(regexp = "[0-9]+", message = "Trọng lượng sản phẩm không hợp lệ.")
	@Length(min = 1, message = "Trọng lượng sản phẩm phải từ 1 số.")
	String weight;
	
	@NotNull(message = "Vui lòng chọn đơn vị trọng lượng cho sản phẩm.")
	EWeightUnit weight_unit;
	
	@NotNull(message = "Vui lòng nhập chiều cao sản phẩm.")
	@Pattern(regexp = "[0-9]+", message = "Chiều cao sản phẩm không hợp lệ.")
	@Length(min = 1, message = "Chiều cao sản phẩm phải từ 1 số.")
	String height;
	
	@NotNull(message = "Vui lòng chọn đơn vị chiều cao cho sản phẩm.")
	ESizeUnit height_unit;
	
	@NotNull(message = "Vui lòng nhập chiều ngang sản phẩm.")
	@Pattern(regexp = "[0-9]+", message = "Chiều ngang sản phẩm không hợp lệ.")
	@Length(min = 1, message = "Chiều ngang sản phẩm phải từ 1 số.")
	String width;
	
	@NotNull(message = "Vui lòng chọn đơn vị chiều ngang cho sản phẩm.")
	ESizeUnit width_unit;
	
	@NotNull(message = "Vui lòng nhập thời gian bảo hành sản phẩm.")
	@Pattern(regexp = "[0-9]+", message = "Thời gian bảo hành sản phẩm không hợp lệ.")
	@Length(min = 1, message = "Thời gian bảo hành sản phẩm phải từ 1 số.")
	String warranty_period;
	
	@NotNull(message = "Vui lòng chọn trạng thái cho sản phẩm.")
	Boolean activity;
	
	@NotNull(message = "Vui lòng chọn thương hiệu cho sản phẩm.")
	Brand brand;
	
	@NotNull(message = "Vui lòng chọn đơn vị tính cho sản phẩm.")
	Unit unit;
	
	@NotNull(message = "Vui lòng chọn danh mục sản phẩm.")
	@Size(min = 1, message = "Vui lòng chọn danh mục sản phẩm.")
	Set<Category> categories;
	
	@NotNull(message = "Vui lòng chọn hình ảnh sản phẩm.")
	@Size(min = 1, message = "Vui lòng chọn hình ảnh sản phẩm.")
	List<ProductImage> images;
	
}
