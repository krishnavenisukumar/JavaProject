package com.trade.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long purchaseId;
	private String stockName;
	private Integer stockQuantity;
	private Double stockPrice;
	private Double TotalPurchasedAmount;

	private Long userId;
	private Long stockId;

	public Purchase() {
		super();

	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Double getTotalPurchasedAmount() {
		return TotalPurchasedAmount;
	}

	public void setTotalPurchasedAmount(Double totalPurchasedAmount) {
		TotalPurchasedAmount = totalPurchasedAmount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Purchase(String stockName, Integer stockQuantity, Double stockPrice, Double totalPurchasedAmount,
			Long userId, Long stockId) {
		super();
		this.stockName = stockName;
		this.stockQuantity = stockQuantity;
		this.stockPrice = stockPrice;
		TotalPurchasedAmount = totalPurchasedAmount;
		this.userId = userId;
		this.stockId = stockId;
	}

}
