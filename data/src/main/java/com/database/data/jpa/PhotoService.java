package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Photo;

public interface PhotoService {

	List<Photo> findAll();
	
	Photo findById(Long id);
	
}
