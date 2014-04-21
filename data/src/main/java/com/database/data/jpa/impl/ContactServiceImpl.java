package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.database.data.domain.Contact;
import com.database.data.jpa.ContactService;

public class ContactServiceImpl implements ContactService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Contact> findAll() {
		return entityManager.createNamedQuery("Contact.findAll", Contact.class)
				.getResultList();
	}

	@Override
	public List<Contact> findAllWithDetails() {
		return entityManager.createNamedQuery("Contact.findAllWithDetails",
				Contact.class).getResultList();
	}

	@Override
	public Contact findById(Long id) {
		return entityManager
				.createNamedQuery("Contact.findById", Contact.class)
				.getSingleResult();
	}

}
