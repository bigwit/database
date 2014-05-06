package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Flight;

public interface FlightService {

	List<Flight> findAll();
	
	Flight findById(Long id);
	
}
