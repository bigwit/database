package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Location;
import com.database.data.jpa.LocationService;

@Service("locationService")
@Repository
@Transactional
@SuppressWarnings("unchecked")
public class LocationServiceImpl implements LocationService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	@Override
	public List<Location> findAll() {
		return entityManager.createNativeQuery(
				"select * from table(load_locations)", Location.class)
				.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Location findById(Long id) {
		return (Location) entityManager
				.createNativeQuery(
						"select * from table(load_locations) where id = :id",
						Location.class).setParameter("id", id)
				.getSingleResult();
	}

}
