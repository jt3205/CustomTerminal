package com.jnorol.terminal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jnorol.terminal.domain.CommandVO;
import com.jnorol.terminal.domain.UserVO;
import com.jnorol.terminal.service.CommandService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "classpath:application.properties")
public class CommandTests {
	@Autowired
	private CommandService commandService;
	
	private CommandVO commandVO;
	
	{
		commandVO = CommandVO.builder()
					.command("test1")
					.text("Test1 명령어 입니다!")
					.userVO(new UserVO(1, "", "", ""))
					.build();
	}
	
	//@Test
	public void insertCommandTest() {
		commandService.insertCommand(commandVO);
	}
	
	//@Test
	public void getTextTest() {
		System.out.println(commandService.getText(commandVO));
	}
	
	@Test
	public void getAllTextByUserSeqTest() {
		System.out.println("$$++$$ :" + commandService.getAllCommandByUserSeq(1));
	}
}
