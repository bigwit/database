package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Employee;

public interface EmployeeService {

	List<Employee> findAll();
	
	Employee finById(Long id);
	
}
