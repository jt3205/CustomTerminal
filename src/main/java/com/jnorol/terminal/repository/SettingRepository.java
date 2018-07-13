package com.jnorol.terminal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jnorol.terminal.domain.SettingVO;

public interface SettingRepository extends JpaRepository<SettingVO, Integer>{
	
}
