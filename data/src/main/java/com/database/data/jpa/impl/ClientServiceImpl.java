package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Client;
import com.database.data.jpa.ClientService;

@Repository
@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> findAll() {
		return entityManager.createNativeQuery(
				"select * from table(load_clients)", Client.class)
				.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Client findById(Long id) {
		try {
			return (Client) entityManager
					.createNativeQuery("select * from table(load_clients)",
							Client.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
