package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.TourInfo;
import com.database.data.type.SearchType;

public interface SearchService {

	List<TourInfo> search(String searchLine, SearchType searchType);
	
}
