package com.mckesson.inferno.loginsvc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginService {

	Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	
	@GetMapping("/hello")
	public String sayHello() {
		logger.info("Login Service is up and running.");
		return  "Welcome to Inferno - Login  Service.";
	}
	
}
