package MobileShop.service.impl;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import MobileShop.Common.BodySendMail;
import MobileShop.Common.ERole;
import MobileShop.Common.JwtUtils;
import MobileShop.Dao.AccountDAO;
import MobileShop.Dao.RoleDAO;
import MobileShop.Dao.VerificationDAO;
import MobileShop.Dto.JwtResponse;
import MobileShop.Entity.Account;
import MobileShop.Entity.Role;
import MobileShop.Entity.Verification;
import MobileShop.Message.ResponseMessage;
import MobileShop.Message.ResponseMessageAccount;
import MobileShop.Message.ResponseMessageError;
import MobileShop.Validation.AccountBlockValidation;
import MobileShop.Validation.AccountCreateValidation;
import MobileShop.Validation.AccountDeleteValidation;
import MobileShop.Validation.AccountOpenValidation;
import MobileShop.Validation.AccountUpdateValidation;
import MobileShop.Validation.ForgotPasswordValidation;
import MobileShop.Validation.RegisterValidation;
import MobileShop.Validation.SigninValidation;
import MobileShop.service.AccountService;
import MobileShop.service.MailerService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO accountDAO;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	RoleDAO roleDAO;
	@Autowired
	VerificationDAO verificationDAO;
	@Autowired
	MailerService mailerService;
	@Autowired
	BodySendMail bodySendMail;
	
	@Override
	public ResponseEntity<?> create(@Valid AccountCreateValidation accountCreateValidation) {
		if (accountDAO.existsByUsername(accountCreateValidation.getUsername())) {
			return ResponseEntity.badRequest()
					.body(new ResponseMessageError("T??n ????ng nh???p ???? ???????c s??? d???ng!", "username"));
		}
		if (accountDAO.existsByPhone(accountCreateValidation.getPhone())) {
			return ResponseEntity.badRequest()
					.body(new ResponseMessageError("S??? ??i???n tho???i ???? ???????c s??? d???ng!", "phone"));
		}
		if (accountDAO.existsByEmail(accountCreateValidation.getEmail())) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Email ???? ???????c s??? d???ng!", "email"));
		}
		if (!accountCreateValidation.getPassword().equals(accountCreateValidation.getConfirm_password())) {
			return ResponseEntity.badRequest()
					.body(new ResponseMessageError("X??c nh???n m???t kh???u kh??ng ch??nh x??c!", "confirm_password"));
		}
		Account account = new Account(accountCreateValidation.getUsername(), accountCreateValidation.getFullname(),
				encoder.encode(accountCreateValidation.getPassword()), accountCreateValidation.getPhone(),
				accountCreateValidation.getEmail(), accountCreateValidation.getBirthday(),
				accountCreateValidation.getGender(), accountCreateValidation.getAddress(), true,
				accountCreateValidation.getAvatar(), false, accountCreateValidation.getCity(),
				accountCreateValidation.getDistrict(), accountCreateValidation.getWard(),
				accountCreateValidation.getRoles());
		accountDAO.save(account);
		return ResponseEntity.ok(new ResponseMessageAccount("T??i kho???n ???? ????ng k?? th??nh c??ng!", account));
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountDAO.findAll();
	}

	@Override
	public ResponseEntity<?> update(@Valid AccountUpdateValidation accountUpdateValidation) {
		Account account = accountDAO.findById(accountUpdateValidation.getId()).get();
		if (account == null || account.getDeleted_by() != null || account.getDeleted() == true) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("T??i kho???n kh??ng t???n t???i!", "account"));
		}
		if (!account.getPhone().equals(accountUpdateValidation.getPhone())) {
			if (accountDAO.existsByPhone(accountUpdateValidation.getPhone())) {
				return ResponseEntity.badRequest()
						.body(new ResponseMessageError("S??? ??i???n tho???i ???? ???????c s??? d???ng!", "phone"));
			}
			account.setPhone(accountUpdateValidation.getPhone());
		}
		if (!account.getEmail().equals(accountUpdateValidation.getEmail())) {
			if (accountDAO.existsByEmail(accountUpdateValidation.getEmail())) {
				return ResponseEntity.badRequest().body(new ResponseMessageError("Email ???? ???????c s??? d???ng!", "email"));
			}
			account.setEmail(accountUpdateValidation.getEmail());
		}
		if (!accountUpdateValidation.getPassword().isEmpty()) {
			if (!account.getPassword().equals(accountUpdateValidation.getPassword())) {
				if (accountUpdateValidation.getConfirm_password().isEmpty()) {
					return ResponseEntity.badRequest()
							.body(new ResponseMessageError("Vui l??ng nh???p x??c nh???n m???t kh???u!", "confirm_password"));
				}
				if (!accountUpdateValidation.getPassword().equals(accountUpdateValidation.getConfirm_password())) {
					return ResponseEntity.badRequest()
							.body(new ResponseMessageError("X??c nh???n m???t kh???u kh??ng ch??nh x??c!", "confirm_password"));
				}
				account.setPassword(accountUpdateValidation.getPassword());
			}
		}
		account.setFullname(accountUpdateValidation.getFullname());
		account.setBirthday(accountUpdateValidation.getBirthday());
		account.setGender(accountUpdateValidation.getGender());
		account.setAddress(accountUpdateValidation.getAddress());
		if (!accountUpdateValidation.getAvatar().isEmpty()) {
			account.setAvatar(accountUpdateValidation.getAvatar());
		}
		account.setCity(accountUpdateValidation.getCity());
		account.setDistrict(accountUpdateValidation.getDistrict());
		account.setWard(accountUpdateValidation.getWard());
		accountDAO.save(account);
		return ResponseEntity.ok(new ResponseMessageAccount("T??i kho???n ???? c???p nh???t th??nh c??ng!", account));
	}

	@Override
	public ResponseEntity<?> delete(@Valid AccountDeleteValidation accountDeleteValidation) {

		Account account = accountDAO.findById(accountDeleteValidation.getId()).get();
		if (account == null) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("T??i kho???n kh??ng t???n t???i!", "account"));
		}
		account.setDeleted(true);
