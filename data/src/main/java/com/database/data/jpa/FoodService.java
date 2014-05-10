package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Food;

public interface FoodService {

	List<Food> findAll();
	
	Food findById(Long id);
	
	Long addFood(String type, Float price, Long idCurrency);
	
}
