package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Room;

public interface RoomService {

	List<Room> findAll();
	
	Room findById(Long id);
	
}
