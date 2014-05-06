package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Travel;

public interface TravelService {

	List<Travel> findAll();
	
	Travel findById(Long id);
	
}
