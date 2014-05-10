package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import oracle.sql.BFILE;

import com.database.data.domain.Photo;
import com.database.data.jpa.PhotoService;
import com.database.data.jpa.procedure.ProcedureExecutor;

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
		try {
			return (Photo) entityManager
					.createNativeQuery(
							"select * from table(load_photos) where id = :id",
							Photo.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Long addPhoto(BFILE photo, Long idPeople, Long idHotel, Long idPlace) {
		return new ProcedureExecutor(entityManager, "add_photo")
				.in(BFILE.class, Long.class, Long.class, Long.class)
				.out(Long.class).returnThis()
				.call(photo, idPeople, idHotel, idPlace);
	}

}
