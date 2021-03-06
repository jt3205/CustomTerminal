package com.jnorol.terminal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jnorol.terminal.domain.UserVO;
import com.jnorol.terminal.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "classpath:application.properties")
public class UserTests {
	@Autowired
	private UserService userService;
	
	private UserVO userVO;
	
	{
		userVO = UserVO.builder()
				.id("park")
				.name("박형진")
				.password("test")
				.build();
	}
	
	//@Test
	public void insertUserTest() {
		userService.insertUser(userVO);
	}
	@Test
	public void contextLoads() {
	}
}
