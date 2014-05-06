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
@SuppressWarnings("unchecked")
public class PlacementServiceImpl implements PlacementService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	@Override
	public List<Placement> findAll() {
		return entityManager.createNativeQuery(
				"select * from table(load_placement)", Placement.class)
				.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	@Deprecated
	public List<Placement> findAllWithDetails() {
		return entityManager
				.createNativeQuery(
						"select * from table(load_placement) p join table(load_currency) c on c.id = p.id_currency",
						Placement.class).getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Placement findById(Long id) {
		return (Placement) entityManager
				.createNativeQuery(
						"select * from table(load_placement) where id = :id",
						Placement.class).setParameter("id", id)
				.getSingleResult();
	}

}
