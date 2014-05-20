package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Tour;
import com.database.data.domain.TourInfo;

public interface TourService {

	List<Tour> findAll();
	
	Tour findById(Long id);
	
	List<TourInfo> findAllInfo();
}
