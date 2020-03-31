package com.trade.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trade.dto.BuyRequestDto;
import com.trade.dto.PurchaseHistoryDto;
import com.trade.dto.ResponseDto;
import com.trade.exception.QuantityNotFoundException;
import com.trade.exception.StockNotFoundException;
import com.trade.exception.UserNotFoundException;
import com.trade.service.PurchaseService;

@RestController
public class PurchaseController {

	Logger log = LoggerFactory.getLogger(PurchaseController.class);

	@Autowired
	PurchaseService service;

	@PostMapping("/user/{userid}/stocks")
	public ResponseEntity<ResponseDto> buyDetails(@RequestParam Long stockId, @RequestParam Long userId,
			@RequestBody BuyRequestDto buyRequestDto)
			throws UserNotFoundException, StockNotFoundException, QuantityNotFoundException {
		ResponseDto responseDto = service.buyDetails(stockId, userId, buyRequestDto);
		log.info("Stock has been purchased by the user Successfully!!");
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}/mystocks")
	public ResponseEntity<List<PurchaseHistoryDto>> purchaseHistory(@RequestParam(name = "userId") Long userId)
			throws UserNotFoundException {
		List<PurchaseHistoryDto> response = service.purchaseHistory(userId);
		log.info("Display the purchase history for the particular user");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
