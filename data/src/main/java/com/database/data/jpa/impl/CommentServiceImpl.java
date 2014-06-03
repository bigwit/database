package com.database.data.jpa.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Comment;
import com.database.data.jpa.CommentService;
import com.database.data.jpa.procedure.ProcedureExecutor;
import com.hazelcast.core.IMap;

@Repository
@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static Logger log = Logger.getLogger(CommentServiceImpl.class.getName());
	
	protected IMap<String, Object> storage;
	
	public void setStorage(IMap<String, Object> stor) {
		this.storage = stor;
	}
	
	private Object selectCommentByOffice(Long id) {
		Object res = null;
		log.info("START SELECT. Time = " + new Date().getTime());
		res = storage.get(id.toString());
		if(res == null || ((List<Comment>)res).size() < 1) {
			res = findByOffice(id);
			storage.putIfAbsent(id.toString(), res, 10L, TimeUnit.MINUTES);
		}
		log.info("FINISH SELECT. Time = " + new Date().getTime());
		return res;
	}

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
	public java.util.List<Comment> findCommentsByOffice(Long officeId) {
		return (List<Comment>) selectCommentByOffice(officeId);
	}
	
	@SuppressWarnings("unchecked")
	private List<Comment> findByOffice(Long officeId) {
		return entityManager
				.createNativeQuery(
						"select * from table(load_comments) where id_office = :idOffice", Comment.class)
				.setParameter("idOffice", officeId).getResultList();
	}

}
