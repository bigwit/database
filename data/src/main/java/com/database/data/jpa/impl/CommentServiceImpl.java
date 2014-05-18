package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Comment;
import com.database.data.jpa.CommentService;
import com.database.data.jpa.procedure.ProcedureExecutor;

@Repository
@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> findAll() {
		return entityManager.createNativeQuery(
				"select * from table(load_comments)", Comment.class)
				.getResultList();
	}

	@Override
	public Comment findById(Long id) {
		try {
			return (Comment) entityManager
					.createNativeQuery(
							"select * from table(load_comments) where id = :id",
							Comment.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Long addComment(String text, Long idHotel, Long idClient,
			Long idPlace, Long idOffice) {
		return new ProcedureExecutor(entityManager, "add_comment")
				.in(String.class, Long.class, Long.class, Long.class,
						Long.class)
				.out(Long.class)
				.returnThis()
				.call(text, (idHotel == null) ? -1L : idHotel, idClient,
						(idPlace == null) ? -1L : idPlace,
						(idOffice == null) ? -1L : idOffice);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> findCommentsByOffice(Long officeId) {
		return entityManager
				.createNativeQuery(
						"select * from table(load_comments) where id_office = :idOffice", Comment.class)
				.setParameter("idOffice", officeId).getResultList();
	}

}
