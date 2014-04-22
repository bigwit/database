package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Placement;

public interface PlacementService {

	List<Placement> findAll();
	
	List<Placement> findAllWithDetails();
	
	Placement findById(Long id);
	
}
