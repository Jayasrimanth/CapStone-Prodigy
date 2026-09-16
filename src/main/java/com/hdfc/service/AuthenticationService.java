package com.hdfc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hdfc.dto.LoginRequestDTO;
import com.hdfc.dto.LoginResponseDTO;
import com.hdfc.dto.LoginServiceDTO;
import com.hdfc.exception.InvalidCredentialsException;
import com.hdfc.security.JWTTokenProvider;
import com.hdfc.session.SessionStore;

@Service
public class AuthenticationService {

	@Value("${hardcoded.username}")
	private String username;

	@Value("${hardcoded.password}")
	private String password;

	private JWTTokenProvider jwtTokenProvider;

	private SessionStore sessionStore;

	public AuthenticationService(JWTTokenProvider jwtTokenProvider, SessionStore sessionStore) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.sessionStore = sessionStore;
	}

	public LoginServiceDTO login(LoginRequestDTO request) throws InvalidCredentialsException{

		if (!username.equals(request.getUsername()) || !password.equals(request.getPassword())) {
			throw new InvalidCredentialsException();
		}
		Date now = new Date();
		String token = jwtTokenProvider.generateToken(username, now);

		sessionStore.add(token);

		return new LoginServiceDTO(username, token, now);

	}

}
