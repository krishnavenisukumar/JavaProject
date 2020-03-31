package com.trade.service.impl.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.trade.dto.BuyRequestDto;
import com.trade.dto.PurchaseHistoryDto;
import com.trade.dto.ResponseDto;
import com.trade.dto.StockDetails;
import com.trade.entity.Purchase;
import com.trade.entity.Stock;
import com.trade.entity.User;
import com.trade.exception.QuantityNotFoundException;
import com.trade.exception.StockNotFoundException;
import com.trade.exception.UserNotFoundException;
import com.trade.repository.PurchaseRepository;
import com.trade.repository.StockRepository;
import com.trade.repository.UserRepository;
import com.trade.service.impl.PurchaseServiceImpl;
import com.trade.service.impl.StockServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)

public class PurchaseServiceImplTest {

	@Mock
	PurchaseRepository purchaseRepository;

	@Mock
	UserRepository userRepository;
	@Mock
	StockRepository stockRepository;

	@InjectMocks
	private PurchaseServiceImpl purchaseServiceImpl;

	Purchase purchase = null;
	User user = null;
	Stock stock = null;
	BuyRequestDto buyRequestDto = null;
	List<Purchase> purchases = null;

	@Before
	public void before() {
		purchase = new Purchase();
		user = new User();
		buyRequestDto = new BuyRequestDto();
		stock = new Stock();

		purchase.setPurchaseId(1L);
		purchase.setStockId(1L);
		purchase.setUserId(1L);
		purchase.setStockName("SBI");
		purchase.setStockPrice(20000.00);
		purchase.setStockQuantity(100);

		user.setUserId(1L);
		user.setUserMobileNo(9876345291L);
		user.setUserName("Krishna");

		buyRequestDto = new BuyRequestDto();
		buyRequestDto.setQuantity(100);
		buyRequestDto.setStockId(1L);

		stock.setStockId(1L);
		stock.setDate(LocalDate.of(20202, 01, 20));
		stock.setStockName("SBI");
		stock.setStockPrice(25000.00);
		stock.setStockQuantity(1000);
	}

	@Test
	public void buyDetailsTest() throws UserNotFoundException, StockNotFoundException, QuantityNotFoundException {
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
		Mockito.when(purchaseRepository.findByPurchaseId(1L)).thenReturn(purchases);
		ResponseDto responseDto = purchaseServiceImpl.buyDetails(1L, 1L, buyRequestDto);
		assertNotNull(responseDto);

	}

	@Test
	public void testPurchaseHistory() {
		Mockito.when(purchaseRepository.findAll()).thenReturn(purchases);
		List<PurchaseHistoryDto> responseDTO = purchaseServiceImpl.purchaseHistory(1L);
		assertNotNull(responseDTO);

	}

}
