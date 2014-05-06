package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Flight;
import com.database.data.jpa.FlightService;

@Repository
@Service("flightService")
@Transactional
public class FlightServiceImpl implements FlightService {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Flight> findAll() {
		return entityManager.createNativeQuery(
				"select * from table(load_flights)", Flight.class)
				.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Flight findById(Long id) {
		return (Flight) entityManager
				.createNativeQuery(
						"select * from table(load_flights) where id = :id",
						Flight.class).setParameter("id", id).getSingleResult();
	}

}
