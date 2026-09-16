package com.hdfc.session;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;


@Repository
public class InMemorySessionStore implements SessionStore {

	private Set<String> session = new HashSet<>();

	@Override
	public void add(String token) {
		session.add(token);
	}

	@Override
	public boolean exists(String token) {
		return session.contains(token);
	}

	@Override
	public void remove(String token) {
		session.remove(token);
	}

}
