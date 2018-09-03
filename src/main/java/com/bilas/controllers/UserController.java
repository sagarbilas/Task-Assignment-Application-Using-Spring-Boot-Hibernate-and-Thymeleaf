package com.bilas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bilas.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public String listOfUsers(Model model, @RequestParam(defaultValue="") String name)
	{
		model.addAttribute("users", userService.findByName(name)); 
		
		return "views/listOfUsers";
	}
	
	
	@GetMapping("/delete")
	public String deleteUer(@RequestParam("email") String email) {
		userService.deleteCustomer(email);
		return "views/listOfUsers";
	}

}
