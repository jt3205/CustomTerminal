package com.jnorol.terminal.service;

import com.jnorol.terminal.domain.SettingVO;

public interface SettingService {
	public void insertSetting(SettingVO settingVO);
	
	public SettingVO getSettingByUserSeq(int seq);
	
	public SettingVO getSettingByUserId(String userId);
}
