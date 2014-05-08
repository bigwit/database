package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Location;
import com.database.data.jpa.LocationService;
import com.database.data.jpa.procedure.ProcedureExecutor;

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
		try {
			return (Location) entityManager
					.createNativeQuery(
							"select * from table(load_locations) where id = :id",
							Location.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Long addLocation(String country, String city, String description) {
		return new ProcedureExecutor(entityManager, "add_location")
				.in(String.class, String.class, String.class)
				.out(Long.class).returnThis().call(country, city, description);
	}

	@Override
	public Long addLocation(Location location) {
		return addLocation(location.getCountry(), location.getCity(),
				location.getDescription());
	}

}
