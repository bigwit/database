package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Tour;

public interface SearchService {

	List<Tour> searchTours(String searchLine);
	
}
