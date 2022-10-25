package MobileShop.Validation;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class AccountBlockValidation {
	@NotNull(message = "Mã tài khoản không được để trống!")
	Integer id;
	
	@NotNull(message = "Xác nhận việc xóa tài khoản không được để trống!")
	@AssertTrue(message = "Vui lòng xác nhận rằng bạn chắc chắn việc xóa tài khoản này!")
	Boolean confirm;
}
