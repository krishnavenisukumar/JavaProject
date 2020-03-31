package com.trade.exception;

public class StockNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final Integer statusCode = 6100;

	public static Integer getStatuscode() {
		return statusCode;
	}

	public StockNotFoundException(Long stockId) {
		super("stock with stockId is not found");

	}
}
