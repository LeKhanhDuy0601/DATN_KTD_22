package MobileShop.Validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import MobileShop.Entity.Account;
import lombok.Data;

@Data
public class ForgotPasswordValidation {
	@NotNull(message = "Vui lòng nhập mã xác nhận.")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "Vui lòng không nhập ký tự đặc biệt.")
	String code;
	
	@NotNull(message = "Loại xác thực không được để trống!")
	Integer type;
	
	@NotNull(message = "Tài khoản không được để trống.")
	Account account;
}
