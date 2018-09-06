package com.jnorol.terminal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jnorol.terminal.domain.CommandVO;
import com.jnorol.terminal.mapper.CommandMapper;
import com.jnorol.terminal.repository.CommandRepository;

@Service
public class CommandServiceImpl implements CommandService {
	@Autowired
	private CommandRepository commandRepository;
	
	@Autowired
	private CommandMapper commandMapper;

//	@Autowired
//	private EntityManager em;
	
	@Transactional
	@Override
	public void insertCommand(CommandVO commandVO) {
		commandRepository.save(commandVO);
	}

	@Transactional
	@Override
	public String getText(CommandVO commandVO) {
		return commandMapper.getText(commandVO);
	}

	@Transactional
	@Override
	public List<CommandVO> getAllCommandByUserSeq(int userSeq) {
		return commandMapper.getAllCommandByUserSeq(userSeq);
	}

	@Transactional
	@Override
	public void deleteCommand(int commandSeq) {
		commandRepository.deleteById(commandSeq);
	}

	@Transactional
	@Override
	public void updateCommand(CommandVO commandVO) {
		System.out.println(commandVO.getSeq());
		System.out.println(commandVO.getCommand());
		System.out.println(commandVO.getText());
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5");
		
		commandMapper.updateCommand(commandVO);
		
//		CommandVO oldVO = em.find(CommandVO.class, commandVO.getSeq());
//		if(oldVO != null) {
//			oldVO.update(commandVO);
//			commandRepository.save(oldVO);
//		}
//		em.close();
	}

	@Transactional
	@Override
	public CommandVO getCommand(CommandVO commandVO) {
		return commandMapper.getCommand(commandVO);
	}

}
