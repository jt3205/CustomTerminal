package com.jnorol.terminal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jnorol.terminal.domain.CommandVO;
import com.jnorol.terminal.domain.UserVO;
import com.jnorol.terminal.service.CommandService;
import com.jnorol.terminal.service.UserService;
import com.jnorol.terminal.util.JWTUtil;

@RestController
@RequestMapping("/api/command")
@CrossOrigin
public class CommandController {
	@Autowired
	private CommandService commandService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTUtil JWTUtil;

	@GetMapping("/list/{token}")
	public List<CommandVO> getAllCommandByUserSeq(@PathVariable String token) {
		return commandService.getAllCommandByUserSeq(Integer.parseInt(JWTUtil.getClaim(token, "userSeq")));
	}

	@GetMapping("/{command}/{userId}")
	public CommandVO getCommand(@PathVariable String command, @PathVariable String userId) {
		return commandService.getCommand(new CommandVO(0, command, null, new UserVO(userService.getUserSeqById(userId), null, null, null)));
	}

	@PostMapping
	public void addCommand(@RequestBody CommandVO commandVO) {
		commandService.insertCommand(commandVO);
	}
}
