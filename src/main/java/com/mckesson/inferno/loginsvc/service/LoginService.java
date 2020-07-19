package com.mckesson.inferno.loginsvc.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginService {

	@GetMapping("/hello")
	public String sayHello() {
		return  "Welcome to Inferno - Login  Service.";
	}
	
}
