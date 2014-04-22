package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.People;

public interface PeopleService {

	List<People> findAll();
	
	List<People> findAllWithDetails();
	
	People findById(Long id);
}
