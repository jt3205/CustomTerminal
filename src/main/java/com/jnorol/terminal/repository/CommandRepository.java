package com.jnorol.terminal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jnorol.terminal.domain.CommandVO;

public interface CommandRepository extends JpaRepository<CommandVO, Integer>{
	
}
