package MobileShop.Validation;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SigninValidation {
	@NotNull(message = "Vui lòng nhập tên đăng nhập.")
	private String username;
	
	@NotNull(message = "Vui lòng nhập mật khẩu.")
	private String password;
}
