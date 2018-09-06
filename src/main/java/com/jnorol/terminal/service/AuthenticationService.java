package com.jnorol.terminal.service;

import com.jnorol.terminal.domain.AuthenticationVO;

/**
 * @작성자 : 박형진
 * @작성일 : 2018. 6. 11.
 * @수정일 : 2018. 6. 11.
 * @개요 : AuthenticationService의 기능을 정의하는 interface
 */
public interface AuthenticationService {
	public boolean sendAuthKey(String email);
	
	public boolean checkAuth(AuthenticationVO authVO);
}
