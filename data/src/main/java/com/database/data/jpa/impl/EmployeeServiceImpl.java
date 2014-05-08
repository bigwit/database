package com.database.data.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.database.data.domain.Employee;
import com.database.data.jpa.EmployeeService;
import com.database.data.jpa.procedure.ProcedureExecutor;

@SuppressWarnings("unchecked")
@Service("employeeService")
@Repository
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	@Override
	public List<Employee> findAll() {
		return entityManager.createNativeQuery(
				"select * from table(load_employees)", Employee.class)
				.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Employee finById(Long id) {
		try {
			return (Employee) entityManager
					.createNativeQuery(
							"select * from table(load_employees) where id = :id",
							Employee.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Long addEmployee(String position, Float salary, Long bonus,
			String status, String description, Long idOffice, Long idPeople,
			Long idCurrency) {
		return new ProcedureExecutor(entityManager, "add_employee")
				.in(String.class, Float.class, Long.class, String.class,
						String.class, String.class, Long.class, Long.class,
						Long.class)
				.out(Long.class)
				.returnThis()
				.call(position, salary, bonus, status, description, idOffice,
						idPeople, idCurrency);
	}

}
