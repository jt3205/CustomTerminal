package com.jnorol.terminal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jnorol.terminal.domain.SettingVO;
import com.jnorol.terminal.domain.UserVO;
import com.jnorol.terminal.mapper.SettingMapper;
import com.jnorol.terminal.service.SettingService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "classpath:application.properties")
public class SettingTests {
	@Autowired
	private SettingService settingService;

	@Autowired
	private SettingMapper settingMapper;
	
	private SettingVO settingVO;
	
	{
		settingVO = SettingVO.builder()
					.seq(1)
					.title("HyeongJn's Terminal")
					.infoURL("github.com/jt3205")
					.imageURL("https://scontent-hkg3-2.xx.fbcdn.net/v/t31.0-8/25790947_1829995540624748_7367934650415715385_o.jpg?_nc_cat=0&oh=375cb8efcc2b2a154c83daa2da86e14b&oe=5BE390DB")
					.prompt("[JnOrOl@Portpolio] # ")
					.titleColor("#DDDDDD")
					.textColor("#00FFFF")
					.promptColor("#00FF00")
					.backgroundColor("#00FFFF")
					.user(new UserVO(1, "", "", ""))
					.build();
	}
	
	//@Test
	public void insertSettingService() {
		settingService.insertSetting(settingVO);
	}
	
	//@Test
	public void getSettingByUserSeq() {
		System.out.println(settingMapper.getSettingByUserSeq(2));
	}
	
	//@Test
	public void getSettingByUserId() {
		System.out.println(settingMapper.getSettingByUserId("test"));
	}
	@Test
	public void contextLoads() {
	}
}
