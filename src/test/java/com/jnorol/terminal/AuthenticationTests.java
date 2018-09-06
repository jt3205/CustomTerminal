package com.jnorol.terminal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jnorol.terminal.domain.AuthenticationVO;
import com.jnorol.terminal.service.AuthenticationService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "classpath:application.properties")
public class AuthenticationTests {
	@Autowired
	private AuthenticationService authService;
	
	//@Test
	public void sendEmailTests() {
		authService.sendAuthKey("jt3205@naver.com");
	}
	
	@Test
	public void checkAuthTest() {
		System.out.println(authService.checkAuth(new AuthenticationVO("hoFtd8", "jt3205@naver.com")));
	}
}
