package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Contact;
import com.database.data.jpa.ContactService;

@Service("contactService")
@Repository
@Transactional
@SuppressWarnings("unchecked")
public class ContactServiceImpl implements ContactService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	@Override
	public List<Contact> findAll() {
		return entityManager.createNativeQuery(
				"select * from table(load_contacts)", Contact.class)
				.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	@Deprecated
	public List<Contact> findAllWithDetails() {
		return entityManager.createNativeQuery(
				"select * from table(load_contacts)", Contact.class)
				.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Contact findById(Long id) {
		try {
			return (Contact) entityManager
					.createNativeQuery(
							"select * from table(load_contacts) where id = :id",
							Contact.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Long addContact(String phone, String email, String country,
			String city, String description) {
		StoredProcedureQuery query = entityManager
				.createStoredProcedureQuery("add_contact")
				.registerStoredProcedureParameter(1, String.class,
						ParameterMode.IN)
				.setParameter(1, phone)
				.registerStoredProcedureParameter(2, String.class,
						ParameterMode.IN)
				.setParameter(2, email)
				.registerStoredProcedureParameter(3, String.class,
						ParameterMode.IN)
				.setParameter(3, country)
				.registerStoredProcedureParameter(4, String.class,
						ParameterMode.IN)
				.setParameter(4, city)
				.registerStoredProcedureParameter(5, String.class,
						ParameterMode.IN)
				.setParameter(5, description)
				.registerStoredProcedureParameter(6, Long.class,
						ParameterMode.OUT);
		query.execute();
		return (Long) query.getOutputParameterValue(6);
	}

}
