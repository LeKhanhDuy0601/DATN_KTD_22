package MobileShop.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MobileShop.Entity.Account;
import MobileShop.Validation.AccountBlockValidation;
import MobileShop.Validation.AccountCreateValidation;
import MobileShop.Validation.AccountDeleteValidation;
import MobileShop.Validation.AccountOpenValidation;
import MobileShop.Validation.AccountUpdateValidation;
import MobileShop.Validation.ForgotPasswordValidation;
import MobileShop.Validation.RegisterValidation;
import MobileShop.Validation.SigninValidation;
import MobileShop.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
	@Autowired
	AccountService accountService;

	
	
	@PostMapping()
	public ResponseEntity<?> createAccount(@RequestBody @Valid AccountCreateValidation accountCreateValidation) {
		return accountService.create(accountCreateValidation);
	}
	
	@GetMapping()
	public List<Account> findAll() {
		return accountService.findAll();
	}
	
	@PutMapping()
	public ResponseEntity<?> updateAccount(@RequestBody @Valid AccountUpdateValidation accountUpdateValidation){
		return accountService.update(accountUpdateValidation);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> deleteAccount(@RequestBody @Valid AccountDeleteValidation accountDeleteValidation){
		
		return accountService.delete(accountDeleteValidation);
	}
	
	@PutMapping("/block/{id}")
	public ResponseEntity<?> blockAccount(@RequestBody @Valid AccountBlockValidation accountBlockValidation){
		
		return accountService.block(accountBlockValidation);
	}
	
	@PutMapping("/open/{id}")
	public ResponseEntity<?> openAccount(@RequestBody @Valid AccountOpenValidation accountOpenValidation){
		
		return accountService.open(accountOpenValidation);
	}
	
	@GetMapping("/{search}")
	public List<Account> search(@PathVariable("search") String search){
		return accountService.search(search);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateAccount(@RequestBody @Valid SigninValidation signinValidation){
		return accountService.signin(signinValidation);
	}	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerAccount(@RequestBody @Valid RegisterValidation registerValidation){
		return accountService.register(registerValidation);
	}	
	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody @Valid ForgotPasswordValidation forgotPasswordValidation){
		return accountService.forgot(forgotPasswordValidation);
	}	
}
