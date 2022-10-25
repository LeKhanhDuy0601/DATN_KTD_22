package MobileShop.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import MobileShop.Entity.Account;
import MobileShop.Validation.AccountBlockValidation;
import MobileShop.Validation.AccountCreateValidation;
import MobileShop.Validation.AccountDeleteValidation;
import MobileShop.Validation.AccountOpenValidation;
import MobileShop.Validation.AccountUpdateValidation;
import MobileShop.Validation.ForgotPasswordValidation;
import MobileShop.Validation.RegisterValidation;
import MobileShop.Validation.SigninValidation;

public interface AccountService {

	ResponseEntity<?> create(@Valid AccountCreateValidation accountCreateValidation);

	List<Account> findAll();

	ResponseEntity<?> update(@Valid AccountUpdateValidation accountUpdateValidation);

	ResponseEntity<?> delete(@Valid AccountDeleteValidation accountDeleteValidation);

	ResponseEntity<?> block(@Valid AccountBlockValidation accountBlockValidation);

	ResponseEntity<?> open(@Valid AccountOpenValidation accountOpenValidation);

	List<Account> search(String search);

	ResponseEntity<?> signin(@Valid SigninValidation signinValidation);

	ResponseEntity<?> register(@Valid RegisterValidation registerValidation);

	ResponseEntity<?> forgot(@Valid ForgotPasswordValidation forgotPasswordValidation);

}
