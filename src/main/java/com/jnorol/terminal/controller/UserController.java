package com.jnorol.terminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jnorol.terminal.domain.AuthenticationVO;
import com.jnorol.terminal.domain.RegisterDTO;
import com.jnorol.terminal.domain.UserVO;
import com.jnorol.terminal.service.AuthenticationService;
import com.jnorol.terminal.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationService authService;
	
	@PostMapping
	public String login(@RequestBody UserVO userVO) {
		return userService.login(userVO);
	}
	
	@PostMapping("/register")
	public boolean register(@RequestBody RegisterDTO registerDTO) {
		AuthenticationVO authVO = new AuthenticationVO(registerDTO.getAuthKey(), registerDTO.getId());
		if(authService.checkAuth(authVO)) {
			userService.insertUser(new UserVO(0, registerDTO.getId(), registerDTO.getPassword(), registerDTO.getName()));
			return true;
		} else {
			return false;
		}
	}
	
	@GetMapping("/{email}")
	public String emailAuthentication(@PathVariable String email) {
		authService.sendAuthKey(email);
		return "ok";
	}
}
