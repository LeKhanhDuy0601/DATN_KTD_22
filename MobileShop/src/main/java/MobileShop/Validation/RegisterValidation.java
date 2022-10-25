package MobileShop.Validation;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterValidation {
	@NotNull(message = "Vui lòng nhập tên đăng nhập.")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "Vui lòng không nhập ký tự đặc biệt.")
	private String username;
	
	@NotNull(message = "Vui lòng nhập họ và tên.")
	private String fullname;
	
	@NotNull(message = "Vui lòng nhập email.")
	@Email(message = "Vui lòng nhập đúng định dạng email.")
	private String email;
	
	@NotNull(message = "Vui lòng nhập mật khẩu.")
	private String password;
	
	@NotNull(message = "Vui lòng nhập xác nhận mật khẩu.")
	private String confirm_password;
	
	private Set<String> roles;
}
