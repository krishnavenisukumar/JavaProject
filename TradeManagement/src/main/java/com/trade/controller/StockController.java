package com.trade.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trade.constant.Constant;
import com.trade.dto.StockDetails;
import com.trade.dto.StockRequestDto;
import com.trade.dto.StockResponseDto;
import com.trade.exception.StockNotFoundException;
import com.trade.service.StockService;

@RestController

public class StockController {

	Logger log = LoggerFactory.getLogger(StockController.class);

	@Autowired
	StockService service;

	@GetMapping("/stocks/{stockid}")
	public ResponseEntity<StockDetails> getDetailsByUserId(@RequestParam Long stockId) throws StockNotFoundException {
		StockDetails stocks = service.getStockDetailsByStockId(stockId);
		log.info("Inside to get stocks from particular items");
		return new ResponseEntity<>(stocks, HttpStatus.OK);
	}

	@GetMapping("/stocks/stockName")
	public ResponseEntity<StockResponseDto> getStocksByUserName(@RequestParam String stockName) {
		StockResponseDto stockResponseDto = new StockResponseDto();
		List<StockRequestDto> requestDtos = service.getStocksByStockName(stockName);
		if (requestDtos.isEmpty()) {
			stockResponseDto.setDisplayMessage("Stock by its name is not found");
			return new ResponseEntity<>(stockResponseDto, HttpStatus.NOT_FOUND);
		}
		stockResponseDto.setDisplayMessage("Stock by its name is found");
		stockResponseDto.setRequestDtos(requestDtos);
		return new ResponseEntity<>(stockResponseDto, HttpStatus.OK);

	}
}
