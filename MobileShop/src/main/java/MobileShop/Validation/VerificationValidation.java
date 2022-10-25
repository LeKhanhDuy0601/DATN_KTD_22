package MobileShop.Validation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationValidation {
	@NotNull(message = "Vui lòng nhập tên đăng nhập.")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "Vui lòng không nhập ký tự đặc biệt.")
	private String username;
	
	@NotNull(message = "Vui lòng nhập email.")
	@Email(message = "Vui lòng nhập đúng định dạng email.")
	private String email;
}
