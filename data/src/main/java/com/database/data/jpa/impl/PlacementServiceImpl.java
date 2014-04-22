package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Placement;
import com.database.data.jpa.PlacementService;

@Repository
@Service("placementService")
@Transactional
public class PlacementServiceImpl implements PlacementService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	@Override
	public List<Placement> findAll() {
		return entityManager.createNamedQuery("Placement.findAll",
				Placement.class).getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Placement> findAllWithDetails() {
		return entityManager.createNamedQuery("Placement.findAllWithDetails",
				Placement.class).getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Placement findById(Long id) {
		return entityManager
				.createNamedQuery("Placement.findById", Placement.class)
				.setParameter("id", id).getSingleResult();
	}

}
