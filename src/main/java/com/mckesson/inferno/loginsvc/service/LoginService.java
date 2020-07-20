package com.mckesson.inferno.loginsvc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mckesson.inferno.loginsvc.exception.InvalidInputException;
import com.mckesson.inferno.loginsvc.model.User;
import com.mckesson.inferno.loginsvc.reposetry.UserReposetry;
import com.mckesson.inferno.loginsvc.util.LoginSvcUtility;

@RestController
public class LoginService {

	Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    UserReposetry userReposetry;
	
	@GetMapping("/hello")
	public String sayHello() {
		logger.info("Login Service is up and running.");
		return  "Welcome to Inferno - Login  Service.";
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User userRequest) {
		
		logger.info("addUser :Start");
		logger.info(" Request Data  :"+userRequest);
		if(LoginSvcUtility.isValidAddUserRequest(userRequest)) {
			 try 
			 {
			    User userDetails = userReposetry.save(userRequest);
			    logger.info(" Response Data  :"+userDetails);
			    return new ResponseEntity<>(userDetails, HttpStatus.CREATED);
				 
			 } catch (Exception e) {
				logger.debug("Exception at AddUser :"+e.getMessage());
				return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
				 
			 }
		} else {
			throw new InvalidInputException(" Invalid AddUser Request  : userName, passpord, userRole required .");
			
		}
		
		 
	}

	@PostMapping("/login")
    public ResponseEntity<User> userLogin(@RequestBody User userCredentials) {
		
		return null;
    }
}
