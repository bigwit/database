package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Currency;
import com.database.data.jpa.CurrencyService;
import com.database.data.jpa.procedure.ProcedureExecutor;

@Repository
@Service("currencyService")
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Currency> findAll() {
		return entityManager.createNativeQuery(
				"select * from table (load_currency_pipe)", Currency.class)
				.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Currency findById(Long id) {
		try {
			return (Currency) entityManager
					.createNativeQuery(
							"select * from table(load_currency) where id = :id",
							Currency.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Long addCurrency(String description, Float rate) {
		return new ProcedureExecutor(entityManager, "add_currency")
				.in(String.class, Long.class).out(Long.class).returnThis()
				.call(description, rate);
	}

}
