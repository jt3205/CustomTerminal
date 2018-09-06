package com.jnorol.terminal.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jnorol.terminal.domain.UserVO;

@Mapper
public interface UserMapper {
	public UserVO login(UserVO userVO);
	
	public int getUserSeqById(String id);
}
