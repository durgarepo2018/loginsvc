package com.mckesson.inferno.loginsvc.response;

public class AuthenticationResponse {

	private String userName;
	private String userRole;
	private String authSuccss;
	private String message;
	private String firstName;
	private String lastName;
	
	public AuthenticationResponse() {
		
	}
	
	
	
	@Override
	public String toString() {
		return "AuthenticationResponse [userName=" + userName + ", userRole=" + userRole + ", authSuccss=" + authSuccss
				+ ", message=" + message + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getAuthSuccss() {
		return authSuccss;
	}
	public void setAuthSuccss(String authSuccss) {
		this.authSuccss = authSuccss;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
