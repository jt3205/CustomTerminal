package com.jnorol.terminal.service;

import java.util.List;

import com.jnorol.terminal.domain.CommandVO;

public interface CommandService {
	public void insertCommand(CommandVO commandVO);
	
	public void deleteCommand(int commandSeq);
	
	public void updateCommand(CommandVO commandVO);
	
	public String getText(CommandVO commandVO);
	
	public List<CommandVO> getAllCommandByUserSeq(int userSeq);
}
