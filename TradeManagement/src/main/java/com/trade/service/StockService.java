package com.trade.service;

import java.util.List;

import com.trade.dto.StockDetails;
import com.trade.dto.StockRequestDto;
import com.trade.exception.StockNotFoundException;

public interface StockService {

	StockDetails getStockDetailsByStockId(Long stockId) throws StockNotFoundException;

	List<StockRequestDto> getStocksByStockName(String stockName);

}
