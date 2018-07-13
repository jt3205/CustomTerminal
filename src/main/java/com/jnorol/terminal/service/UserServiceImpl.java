package com.jnorol.terminal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jnorol.terminal.domain.UserVO;
import com.jnorol.terminal.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Transactional
	@Override
	public void insertUser(UserVO userVO) {
		userRepository.save(userVO);
	}
	
}
