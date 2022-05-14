package com.taller3.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.taller3.demo.model.prod.UserApp;


@Controller
public class LogInController {

	@GetMapping("/login")
	public String login(UserApp ua) {
		
		return "login";
	}
	
	/*
	@PostMapping("/login")
	public String postLogin(UserApp ua) {
		System.out.println(">>>>>>>>");
		return "redirect:index";
	}
	*/
}
