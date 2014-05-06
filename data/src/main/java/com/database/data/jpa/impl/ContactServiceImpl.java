package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		return (Contact) entityManager
				.createNativeQuery(
						"select * from table(load_contacts) where id = :id",
						Contact.class).setParameter("id", id).getSingleResult();
	}

}
