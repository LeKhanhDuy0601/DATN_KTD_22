package MobileShop.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import MobileShop.Validation.VerificationValidation;

public interface VerificationService {

	ResponseEntity<?> sendVerificationCodeForgotPassword(@Valid VerificationValidation forgotPasswordValidation);

}
