package com.bilas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bilas.entities.User;
import com.bilas.services.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String showRegisterForm(Model theModel) {
		User theUser = new User();
		theModel.addAttribute("user", theUser);

		return "views/doRegister";
	}

	@PostMapping("/register")
	public String registerNewUser(@Valid User user, BindingResult bindingResult, Model model) {
		// check if there is any validation error
		if (bindingResult.hasErrors()) {
			return "views/doRegister";
		}
		if (userService.isUserPresent(user.getEmail())) {
			model.addAttribute("exist", true);

			return "views/doRegister";
		}
		userService.user_creation(user);

		return "views/registrationSuccess";
	}

}
