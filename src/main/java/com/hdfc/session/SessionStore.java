package com.hdfc.session;

public interface SessionStore {
	
	public void add(String token);

	public boolean exists(String token);

	public void remove(String token);
	
}
