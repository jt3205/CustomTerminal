package com.jnorol.terminal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jnorol.terminal.domain.UserVO;

public interface UserRepository extends JpaRepository<UserVO, Integer> {
	
}
