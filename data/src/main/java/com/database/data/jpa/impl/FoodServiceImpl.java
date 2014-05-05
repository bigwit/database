package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Food;
import com.database.data.jpa.FoodService;

@Repository
@Service("foodService")
@Transactional
public class FoodServiceImpl implements FoodService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Food> findAll() {
		return entityManager.createNamedQuery("Food.findAll", Food.class)
				.getResultList();
	}

	@Override
	public Food findById(Long id) {
		return entityManager.createNamedQuery("Food.findById", Food.class)
				.setParameter("id", id).getSingleResult();
	}

}
