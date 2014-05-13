package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Currency;

public interface CurrencyService {

	List<Currency> findAll();
	
	Currency findById(Long id);
	
	Long addCurrency(String description, Float rate);
}
