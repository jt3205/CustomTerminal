package com.jnorol.terminal.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jnorol.terminal.domain.SettingVO;

@Mapper
public interface SettingMapper {
	public SettingVO getSettingByUserSeq(int userSeq);

	public SettingVO getSettingByUserId(String userId);
}
