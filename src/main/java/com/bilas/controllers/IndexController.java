package com.bilas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping("/")
	public String showindexPage()
	{
		return "index";
	}
	
	
	@GetMapping("/login")
	public String showLogin()
	{
		return "views/login";
	}
	
	@GetMapping("/about")
	public String about()
	{
		return "views/about";
	}

}
