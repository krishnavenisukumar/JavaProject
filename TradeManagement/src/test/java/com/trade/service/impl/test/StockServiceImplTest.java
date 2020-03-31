package com.trade.service.impl.test;

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

import com.trade.dto.StockDetails;
import com.trade.dto.StockRequestDto;
import com.trade.entity.Stock;
import com.trade.exception.StockNotFoundException;
import com.trade.repository.StockRepository;
import com.trade.service.impl.StockServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)

public class StockServiceImplTest {

	@Mock
	StockRepository stockRepository;

	@InjectMocks
	private StockServiceImpl serviceImpl;

	Stock stock = null;
	List<Stock> list = null;
	StockDetails details = null;

	@Before
	public void setup() {

		stock = new Stock();
		list = new ArrayList<Stock>();
		details = new StockDetails();

		stock.setStockId(1L);
		stock.setStockName("SBI common");
		stock.setStockPrice(20000.00);
		stock.setStockQuantity(50);
		stock.setDate(LocalDate.of(2020, 01, 10));

		list.add(stock);

	}

	@Test
	public void getStockDetailsTest() throws StockNotFoundException {
		Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
		StockDetails dto = serviceImpl.getStockDetailsByStockId(1L);
		assertEquals(dto.getStockName(), stock.getStockName());

	}

	@Test(expected = StockNotFoundException.class)
	public void getStockDetailsNegativeTest() throws StockNotFoundException {
		Mockito.when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));
		StockDetails dto = serviceImpl.getStockDetailsByStockId(4L);
		assertEquals(dto.getStockName(), stock.getStockId());

	}

	@Test
	public void getStockDetailsByNameTest() throws StockNotFoundException {
		Mockito.when(stockRepository.findByStockNameContaining("SBI")).thenReturn(list);
		List<StockRequestDto> request = serviceImpl.getStocksByStockName("SBI");
		assertEquals(list.size(), request.size());

	}

}
