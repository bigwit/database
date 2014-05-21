package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.TourInfo;

public interface SearchService {

	List<TourInfo> search(String searchLine, String searchType);
	
}
