package com.jnorol.terminal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jnorol.terminal.util.JWTUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MainPageDTO {
	private String title;
	
	private String infoURL;
	
	private String imageURL;
	
	private String prompt;
	
	private String titleColor;

	private String textColor;

	private String promptColor;
	
	private String backgroundColor;
	
	private CommandVO commandList[];
	
	private int seq;
	
	private String jwt;
	
	public SettingVO getSettingVO() {
		JWTUtil jwtUtil = new JWTUtil();
		return new SettingVO(seq, title, infoURL, imageURL, prompt, titleColor, textColor, promptColor, backgroundColor, 
				new UserVO(Integer.parseInt(jwtUtil.getClaim(jwt, "userSeq")), null, null, null));
	}
}
