package com.jnorol.terminal.util;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTUtil {
	public String getClaim(String jwt, String key) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser()
					  .setSigningKey("!CuST0mTerMiNa1!".getBytes("UTF-8"))
					  .parseClaimsJws(jwt);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
				| IllegalArgumentException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return claims.getBody().get(key).toString();
	}
}
