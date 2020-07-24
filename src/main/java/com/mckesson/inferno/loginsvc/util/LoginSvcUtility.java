package com.mckesson.inferno.loginsvc.util;

import com.mckesson.inferno.loginsvc.model.User;

public class LoginSvcUtility {

   public static enum UserRole {
		
		CSR, AGENT, ADMIN;
		
		
	}
	
	public static boolean isValidAddUserRequest(User userRequest) {
		if(userRequest != null 
				&& userRequest.getUserName() != null && userRequest.getUserName().trim().length() > 0
				&& userRequest.getPassword() != null && userRequest.getPassword().trim().length() > 0
				&& userRequest.getUserRole() != null && userRequest.getUserRole().trim().length() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isValidAuthRequest(User userRequest) {
		if(userRequest != null 
				&& userRequest.getUserName() != null && userRequest.getUserName().trim().length() > 0
				&& userRequest.getPassword() != null && userRequest.getPassword().trim().length() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
