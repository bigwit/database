package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Currency;
import com.database.data.jpa.CurrencyService;

@Repository
@Service("currencyService")
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	@Override
	public List<Currency> findAll() {
		return entityManager.createNamedQuery("Currency.findAll",
				Currency.class).getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Currency findById(Long id) {
		return entityManager
				.createNamedQuery("Currency.findById", Currency.class)
				.setParameter("id", id).getSingleResult();
	}

}
