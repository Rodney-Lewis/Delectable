package com.delectable.userauth;

import com.delectable.EntityUtil;
import com.delectable.userauth.payload.request.LoginRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import com.delectable.userauth.payload.request.SignupRequest;
import java.io.IOException;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class UserAuth {

	@Autowired
	private UserAuthUtil util;

	@Autowired
	private EntityUtil entityUtil;

	@Autowired
	private MockMvc mockMvc;

	MvcResult response;

	@Test
	@Transactional
	public void invalidSignUp() throws IOException, Exception {
		SignupRequest signupRequest = util.createInvalidSignupRequest();
		response = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
				.content(entityUtil.toJson(signupRequest)).contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();
	}

	@Test
	@Transactional
	public void invalidLogin() throws IOException, Exception {
		LoginRequest loginRequest = util.createLoginRequest("username", "password");

		response = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signin")
				.content(entityUtil.toJson(loginRequest)).contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

	}

	@Test
	@Transactional
	public void signUpAndValidLogin() throws IOException, Exception {
		SignupRequest signupRequest = util.createValidSignupRequest();
		response = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup")
				.content(entityUtil.toJson(signupRequest)).contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		LoginRequest loginRequest =
				util.createLoginRequest(signupRequest.getUsername(), signupRequest.getPassword());

		response = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signin")
				.content(entityUtil.toJson(loginRequest)).contentType(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

	}

}
