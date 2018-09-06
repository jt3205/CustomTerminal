package com.jnorol.terminal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jnorol.terminal.domain.AuthenticationVO;

/**
 * @작성자 : 박형진
 * @작성일 : 2018. 6. 10.
 * @수정일 : 2018. 6. 10.
 * @개요 : TB_AUTHENTICATION에 있는 정보를 가져오는 JpaRepository 클래스
 */
public interface AuthenticationRepository extends JpaRepository<AuthenticationVO, String>{

}
