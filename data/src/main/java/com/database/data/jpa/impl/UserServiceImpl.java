package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Contact;
import com.database.data.domain.Location;
import com.database.data.domain.People;
import com.database.data.domain.User;
import com.database.data.jpa.UserService;
import com.database.data.jpa.procedure.ProcedureExecutor;

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
		try {
			return (User) entityManager
					.createNativeQuery(
							"select * from table(load_users) where id = :id",
							User.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Long addUser(User user) {
		StoredProcedureQuery query = entityManager
				.createStoredProcedureQuery("add_user")
				.registerStoredProcedureParameter(1, String.class,
						ParameterMode.IN)
				.setParameter(1, user.getLogin())
				.registerStoredProcedureParameter(2, String.class,
						ParameterMode.IN)
				.setParameter(2, user.getHashPasswd())
				.registerStoredProcedureParameter(3, String.class,
						ParameterMode.IN)
				.setParameter(3, user.getRole())
				.registerStoredProcedureParameter(4, Long.class,
						ParameterMode.IN)
				.setParameter(4, user.getPeople().getId())
				.registerStoredProcedureParameter(5, Long.class,
						ParameterMode.OUT);
		query.execute();
		return (Long) query.getOutputParameterValue(5);
	}

	@Override
	public User getUser(String login, String hashPasswd) {
		try {
			return (User) entityManager
					.createNativeQuery(
							"select * from table(load_users) u where u.login = :login and u.hash_passwd = :hashPasswd",
							User.class).setParameter("login", login)
					.setParameter("hashPasswd", hashPasswd).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Long addUser(String login, String hashPasswd, String role,
			People people) {
		User user = new User();
		user.setLogin(login);
		user.setHashPasswd(hashPasswd);
		user.setRole(role);
		user.setPeople(people);
		return addUser(user);
	}

	public Long registerUser(User user) {
		People people = user.getPeople();
		Contact contact = people.getContact();
		Location location = contact.getLocation();
		return new ProcedureExecutor(entityManager, "register_user")
				.in(user.getLogin()).in(user.getHashPasswd())
				.in(people.getFirstName()).in(people.getMiddleName())
				.in(people.getLastName()).in(people.getDateBirth())
				.in(people.getSex()).in(contact.getPhone())
				.in(contact.getEmail()).in(location.getCountry())
				.in(location.getCity()).in(location.getDescription())
				.out(Long.class).execute().getOut(13, Long.class);
	}
}
