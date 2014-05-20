package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.database.data.domain.Tour;
import com.database.data.domain.TourInfo;
import com.database.data.jpa.TourService;

@Repository
@Transactional
@Service("tourService")
@SuppressWarnings("unchecked")
public class TourServiceImpl implements TourService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	@Override
	public List<Tour> findAll() {
		return entityManager.createNativeQuery(
				"select * from table(load_tours)", Tour.class).getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Tour findById(Long id) {
		try {
			return (Tour) entityManager
					.createNativeQuery(
							"select * from table(load_tours) where id = :id",
							Tour.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<TourInfo> findAllInfo() {
		return entityManager.createNativeQuery(
				"select * from table(find_tour_info())", TourInfo.class)
				.getResultList();
	}

}
