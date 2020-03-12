package co2103.hw2.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import co2103.hw2.domain.Person;
import co2103.hw2.domain.UserKind;
import co2103.hw2.repository.PersonRepository;

@Controller
public class AuthenticationController {
	
	@Autowired
	private PersonRepository prepo;
	
	@GetMapping("/login-form")
	public String loginForm() {
		return "security/login";
	}
	
	@GetMapping("/success-login")
	public String successLogin(Principal principal) {
		Person p = prepo.findByUsername(principal.getName());
		if (p.getKind() == UserKind.Manager) {
			return "redirect:/";
		}
		return "redirect:b/bookings";
	}
	
	@GetMapping("/access-denied")
	public String error() {
		return "security/denied";
	}
}
