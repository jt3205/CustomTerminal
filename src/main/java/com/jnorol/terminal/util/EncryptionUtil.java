package com.jnorol.terminal.util;

import java.security.MessageDigest;

import org.springframework.stereotype.Component;

/**
 * @작성자 : 박형진
 * @작성일 : 2018. 6. 09.
 * @수정일 : 2018. 6. 09. / 2018. 06. 19
 * @개요 : 주로 비밀번호 저장시에 sha256코드로 암호화하는 클래스
 * @검증 : http://www.convertstring.com/ko/Hash/SHA256
 */
@Component
public class EncryptionUtil {
	public String encodeToSha256(String base) {
		if(base == null) {
			return null;
		}
		base = base + Constants.SALT_KEY;
	    StringBuffer hexString = new StringBuffer();
		try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
		return hexString.toString();
	}
}
