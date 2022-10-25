package MobileShop.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import MobileShop.Common.BodySendMail;
import MobileShop.Dao.AccountDAO;
import MobileShop.Dao.VerificationDAO;
import MobileShop.Entity.Account;
import MobileShop.Entity.Verification;
import MobileShop.Message.ResponseMessageAccount;
import MobileShop.Message.ResponseMessageError;
import MobileShop.Validation.VerificationValidation;
import MobileShop.service.MailerService;
import MobileShop.service.VerificationService;
@Service
public class VerificationServiceImpl implements VerificationService{
	@Autowired
	VerificationDAO verificationDAO;
	
	@Autowired
	AccountDAO accountDAO;
	
	@Autowired
	MailerService mailerService;
	
	@Autowired
	BodySendMail bodySendMail;

	@Override
	public ResponseEntity<?> sendVerificationCodeForgotPassword(
			@Valid VerificationValidation forgotPasswordValidation) {
		Account account = accountDAO.findAccountByUsername(forgotPasswordValidation.getUsername());
		if(account == null) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Tên đăng nhập không tồn tại!", "username"));
		}
		if(!account.getEmail().equals(forgotPasswordValidation.getEmail())) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Email không chính xác!", "email"));
		}
		String AlphaNumericStringCode = "0123456789";
		String AlphaNumericStringPass = 
				"ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
		StringBuilder stringBuilderCode 		= new StringBuilder(6);
		StringBuilder stringBuilderPassword 	= new StringBuilder(8);
		for (int i = 0; i < 8; i++) {
			if(i < 6) {
				int indexCode = (int)(AlphaNumericStringCode.length() * Math.random());		  
	            stringBuilderCode.append(AlphaNumericStringCode.charAt(indexCode));
			}
            int indexPass = (int)(AlphaNumericStringPass.length() * Math.random());		  
            stringBuilderPassword.append(AlphaNumericStringPass.charAt(indexPass));
        }
		String code = stringBuilderCode.toString();
		String password = stringBuilderPassword.toString();
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		List<Verification> verifications = verificationDAO.findByAccountId(account.getId(), 1, true);
		verifications.forEach(verification -> {
			verification.setActivity(false);
			verificationDAO.save(verification);
		});
		Verification verification = new Verification(password, code, expiry.getTime(), 1, true, account);
		verificationDAO.save(verification);
		try {
			mailerService.send(account.getEmail(), "Mã xác nhận tài khoản Sneat", bodySendMail.sendMailVerification(account.getFullname(), code));
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(new ResponseMessageAccount("Kiểm tra email để nhận mã xác nhận!", account));
		
	}
}
