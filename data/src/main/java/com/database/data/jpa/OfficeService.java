package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Office;

public interface OfficeService {

	List<Office> findAll();
	
	List<Office> findAllWithDetails();
	
	Office findById(Long id);
	
	Long addOffice(Office office);
}
