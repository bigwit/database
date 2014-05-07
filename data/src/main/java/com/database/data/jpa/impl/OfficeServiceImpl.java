package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Office;
import com.database.data.jpa.OfficeService;

@Service("officeService")
@Repository
@Transactional
@SuppressWarnings("unchecked")
public class OfficeServiceImpl implements OfficeService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	@Override
	public List<Office> findAll() {
		return entityManager.createNativeQuery(
				"select * from table(load_offices)", Office.class)
				.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	@Deprecated
	public List<Office> findAllWithDetails() {
		return entityManager.createNativeQuery(
				"select * from table(load_offices)", Office.class)
				.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Office findById(Long id) {
		try {
			return (Office) entityManager
					.createNativeQuery(
							"select * from table(load_offices) where id = :id",
							Office.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
