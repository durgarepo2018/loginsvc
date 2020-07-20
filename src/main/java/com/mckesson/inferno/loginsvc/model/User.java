package com.mckesson.inferno.loginsvc.model;

import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {

	  @Id
	  private String userId;
	  private String userRole;
	  
	  @Indexed(unique=true)
	  private String userName;
	  
	  private String password;
	  
	  
	  public User() {

	  }
	  
	 public User(String userName, String password, String userRole) {
		    this.userName = userName;
		    this.password = password;
		    this.userRole = userRole;
	 }

	 public User(String userName, String password) {
		    this.userName = userName;
		    this.password = password;
	 }

	public String getUserId() {
		return userId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
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

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userRole=" + userRole + ", userName=" + userName + ", password=" + password
				+ "]";
	}
	  
	  
	  
}
