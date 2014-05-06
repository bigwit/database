package com.database.data.jpa.impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
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

	@Override
	public Long addPeople(String firstName, String middleName, String lastName,
			Date dateBirth, String sex, Long idContact) {
		StoredProcedureQuery query = entityManager
				.createStoredProcedureQuery("add_people")
				.registerStoredProcedureParameter(1, String.class,
						ParameterMode.IN)
				.setParameter(1, firstName)
				.registerStoredProcedureParameter(2, String.class,
						ParameterMode.IN)
				.setParameter(2, middleName)
				.registerStoredProcedureParameter(3, String.class,
						ParameterMode.IN)
				.setParameter(3, lastName)
				.registerStoredProcedureParameter(4, Date.class,
						ParameterMode.IN)
				.setParameter(4, dateBirth)
				.registerStoredProcedureParameter(5, String.class,
						ParameterMode.IN)
				.setParameter(5, sex)
				.registerStoredProcedureParameter(6, Long.class,
						ParameterMode.IN)
				.setParameter(6, idContact)
				.registerStoredProcedureParameter(7, Long.class,
						ParameterMode.OUT);
		query.execute();
		return (Long) query.getOutputParameterValue(7);
	}

}
