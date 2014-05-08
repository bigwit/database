package com.database.data.jpa;

import java.sql.Date;
import java.util.List;

import com.database.data.domain.Flight;

public interface FlightService {

	List<Flight> findAll();

	Flight findById(Long id);

	Long addFlight(Date leavingDate, Date arrivalDate, String typeTransport,
			Float price, Long idTour, Long idLocationTo, Long idLocationFrom,
			Long idCurrency);
}
