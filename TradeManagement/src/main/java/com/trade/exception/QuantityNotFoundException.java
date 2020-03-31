package com.trade.exception;

public class QuantityNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private static final Integer statusCode = 6300;

	public static Integer getStatuscode() {
		return statusCode;
	}

	public QuantityNotFoundException(Long stockquantity) {
		super("Quantity is not much sufficient");

	}

}
