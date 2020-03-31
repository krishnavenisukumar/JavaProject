package com.trade.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trade.dto.StockDetails;
import com.trade.dto.StockRequestDto;
import com.trade.entity.Stock;
import com.trade.exception.StockNotFoundException;
import com.trade.repository.StockRepository;
import com.trade.service.StockService;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;

	@Override
	public StockDetails getStockDetailsByStockId(Long stockId) throws StockNotFoundException {
		Optional<Stock> stocks = stockRepository.findById(stockId);
		if (!stocks.isPresent()) {
			throw new StockNotFoundException(stockId);
		}
		StockDetails details = new StockDetails();

		details.setStockName(stocks.get().getStockName());
		details.setStockPrice(stocks.get().getStockPrice());
		details.setStockQuantity(stocks.get().getStockQuantity());
		details.setDate(stocks.get().getDate());

		return details;
	}

	@Override
	public List<StockRequestDto> getStocksByStockName(String stockName) {
		List<StockRequestDto> stockRequestDtos = new ArrayList<>();
		List<Stock> stocks = stockRepository.findByStockNameContaining(stockName);
		stocks.forEach(stockDetails -> {
			StockRequestDto requestDto = new StockRequestDto();
			BeanUtils.copyProperties(stockDetails, requestDto);
			stockRequestDtos.add(requestDto);
		});
		return stockRequestDtos;
	}

}
