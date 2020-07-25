package com.mckesson.inferno.loginsvc.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mckesson.inferno.loginsvc.dao.SequenceDao;
import com.mckesson.inferno.loginsvc.exception.InvalidInputException;
import com.mckesson.inferno.loginsvc.model.User;
import com.mckesson.inferno.loginsvc.reposetry.UserReposetry;
import com.mckesson.inferno.loginsvc.response.AuthenticationResponse;
import com.mckesson.inferno.loginsvc.response.AuthentivationRequest;
import com.mckesson.inferno.loginsvc.util.LoginSvcUtility;
import com.mckesson.inferno.loginsvc.util.LoginSvcUtility.UserRole;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;



@RestController
public class LoginService {

	Logger logger = LoggerFactory.getLogger(LoginService.class);

    public static final String SEQUENCE_NAME = "users_sequence";
	  
    @Autowired
    UserReposetry userReposetry;
    
	@Autowired
	private SequenceDao sequenceDao;
	
	@Autowired
	private GridFsTemplate gridFsTemplate;
	
	@GetMapping("/hello")
	public String sayHello() {
		logger.info("Login Service is up and running.");
		return  "Welcome to Inferno - Login  Service.";
	}
	
	
	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User userRequest)  {
		
		logger.info("addUser :Start");
		logger.info(" Request Data  :"+userRequest);
		
		userRequest.setUserId(""+sequenceDao.getNextSequenceId(SEQUENCE_NAME));
		userRequest.setCreatedDate(new Date().toString());
		if(LoginSvcUtility.isValidAddUserRequest(userRequest)) {
			
			boolean isValidUserRole = false;
			for (UserRole userRole : UserRole.values()) { 
				
				if(userRole.toString().equalsIgnoreCase(userRequest.getUserRole())) {
					isValidUserRole = true;
					break;	
				} 
			}
			if(isValidUserRole) {
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
				StringBuffer userRoleValue = new StringBuffer("");
				for (UserRole validUserRole : UserRole.values()) { 
					userRoleValue.append(validUserRole.toString() + ":");
				}
				throw new InvalidInputException(" Invalid UserRole Value, Valid User Role values are  : "+ userRoleValue.toString());
			}
		} else {
			throw new InvalidInputException(" Invalid AddUser Request  : userName, passpord, userRole required .");
		}
	
		 
	}

	@PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthentivationRequest authRequest) {

		logger.info("authenticateUser :Start");
		logger.info(" Request Data  :"+authRequest);
		if(LoginSvcUtility.isValidAuthRequest(authRequest)) {
			try 
			 {
				AuthenticationResponse response = new AuthenticationResponse();
				
			    User userDetails = userReposetry.findByUserName(authRequest.getUserName());
			    uploadFaxImage();
			    if(userDetails!= null) {
			    	if(userDetails.getPassword() != null && userDetails.getPassword().equals(authRequest.getPassword())) {
			    		 logger.info("Authentication Succsess.");
			    		 response.setAuthSuccss("true");
			    		 response.setUserName(authRequest.getUserName());
			    		 response.setFirstName(userDetails.getUser_fname());
			    		 response.setLastName(userDetails.getUser_lname());
			    		 response.setUserRole(userDetails.getUserRole());
			    		 return new ResponseEntity<>(response, HttpStatus.FOUND);
			    	} else {
			    		logger.info("Incorrect Password.");
			    		response.setAuthSuccss("false");
			    		response.setMessage("Incorrect Password.");
			    		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			    	}
			    	
			    } else {
			    	 logger.info("Invalid UserName.");
			    	 response.setAuthSuccss("false");
			    	 response.setMessage("Incorrect UserName.");
			    	 return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			    }
			   // logger.info(" Auth Response Data  :"+userDetails);
			   
				 
			 } catch (Exception e) {
				logger.debug("Exception at AddUser :"+e.getMessage());
				return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
				 
			 }
		}else {
			throw new InvalidInputException(" Invalid Authentication Request  : userName and passpord are required .");
		}
		
		
    }
	
	
	public void uploadFaxImage() {
		
		InputStream inputStream = null;
		DBObject metaData = new BasicDBObject();
		metaData.put("user", "durga");
		try {
			inputStream = new FileInputStream("src/main/resources/InsuranceCardHover_v2.png");
			gridFsTemplate.store(inputStream, "test.png", "image/png", metaData).toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
}
