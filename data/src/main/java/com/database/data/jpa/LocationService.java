package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Location;

public interface LocationService {

	List<Location> findAll();
	
	Location findById(Long id);
}
