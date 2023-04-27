package com.spring.boot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//WecomeController is bean or not?? yes
public class SignupController {
	//@Autowired - it pull bean here - >> it is used to inject one bean inside another bean
	@Autowired
	private SignupService  signupService;

	@GetMapping("/clogin")
	public String showLogin() {
		return "login"; // login.jsp
	}

	@GetMapping("/csignup")
	public String showSignup() {
		return "signup"; // signup.jsp
	}

	@PostMapping("/csignup")
	public String posrCreate(@ModelAttribute SignupDTO signupDTO, Model model) {
		signupService.save(signupDTO);
		// JDBC PROGRAMMING
		model.addAttribute("message", "Hey profile is created successfully");
		return "signup"; // signup.jsp
	}

	@GetMapping("/showSignups")
	public String showSignups() {
		List<SignupDTO>   dtos=signupService.findAll();
		DataStore.getSignupDTOs().addAll(dtos);
		return "showSignups"; // showSignups.jsp
	}
}
