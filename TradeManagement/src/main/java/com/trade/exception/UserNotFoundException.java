package com.trade.exception;

public class UserNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private static final Integer statusCode = 6200;

	public static Integer getStatuscode() {
		return statusCode;
	}

	public UserNotFoundException(Long userId) {
		super("user with userId is not found");

	}
	

}
