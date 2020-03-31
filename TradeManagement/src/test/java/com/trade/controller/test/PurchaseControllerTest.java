package com.trade.controller.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.common.base.Optional;
import com.google.common.math.Quantiles;
import com.trade.controller.PurchaseController;
import com.trade.dto.BuyRequestDto;
import com.trade.dto.PurchaseHistoryDto;
import com.trade.dto.ResponseDto;
import com.trade.entity.Purchase;
import com.trade.entity.User;
import com.trade.exception.QuantityNotFoundException;
import com.trade.exception.StockNotFoundException;
import com.trade.exception.UserNotFoundException;
import com.trade.service.PurchaseService;
import com.trade.service.StockService;

@RunWith(MockitoJUnitRunner.Silent.class)

public class PurchaseControllerTest {

	@Mock
	PurchaseService purchaseService;
	@Mock
	StockService stockService;
	@InjectMocks
	PurchaseController purchaseController;

	Purchase purchase = null;
	List<Purchase> purchases = null;
	PurchaseHistoryDto purchaseHistoryDto = null;
	List<PurchaseHistoryDto> responseDto = null;
	BuyRequestDto buyRequestDto = null;
	ResponseDto dto = null;
	User user = null;

	@Before
	public void before() {

		Purchase purchase = new Purchase();
		purchase.setPurchaseId(1L);
		purchase.setStockId(1L);
		purchase.setUserId(1L);
		purchase.setStockName("SBI");
		purchase.setStockPrice(20000.00);
		purchase.setStockQuantity(100);

		List<Purchase> purchases = new ArrayList<>();
		purchases.add(purchase);

		User user = new User();
		user.setUserId(1L);

		buyRequestDto = new BuyRequestDto();
		buyRequestDto.setStockId(1L);

		purchaseHistoryDto = new PurchaseHistoryDto();
		purchaseHistoryDto.setPurchaseId(1L);

		responseDto = new ArrayList<>();
		responseDto.add(purchaseHistoryDto);

		dto = new ResponseDto();
		dto.setStatusCode(HttpStatus.OK.value());

	}

	@Test
	public void purchaseHistoryTest() throws UserNotFoundException {
		Mockito.when(purchaseService.purchaseHistory(1L)).thenReturn(responseDto);
		ResponseEntity<List<PurchaseHistoryDto>> response = purchaseController.purchaseHistory(1L);
		assertNotNull(response);
	}

	@Test
	public void buyStockTest() throws UserNotFoundException, StockNotFoundException, QuantityNotFoundException {
		Mockito.when(purchaseService.buyDetails(1L, 1L, buyRequestDto)).thenReturn(dto);
		Integer responseEntity = purchaseController.buyDetails(1L, 1L, buyRequestDto).getStatusCodeValue();
		assertEquals(200, responseEntity);
	}

}
