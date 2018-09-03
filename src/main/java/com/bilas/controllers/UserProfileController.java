package com.bilas.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bilas.entities.User;
import com.bilas.services.TaskService;
import com.bilas.services.UserService;

@Controller
public class UserProfileController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;

	@GetMapping("/profile")
	public String displayProfilePage(Model model, Principal principal) {
		String email = principal.getName();
		User user = userService.getUserByEmail(email);
		model.addAttribute("tasks", taskService.findUserTask(email));

		return "views/userProfile";
	}
}
