package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Contact;
import com.database.data.jpa.ContactService;
import com.database.data.jpa.procedure.ProcedureExecutor;

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
		return new ProcedureExecutor(entityManager, "add_contact")
				.in(String.class, String.class, String.class, String.class,
						String.class).out(Long.class).returnThis()
				.call(phone, email, country, city, description);
	}

}
