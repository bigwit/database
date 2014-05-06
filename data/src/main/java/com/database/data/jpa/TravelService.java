package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Travel;
import com.database.data.domain.User;

public interface TravelService {

	List<Travel> findAll();
	
	Travel findById(Long id);
	
	List<Travel> findByUser(User user);
	
}
