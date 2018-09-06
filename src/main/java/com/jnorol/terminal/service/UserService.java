package com.jnorol.terminal.service;

import com.jnorol.terminal.domain.UserVO;

public interface UserService {
	public void insertUser(UserVO userVO);
	
	public String login(UserVO userVO);
	
	public int getUserSeqById(String id);
}
