package com.hdfc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.dto.LoginRequestDTO;
import com.hdfc.dto.LoginResponseDTO;
import com.hdfc.dto.LoginServiceDTO;
import com.hdfc.service.AuthenticationService;

@RestController
public class AuthController {

	private AuthenticationService authenticationService;

	public AuthController(AuthenticationService authenticationService) {

		this.authenticationService = authenticationService;

	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
		try {

			LoginServiceDTO response = authenticationService.login(loginRequestDTO);

			ResponseCookie cookie = ResponseCookie.from("token", response.getToken()).httpOnly(true)

					.secure(false)

					.sameSite("Strict")

					.path("/")

					.maxAge(3600)

					.build();

			return ResponseEntity.status(HttpStatus.CREATED).header("Set-Cookie", cookie.toString())
					.body(new LoginResponseDTO(

							response.getUsername(),

							response.getLoggedInAt()

					));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
		}
	}
}
