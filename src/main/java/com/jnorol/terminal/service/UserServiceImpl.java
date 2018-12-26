package com.jnorol.terminal.service;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jnorol.terminal.domain.UserVO;
import com.jnorol.terminal.mapper.UserMapper;
import com.jnorol.terminal.repository.UserRepository;
import com.jnorol.terminal.util.Constants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;
	
	@Transactional
	@Override
	public void insertUser(UserVO userVO) {
		userRepository.save(userVO);
	}

	@Transactional
	@Override
	public String login(UserVO userVO) {
		userVO = userMapper.login(userVO);
		
		if(userVO == null) {
			return null;
		}
		
		String jwt = null;
		try {
			jwt = Jwts.builder()
							.claim("userSeq", userVO.getSeq())
							.signWith(SignatureAlgorithm.HS256, Constants.SALT_KEY.getBytes("UTF-8"))
							.compact();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return jwt;
	}
	
	@Override
	public int getUserSeqById(String id) {
		return userMapper.getUserSeqById(id);
	}
	
	@Override
	public UserVO getUser(int seq) {
		return userRepository.getOne(seq);
	}
}
