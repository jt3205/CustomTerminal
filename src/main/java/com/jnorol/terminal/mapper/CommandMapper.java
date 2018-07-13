package com.jnorol.terminal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jnorol.terminal.domain.CommandVO;

@Mapper
public interface CommandMapper {
	public String getText(CommandVO commandVO);
	
	public List<CommandVO> getAllCommandByUserSeq(int userSeq);
}
