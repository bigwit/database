package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Hotel;
import com.database.data.jpa.HotelService;

@Repository
@Service("hotelService")
@Transactional
public class HotelServiceImpl implements HotelService {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Hotel> findAll() {
		return entityManager.createNativeQuery(
				"select * from table(load_hotels)", Hotel.class)
				.getResultList();
	}

	@Override
	public Hotel findById(Long id) {
		try {
			return (Hotel) entityManager
					.createNativeQuery(
							"select * from table(load_hotels) where id = :id",
							Hotel.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
