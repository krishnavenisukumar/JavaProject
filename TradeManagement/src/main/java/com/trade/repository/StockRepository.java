package com.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trade.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

	List<Stock> findByStockNameContaining(String stockName);

	Stock findByStockId(Long stockId);

}
