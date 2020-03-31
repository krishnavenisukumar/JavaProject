package com.trade.dto;

import java.util.List;

public class StockResponseDto {

	List<StockRequestDto> requestDtos;
	String displayMessage;

	public List<StockRequestDto> getRequestDtos() {
		return requestDtos;
	}

	public void setRequestDtos(List<StockRequestDto> requestDtos) {
		this.requestDtos = requestDtos;
	}

	public String getDisplayMessage() {
		return displayMessage;
	}

	public void setDisplayMessage(String displayMessage) {
		this.displayMessage = displayMessage;
	}

}
