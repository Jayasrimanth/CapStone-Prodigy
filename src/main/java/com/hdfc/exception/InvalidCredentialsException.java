package com.hdfc.exception;


public class InvalidCredentialsException extends RuntimeException {
	
	public InvalidCredentialsException() {
		super("Invalid Username or Password");
	}

}
