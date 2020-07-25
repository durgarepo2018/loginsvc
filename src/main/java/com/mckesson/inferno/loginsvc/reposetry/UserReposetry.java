package com.mckesson.inferno.loginsvc.reposetry;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.mckesson.inferno.loginsvc.model.User;

public interface UserReposetry extends MongoRepository<User, Long> {
	
	 User findByUserName(String userName); 
	 
	// @Query("{'userName' : {$ne : null}}")
	// @Query(value="{'userName' : {$ne : null}}", fields="{user_fName : 0, user_lName : 1}")
	 @Query(value = "{'userName' : {$ne : null}}", fields = "{'userName' : 0, 'password' : 0, 'createdDate' : 0 , 'password' : 0}")
     public List<User> findAllAgents();
	 
}
