package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Office;
import com.database.data.jpa.OfficeService;

@Service("officeService")
@Repository
@Transactional
public class OfficeServiceImpl implements OfficeService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	@Override
	public List<Office> findAll() {
		return entityManager.createNamedQuery("Office.findAll", Office.class)
				.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Office> findAllWithDetails() {
		return entityManager.createNamedQuery("Office.findAllWithDetails",
				Office.class).getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Office findById(Long id) {
		return entityManager.createNamedQuery("Office.findById", Office.class)
				.setParameter("id", id).getSingleResult();
	}

}
