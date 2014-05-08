package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Employee;

public interface EmployeeService {

	List<Employee> findAll();

	Employee finById(Long id);

	/*
	 * POSITION VARCHAR2, SALARY FLOAT, BONUS INTEGER, STATUS VARCHAR2,
	 * DESCRIPTION VARCHAR2, ID_OFFICE INTEGER , ID_PEOPLE INTEGER , ID_CURRENCY
	 * INTEGER,
	 */

	Long addEmployee(String position, Float salary, Long bonus, String status,
			String description, Long idOffice, Long idPeople, Long idCurrency);

}
