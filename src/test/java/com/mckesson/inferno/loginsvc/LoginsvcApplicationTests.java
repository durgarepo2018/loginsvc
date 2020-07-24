package com.mckesson.inferno.loginsvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mckesson.inferno.loginsvc.model.User;
import com.mckesson.inferno.loginsvc.response.AuthentivationRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginsvcApplicationTests {

	@Test
	void contextLoads() {
	}

	
	@Autowired
	private MockMvc mockMvc ;
	
	private final ObjectMapper mapper = new ObjectMapper();
	 
	@Test
	public void testInvalidReqUrl() throws Exception {
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/invalidUrl")
			).andExpect(MockMvcResultMatchers.status().isNotFound());
		
	}
	
	@Test
	public void testHelloMessage() throws Exception {
		
 
		mockMvc.perform(get("/hello")).andExpect(status().isOk())
			 .andExpect(content().string("Welcome to Inferno - Login  Service."));
		 
	}
	
	
	@Test
	public void testGetAddUser() throws Exception {
		
        mockMvc.perform(get("/addUser")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
                
	}
	
	@Test
	public void testPostWAddUserWithNoReqData() throws Exception {
		
        mockMvc.perform(post("/addUser")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
                
	}
	
	@Test
	public void testPostWAddUserWithEmptyReqData() throws Exception {
		
        mockMvc.perform(post("/addUser")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
                
	}
	
	@Test
	public void testAddUserWithInvalidReqData() throws Exception {
		
		User userRequest = new User("testUser", "testPwd");
		HttpHeaders httpHeaders = new HttpHeaders();
        mockMvc.perform(post("/addUser")
        		.accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(userRequest))
                .headers(httpHeaders))
        		.andExpect(status().isBadRequest());	
                
                
	}
	
	@Test
	public void testAddUserWithValidReqData() throws Exception {
		
		User userRequest = new User("e3j7qfx_test", "testPwd", "Agent", "first1", "lastname1", "0000");
		HttpHeaders httpHeaders = new HttpHeaders();
        mockMvc.perform(post("/addUser")
        		.accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(userRequest))
                .headers(httpHeaders))
        		.andExpect(status().isCreated());	
       
	}
	
	@Test
	public void testGetAuthUser() throws Exception {
		
        mockMvc.perform(get("/authenticate")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
                
	}
	
	@Test
	public void testPostWAuthUserWithEmptyData() throws Exception {
		
        mockMvc.perform(post("/authenticate")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
                
	}
	
	@Test
	public void testAuthUserWithInValidUserName() throws Exception {
		
		AuthentivationRequest authRequest = new AuthentivationRequest("e3j7qfx_invalid", "password1");
		HttpHeaders httpHeaders = new HttpHeaders();
        mockMvc.perform(post("/authenticate")
        		.accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(authRequest))
                .headers(httpHeaders))
        		.andExpect(status().isNotFound())
        		.andExpect(jsonPath("$.message", Matchers.is("Incorrect UserName.")));
       
	}
	
	@Test
	public void testAuthUserWithInValidpwd() throws Exception {
		
		AuthentivationRequest authRequest = new AuthentivationRequest("e3j7qfx", "password1_invalid");
		HttpHeaders httpHeaders = new HttpHeaders();
        mockMvc.perform(post("/authenticate")
        		.accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(authRequest))
                .headers(httpHeaders))
        		.andExpect(status().isNotFound())
        		.andExpect(jsonPath("$.message", Matchers.is("Incorrect Password.")))
        		.andExpect(jsonPath("$.authSuccss", Matchers.is("false")));
       
	}
	@Test
	public void testAuthUserWithValidReqData() throws Exception {
		
		AuthentivationRequest authRequest = new AuthentivationRequest("e3j7qfx", "password1");
		HttpHeaders httpHeaders = new HttpHeaders();
        mockMvc.perform(post("/authenticate")
        		.accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(authRequest))
                .headers(httpHeaders))
        		.andExpect(status().isFound())
        		.andExpect(jsonPath("$.authSuccss", Matchers.is("true")));
       
	}
}
