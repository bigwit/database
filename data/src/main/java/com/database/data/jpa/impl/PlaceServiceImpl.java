package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Place;
import com.database.data.jpa.PlaceService;

@Service("placeService")
@Repository
@Transactional
@SuppressWarnings("unchecked")
public class PlaceServiceImpl implements PlaceService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	@Override
	public List<Place> findAll() {
		return entityManager.createNativeQuery(
				"select * from table(load_places)", Place.class)
				.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Place findById(Long id) {
		return (Place) entityManager
				.createNativeQuery(
						"select * from table(load_places) where id = :id",
						Place.class).setParameter("id", id).getSingleResult();
	}

}
