package com.database.data.jpa;

import java.util.List;

import oracle.sql.BFILE;

import com.database.data.domain.Photo;

public interface PhotoService {

	List<Photo> findAll();
	
	Photo findById(Long id);
	
	Long addPhoto(BFILE photo, Long idPeople, Long idHotel, Long idPlace);
	
}