//		account.setDeleted_by(null);
		accountDAO.save(account);
		return ResponseEntity.ok(new ResponseMessageAccount("X??a t??i kho???n th??nh c??ng!", account));
	}

	@Override
	public ResponseEntity<?> block(@Valid AccountBlockValidation accountBlockValidation) {
		Account account = accountDAO.findById(accountBlockValidation.getId()).get();
		if (account == null) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("T??i kho???n kh??ng t???n t???i!", "account"));
		}
		account.setActivity(false);
		accountDAO.save(account);
		return ResponseEntity.ok(new ResponseMessageAccount("T??i kho???n ???? b??? kh??a!", account));
	}

	@Override
	public ResponseEntity<?> open(@Valid AccountOpenValidation accountOpenValidation) {
		Account account = accountDAO.findById(accountOpenValidation.getId()).get();
		if (account == null) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("T??i kho???n kh??ng t???n t???i!", "account"));
		}
		account.setActivity(true);
		accountDAO.save(account);
		return ResponseEntity.ok(new ResponseMessageAccount("T??i kho???n ???? ???????c m??? kh??a!", account));
	}

	@Override
	public List<Account> search(String search) {
		if (search.equals("null")) {
			return accountDAO.findAll();
		}
		return accountDAO.search(search);
	}

	@Override
	public ResponseEntity<?> signin(@Valid SigninValidation signinValidation) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				signinValidation.getUsername(), signinValidation.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		AccountDetailsImpl accountDetails = (AccountDetailsImpl) authentication.getPrincipal();
		List<String> roles = accountDetails.getAuthorities().stream().map(account -> account.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, roles));
	}

	@Override
	public ResponseEntity<?> register(@Valid RegisterValidation registerValidation) {
		if(accountDAO.existsByUsername(registerValidation.getUsername())) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("T??n ????ng nh???p ???? ???????c s??? d???ng!", "username"));
		}
		if(accountDAO.existsByEmail(registerValidation.getEmail())) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("Email ???? ???????c s??? d???ng!", "email"));
		}
		if(!registerValidation.getPassword().equals(registerValidation.getConfirm_password())) {
			return ResponseEntity.badRequest().body(new ResponseMessageError("X??c nh???n m???t kh???u kh??ng ch??nh x??c!", "confirm_password"));
		}
		Set<String> strRoles = registerValidation.getRoles();
		Set<Role> roles = new HashSet<Role>();
		if(strRoles == null) {
			Role accountRole = roleDAO.findByCode(ERole.ROLE_GUEST).orElseThrow(() -> new RuntimeException(
					"Error: Vai tr?? kh??ng t???n t???i."));
			roles.add(accountRole);
		} else {
			strRoles.forEach(role -> {
				switch(role) {
				case "admin":
					Role adminRole = roleDAO.findByCode(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Vai tr?? kh??ng t???n t???i."));
					roles.add(adminRole);
					break;
				case "manager":
					Role managerRole = roleDAO.findByCode(ERole.ROLE_MANAGER)
					.orElseThrow(() -> new RuntimeException("Error: Vai tr?? kh??ng t???n t???i."));
					roles.add(managerRole);
					break;
				case "employee":
					Role employeeRole = roleDAO.findByCode(ERole.ROLE_EMPLOYEE)
					.orElseThrow(() -> new RuntimeException("Error: Vai tr?? kh??ng t???n t???i."));
					roles.add(employeeRole);
					break;
				default:
					Role guestRole = roleDAO.findByCode(ERole.ROLE_GUEST)
					.orElseThrow(() -> new RuntimeException("Error: Vai tr?? kh??ng t???n t???i."));
					roles.add(guestRole);
				}
			});
		}
		Account account = new Account(
				registerValidation.getUsername(), 
				registerValidation.getFullname(),
				encoder.encode(registerValidation.getPassword()),
				registerValidation.getEmail(),
				true,
				false,
				roles);
		accountDAO.save(account);
		return ResponseEntity.ok(new ResponseMessageAccount("????ng k?? t??i kho???n th??nh c??ng!", account));
	}

	@Override
	public ResponseEntity<?> forgot(@Valid ForgotPasswordValidation forgotPasswordValidation) {
		Verification verification = verificationDAO.findVerificationByAccountId(forgotPasswordValidation.getAccount().getId(), forgotPasswordValidation.getType(), true);
		if(verification == null) {
			return ResponseEntity.badRequest().body(new ResponseMessage("T??i kho???n kh??ng t???n t???i!"));
		}
		if(!verification.getCode().equals(forgotPasswordValidation.getCode())) {
			return ResponseEntity.badRequest().body(new ResponseMessage("M?? x??c th???c kh??ng ch??nh x??c!"));
		}
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		if((verification.getExpiry().getTime()+900000) < expiry.getTime().getTime()) {
			return ResponseEntity.badRequest().body(new ResponseMessage("M?? x??c th???c ???? h???t h???n!"));
		}
		
		forgotPasswordValidation.getAccount().setPassword(encoder.encode(verification.getPassword()));
		accountDAO.save(forgotPasswordValidation.getAccount());
		
		try {
			mailerService.send(forgotPasswordValidation.getAccount().getEmail(), "Qu??n m???t kh???u t??i kho???n Sneat", bodySendMail.sendMailForgotPassword(
					forgotPasswordValidation.getAccount().getFullname(), verification.getPassword()));
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		verification.setActivity(false);
		verificationDAO.save(verification);
		return ResponseEntity.ok(new ResponseMessage("Ki???m tra email ????? nh???n m???t kh???u m???i!"));
	}
}
