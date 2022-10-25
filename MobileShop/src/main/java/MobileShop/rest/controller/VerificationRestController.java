package MobileShop.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MobileShop.service.VerificationService;
import MobileShop.Validation.VerificationValidation;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/tokens")
public class VerificationRestController {

	@Autowired
	VerificationService verificationService;
	
	@PostMapping()
	public ResponseEntity<?> sendVerificationCodeForgotPassword(@RequestBody @Valid VerificationValidation forgotPasswordValidation){
		return verificationService.sendVerificationCodeForgotPassword(forgotPasswordValidation);
	}
	
}