package com.trade.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.trade.controller.StockController;
import com.trade.dto.StockDetails;
import com.trade.dto.StockRequestDto;
import com.trade.entity.Stock;
import com.trade.exception.StockNotFoundException;
import com.trade.service.StockService;

@RunWith(MockitoJUnitRunner.Silent.class)

public class StockControllerTest {

	@Mock
	StockService service;

	@InjectMocks
	StockController controller;

	List<StockRequestDto> requestDto = null;
	List<StockRequestDto> requestDto1 = null;

	StockRequestDto dto = null;
	Stock stock = null;
	StockDetails details = null;

	@Before
	public void before() {
		stock = new Stock();
		requestDto = new ArrayList<>();
		details = new StockDetails();
		dto = new StockRequestDto();

		stock.setStockId(1L);
		stock.setStockName("SBI common");
		stock.setStockPrice(20000.00);
		stock.setStockQuantity(50);
		stock.setDate(LocalDate.of(2020, 01, 10));

		details.setStockName("SBI common");
		details.setStockPrice(20000.00);
		details.setStockQuantity(50);
		details.setDate(LocalDate.of(2020, 01, 10));

		dto.setStockName("SBI");
		requestDto.add(dto);

	}

	@Test
	public void  getDetailsByUserIdTest() throws StockNotFoundException {
		Mockito.when(service.getStockDetailsByStockId(1L)).thenReturn(details);
		ResponseEntity<StockDetails> responseEntity = controller.getDetailsByUserId(1L);
		assertEquals(responseEntity.getBody().getStockName(), details.getStockName());
	}

	@Test
	public void getStocksByUserNamePositive() {
		Mockito.when(service.getStocksByStockName("SBI")).thenReturn(requestDto);
		Integer responseEntity = controller.getStocksByUserName("SBI").getStatusCodeValue();
		assertEquals(200, responseEntity);
	}

	@Test
	public void getStocksByUserNameNegative() {
		Mockito.when(service.getStocksByStockName("SBI")).thenReturn(requestDto);
		Integer responseEntity = controller.getStocksByUserName("ICICI").getStatusCodeValue();
		assertEquals(404, responseEntity);
	}

}
