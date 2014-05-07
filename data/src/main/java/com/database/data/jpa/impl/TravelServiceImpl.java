package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Travel;
import com.database.data.domain.User;
import com.database.data.jpa.TravelService;

@Transactional
@Repository
@Service("travelService")
@SuppressWarnings("unchecked")
public class TravelServiceImpl implements TravelService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Travel> findAll() {
		return entityManager.createNativeQuery(
				"select * from table(load_travels)", Travel.class)
				.getResultList();
	}

	@Override
	public Travel findById(Long id) {
		try {
			return (Travel) entityManager
					.createNativeQuery(
							"select * from table(load_travels) where id = :id",
							Travel.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Travel> findByUser(User user) {
		Long peopleId = user.getPeople().getId();
		return entityManager
				.createNativeQuery(
						"select tr.id, tr.number_adults, tr.number_child, tr.date_payment, tr.id_tour, tr.id_client "
								+ "from table(load_travels) tr join clients cl on "
								+ "tr.id_client = cl.id and cl.id_people = :peopleId")
				.setParameter("peopleId", peopleId).getResultList();
	}

}
