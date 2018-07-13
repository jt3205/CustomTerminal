package com.jnorol.terminal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jnorol.terminal.domain.SettingVO;
import com.jnorol.terminal.repository.SettingRepository;

@Service
public class SettingServiceImpl implements SettingService {
	@Autowired
	private SettingRepository settingRepository;

	@Transactional
	@Override
	public void insertSetting(SettingVO settingVO) {
		settingRepository.save(settingVO);
	}

}
