package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
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
		return (Travel) entityManager
				.createNativeQuery(
						"select * from table(load_travels) where id = :id",
						Travel.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public List<Travel> findByUser(User user) {
		Long peopleId = user.getPeople().getId();
		return entityManager
				.createNativeQuery(
						"select * from table(load_travels) where id_people = :peopleId")
				.setParameter("peopleId", peopleId).getResultList();
	}

}
