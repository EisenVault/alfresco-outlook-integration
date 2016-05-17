package com.eisenvault.model;

public class User {

	private String userName;

	private char [] password;

	public String getUserName() {
		return userName;
	}

	public char [] getPassword() {
		return password;
	}

	public User() {
		super();
	}

	public User(String userName, char [] password) {
		super();
		this.userName = userName;
		this.password = password;
	}

}
