package com.jnorol.terminal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jnorol.terminal.domain.AuthenticationVO;
import com.jnorol.terminal.repository.AuthenticationRepository;
import com.jnorol.terminal.util.Constants;
import com.jnorol.terminal.util.MailUtil;
import com.jnorol.terminal.util.RandomStringUtil;

/**
 * @작성자 : 박형진
 * @작성일 : 2018. 6. 11.
 * @수정일 : 2018. 6. 11. / 2018. 06. 13. / 2018. 06. 19
 * @개요 : AuthenticationService 관련된 주요 기능을 제공하는 Service
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	@Autowired
	private AuthenticationRepository authenticationRepository;
	
	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private RandomStringUtil randomStringUtil;
	
	@Transactional
	@Override
	public boolean sendAuthKey(String email) {
		randomStringUtil.setLength(6);
		String randomString = randomStringUtil.nextString();
		AuthenticationVO vo = new AuthenticationVO(randomString, email);
		authenticationRepository.save(vo);
		mailUtil.sendMail(Constants.MAIL_SENDER, email, "이메일 인증", "Custom Terminal 인증 키 입니다. ["+randomString+"]");
		return true;
	}
	
	@Transactional
	@Override
	public boolean checkAuth(AuthenticationVO authVO) {
		AuthenticationVO temp = authenticationRepository.getOne(authVO.getKey());
		if(authVO.getEmail().equals(temp.getEmail()) && authVO.getKey().equals(temp.getKey())) {
			return true;
		}
		return false;
	}
}
