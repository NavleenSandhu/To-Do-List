package ca.sheridancollege.ca.sandnavl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.ca.sandnavl.repositories.UserRepository;
@Controller
public class SecurityController {
	@Autowired
	private UserRepository userRepo;
	@GetMapping("/login")
	public String loginPage() {
		return "login.html";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password) {
		return "/"+username;
	}
	
	@GetMapping("/register")
	public String registerPage() {
		return "register.html";
	}
	@PostMapping("/register")
	public String register(@RequestParam String username,@RequestParam String firstName,@RequestParam String lastName, @RequestParam String password) {
		userRepo.addUser(username, firstName, lastName,password);
		userRepo.addRoles(userRepo.findUserByUserName(username).getUserId(), 1);
		return "redirect:/login";
	}
}
