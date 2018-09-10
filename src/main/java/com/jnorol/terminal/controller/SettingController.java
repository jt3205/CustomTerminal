package com.jnorol.terminal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jnorol.terminal.domain.CommandVO;
import com.jnorol.terminal.domain.MainPageDTO;
import com.jnorol.terminal.domain.SettingVO;
import com.jnorol.terminal.domain.UserVO;
import com.jnorol.terminal.service.CommandService;
import com.jnorol.terminal.service.SettingService;
import com.jnorol.terminal.service.UserService;
import com.jnorol.terminal.util.JWTUtil;

@RestController
@RequestMapping("/api/setting")
@CrossOrigin
public class SettingController {
	@Autowired
	private SettingService settingService;
	
	@Autowired
	private CommandService commandService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@GetMapping("/{jwt}")
	public SettingVO getSettingByUserToken(@PathVariable String jwt) {
		return settingService.getSettingByUserSeq(Integer.parseInt(jwtUtil.getClaim(jwt, "userSeq")));
	}
	
	@GetMapping("/get/{id}")
	public SettingVO getSettingByUserId(@PathVariable String id) {
		return settingService.getSettingByUserSeq(userService.getUserSeqById(id));
	}
	
	@PostMapping
	public void setSetting(@RequestBody MainPageDTO dto) {
		settingService.insertSetting(dto.getSettingVO());
		CommandVO commandVO[] = dto.getCommandList();
		UserVO user = UserVO.builder().seq(Integer.parseInt(jwtUtil.getClaim(dto.getJwt(), "userSeq"))).build();
		
		for (int i = 0; i < commandVO.length; i++) {
			commandVO[i].setUserVO(user);
			System.out.println(commandVO[i].getSeq() + " ============================================= ");
			if(commandVO[i].getSeq() == 0) {
				commandService.insertCommand(commandVO[i]);
			} else {
				commandService.updateCommand(commandVO[i]);
			}
		}
	}
}
