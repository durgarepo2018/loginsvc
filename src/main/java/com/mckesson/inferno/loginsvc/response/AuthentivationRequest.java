package com.mckesson.inferno.loginsvc.response;

public class AuthentivationRequest {

	 private String userName;
	 private String password;
	
	AuthentivationRequest(){
		
	}
	
	public AuthentivationRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}