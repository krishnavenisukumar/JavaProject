package com.trade.service;

import java.util.List;

import com.trade.dto.BuyRequestDto;
import com.trade.dto.PurchaseHistoryDto;
import com.trade.dto.ResponseDto;
import com.trade.exception.QuantityNotFoundException;
import com.trade.exception.StockNotFoundException;
import com.trade.exception.UserNotFoundException;

public interface PurchaseService {

	ResponseDto buyDetails(Long stockId, Long userId, BuyRequestDto buyRequestDto)
			throws UserNotFoundException, StockNotFoundException, QuantityNotFoundException;

	public List<PurchaseHistoryDto> purchaseHistory(Long userId);

}
