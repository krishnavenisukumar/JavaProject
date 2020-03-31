package com.trade.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trade.constant.Constant;
import com.trade.dto.BuyRequestDto;
import com.trade.dto.PurchaseHistoryDto;
import com.trade.dto.ResponseDto;
import com.trade.entity.Purchase;
import com.trade.entity.Stock;
import com.trade.entity.User;
import com.trade.exception.QuantityNotFoundException;
import com.trade.exception.StockNotFoundException;
import com.trade.exception.UserNotFoundException;
import com.trade.repository.PurchaseRepository;
import com.trade.repository.StockRepository;
import com.trade.repository.UserRepository;
import com.trade.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	StockRepository stockRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PurchaseRepository purchaseRepository;

	@Override
	public ResponseDto buyDetails(Long stockId, Long userId, BuyRequestDto buyRequestDto)
			throws UserNotFoundException, StockNotFoundException, QuantityNotFoundException {
		Purchase purchase = new Purchase();
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) {
			throw new UserNotFoundException(userId);
		}
		Optional<Stock> stock = stockRepository.findById(buyRequestDto.getStockId());
		if (!stock.isPresent()) {
			throw new StockNotFoundException(buyRequestDto.getStockId());
		}
		Double stockPrice = stock.get().getStockPrice();
		Integer stockQuantity = stock.get().getStockQuantity();
		Integer quantityWanted = buyRequestDto.getQuantity();
		if (quantityWanted > stockQuantity) {
			throw new QuantityNotFoundException(buyRequestDto.getStockId());
		}
		@SuppressWarnings("unused")
		Double price = quantityWanted * stockPrice;

		purchase.setStockQuantity(quantityWanted);
		purchase.setStockPrice(stockPrice);
		purchase.setUserId(user.get().getUserId());
		purchase.setStockId(stock.get().getStockId());
		purchaseRepository.save(purchase);

		Integer updatedQuantity = stockQuantity - quantityWanted;
		stock.get().setStockQuantity(updatedQuantity);
		stockRepository.save(stock.get());

		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(Constant.SUCCESS_CODE);
		responseDto.setStatusMessage(Constant.SUCCESS_MESSAGE);
		return responseDto;
	}

	@Override
	public List<PurchaseHistoryDto> purchaseHistory(Long userId) {
		List<Purchase> purchases = purchaseRepository.findByPurchaseId(userId);
		List<PurchaseHistoryDto> historyDto = purchases.stream().map(history -> {
			PurchaseHistoryDto purchaseHistoryDto = new PurchaseHistoryDto();
			purchaseHistoryDto.setMystockQuantity(history.getStockQuantity());
			BeanUtils.copyProperties(history, purchaseHistoryDto);
			return purchaseHistoryDto;
		}).collect(Collectors.toList());
		return historyDto;
	}
}
