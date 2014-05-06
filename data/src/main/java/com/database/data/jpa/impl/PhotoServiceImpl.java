package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.database.data.domain.Photo;
import com.database.data.jpa.PhotoService;

public class PhotoServiceImpl implements PhotoService {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> findAll() {
		return entityManager.createNativeQuery(
				"select * from table(load_photos)", Photo.class)
				.getResultList();
	}

	@Override
	public Photo findById(Long id) {
		return (Photo) entityManager
				.createNativeQuery(
						"select * from table(load_photos) where id = :id",
						Photo.class).setParameter("id", id).getSingleResult();
	}

}
