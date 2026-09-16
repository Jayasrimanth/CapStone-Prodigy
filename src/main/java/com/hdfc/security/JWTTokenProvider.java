package com.hdfc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component

public class JWTTokenProvider {
	
	@Value("${hardcoded.username}")
	private String hardCodedUsername;
	
	@Value("${security.jwt.secret-key}")
	private String secretKey;

	@Value("${security.jwt.expiration-time}")
	private long jwtExpiration;

	public String generateToken(String username, Date iat) {
		Date exp = new Date(iat.getTime() + jwtExpiration);

		return Jwts.builder().setSubject(username).setIssuedAt(iat).setExpiration(exp).signWith(Keys.hmacShaKeyFor(secretKey.getBytes()),SignatureAlgorithm.HS256).compact();
	}


	public boolean isValid(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())).build().parseClaimsJws(token).getBody().getSubject().equals(hardCodedUsername); 
	}
	

}
