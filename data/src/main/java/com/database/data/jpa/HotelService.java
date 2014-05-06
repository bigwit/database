package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Hotel;

public interface HotelService {

	List<Hotel> findAll();
	
	Hotel findById(Long id);
	
}
