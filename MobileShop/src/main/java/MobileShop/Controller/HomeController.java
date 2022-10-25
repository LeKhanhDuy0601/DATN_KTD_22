package MobileShop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping({"/admin","/admin"})
	public String admin() {
		return "redirect:/admin/layout/index.html";
	}
	
	@RequestMapping("/signin")
	public String login() {
		return "authentications/login_page";
	}
	@RequestMapping("/register")
	public String register() {
		return "authentications/register_page";
	}
	@RequestMapping("/forgot-password")
	public String forgot() {
		return "authentications/forgot_password_page";
	}
	
}
