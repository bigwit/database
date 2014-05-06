package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.database.data.domain.People;
import com.database.data.jpa.PeopleService;

@Service("peopleService")
@Repository
@Transactional
@SuppressWarnings("unchecked")
public class PeopleServiceImpl implements PeopleService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<People> findAll() {
		return entityManager.createNativeQuery(
				"select * from table(load_peoples)", People.class)
				.getResultList();
	}

	@Override
	@Deprecated
	public List<People> findAllWithDetails() {
		return entityManager.createNativeQuery(
				"select * from table(load_peoples)", People.class)
				.getResultList();
	}

	@Override
	public People findById(Long id) {
		return (People) entityManager
				.createNativeQuery(
						"select * from table(load_peoples) where id = :id",
						People.class).setParameter("id", id).getSingleResult();
	}

}
