package com.trade.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.trade.dto.ResponseDto;

@ControllerAdvice
public class GlobalExceptionalHandler {
	@ExceptionHandler(UserNotFoundException.class)
	ResponseEntity<ResponseDto> userNotFoundException(UserNotFoundException exception) {
		ResponseDto response = new ResponseDto();
		response.setStatusMessage(exception.getMessage());
		response.setStatusCode(UserNotFoundException.getStatuscode());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@ExceptionHandler(StockNotFoundException.class)
	ResponseEntity<ResponseDto> stockNotFoundException(StockNotFoundException exception) {
		ResponseDto response = new ResponseDto();
		response.setStatusMessage(exception.getMessage());
		response.setStatusCode(StockNotFoundException.getStatuscode());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@ExceptionHandler(QuantityNotFoundException.class)
	ResponseEntity<ResponseDto> quantityNotFoundException(QuantityNotFoundException exception) {
		ResponseDto response = new ResponseDto();
		response.setStatusMessage(exception.getMessage());
		response.setStatusCode(QuantityNotFoundException.getStatuscode());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}