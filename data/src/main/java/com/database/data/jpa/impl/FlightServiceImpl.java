package com.database.data.jpa.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Flight;
import com.database.data.jpa.FlightService;
import com.database.data.jpa.procedure.ProcedureExecutor;

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
		try {
			return (Flight) entityManager
					.createNativeQuery(
							"select * from table(load_flights) where id = :id",
							Flight.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Long addFlight(Date leavingDate, Date arrivalDate,
			String typeTransport, Float price, Long idTour, Long idLocationTo,
			Long idLocationFrom, Long idCurrency) {
		return new ProcedureExecutor(entityManager, "add_flight")
				.in(Date.class, Date.class, String.class, Float.class,
						Long.class, Long.class, Long.class, Long.class)
				.out(Long.class)
				.returnThis()
				.call(leavingDate, arrivalDate, typeTransport, price, idTour,
						idLocationTo, idLocationFrom, idCurrency);
	}

}
