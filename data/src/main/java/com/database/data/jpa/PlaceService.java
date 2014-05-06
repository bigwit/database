package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Place;

public interface PlaceService {

	List<Place> findAll();
	
	Place findById(Long id);
	
}
