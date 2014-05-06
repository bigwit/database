package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Comment;
import com.database.data.jpa.CommentService;

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
		return (Comment) entityManager
				.createNativeQuery(
						"select * from table(load_comments) where id = :id",
						Comment.class).setParameter("id", id).getSingleResult();
	}

}
