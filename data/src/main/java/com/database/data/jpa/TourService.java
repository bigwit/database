package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Tour;

public interface TourService {

	List<Tour> findAll();
	
	Tour findById(Long id);
}
