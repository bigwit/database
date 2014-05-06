package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.User;
import com.database.data.jpa.UserService;

@Repository
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return entityManager.createNativeQuery(
				"select * from table(load_users)", User.class).getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public User findById(Long id) {
		return (User) entityManager
				.createNativeQuery(
						"select * from table(load_users) where id = :id",
						User.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public Long addUser(User user) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("add_user")
				.setParameter("login", user.getLogin())
				.setParameter("hash_passwd", user.getHashPasswd())
				.setParameter("role", user.getRole())
				.setParameter("id_people", user.getPeople().getId());
		query.execute();
		return (Long) query.getOutputParameterValue("ident");
	}

}
