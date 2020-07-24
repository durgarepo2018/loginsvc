package com.mckesson.inferno.loginsvc.model;



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
	  private String user_fname;
	  private String user_lname;
	  private String program_id;
	  private String createdDate;
	  
	  public User() {

	  }
	  
	  public User(String userName, String password, String userRole,
			  String user_fname, String user_lname, String program_id) {
		    this.userName = userName;
		    this.password = password;
		    this.userRole = userRole;
		    this.user_fname = user_fname;
		    this.user_lname = user_lname;
		    this.program_id = program_id;
	 }
//	 public User(String userName, String password, String userRole) {
//		    this.userName = userName;
//		    this.password = password;
//		    this.userRole = userRole;
//	 }

	 public User(String userName, String password) {
		    this.userName = userName;
		    this.password = password;
	 }

	 
	 
	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getProgram_id() {
		return program_id;
	}

	public void setProgram_id(String program_id) {
		this.program_id = program_id;
	}

	public String getUser_fname() {
		return user_fname;
	}

	public void setUser_fname(String user_fname) {
		this.user_fname = user_fname;
	}

	public String getUser_lname() {
		return user_lname;
	}

	public void setUser_lname(String user_lname) {
		this.user_lname = user_lname;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
