package MobileShop.Validation;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import MobileShop.Common.EGender;
import MobileShop.Entity.City;
import MobileShop.Entity.District;
import MobileShop.Entity.Role;
import MobileShop.Entity.Ward;
import lombok.Data;

@Data
public class AccountCreateValidation {
	@NotNull(message = "Vui lòng nhập tên đăng nhập.")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "Vui lòng không nhập ký tự đặc biệt.")
	String username;
	
	@NotNull(message = "Vui lòng nhập họ và tên.")
	String fullname;
	
	@NotNull(message = "Vui lòng nhập mật khẩu.")
	String password;
	
	@NotNull(message = "Vui lòng nhập xác nhận mật khẩu.")
	String confirm_password;
	
	@NotNull(message = "Vui lòng nhập số điện thoại.")
	@Pattern(regexp = "[0-9]+", message = "Số điện thoại không hợp lệ.")
	@Length(min = 10, max = 11, message = "Số điện thoại phải từ 10 đến 11 số.")
	String phone;
	
	@NotNull(message = "Vui lòng nhập email.")
	@Email(message = "Vui lòng nhập đúng định dạng email.")
	String email;
	
	@NotNull(message = "Vui lòng nhập ngày sinh.")
	Date birthday;
	
	@NotNull(message = "Vui lòng chọn giới tính.")
	EGender gender;
	
	@NotNull(message = "Vui lòng nhập địa chỉ.")
	String address;
	
	String avatar;
	
	@NotNull(message = "Vui lòng chọn tỉnh/thành phố.")
	City city;
	
	@NotNull(message = "Vui lòng chọn quận/huyện.")
	District district;
	
	@NotNull(message = "Vui lòng chọn phường/xã.")
	Ward ward;
	
	@NotNull(message = "Vui lòng chọn vai trò.")
	@Size(min = 1, message = "Vui lòng chọn vai trò.")
	Set<Role> roles;
}
