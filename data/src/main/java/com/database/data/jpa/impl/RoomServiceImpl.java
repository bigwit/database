package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.database.data.domain.Room;
import com.database.data.jpa.RoomService;

@Repository
@Service("roomService")
public class RoomServiceImpl implements RoomService {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> findAll() {
		return entityManager.createNativeQuery(
				"select * from table(load_rooms)", Room.class).getResultList();
	}

	@Override
	public Room findById(Long id) {
		return (Room) entityManager
				.createNativeQuery(
						"select * from table(load_rooms) where id = :id")
				.setParameter("id", id).getSingleResult();
	}

}
