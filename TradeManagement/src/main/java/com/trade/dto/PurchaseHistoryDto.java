package com.trade.dto;

public class PurchaseHistoryDto {

	Long userId;
	Long purchaseId;
	String stockName;
	Integer mystockQuantity;
	Double stockPrice;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Integer getMystockQuantity() {
		return mystockQuantity;
	}

	public void setMystockQuantity(Integer mystockQuantity) {
		this.mystockQuantity = mystockQuantity;
	}

	public Double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

}
