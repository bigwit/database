package com.database.data.jpa;

import java.sql.Date;
import java.util.List;

import com.database.data.domain.People;

public interface PeopleService {

	List<People> findAll();

	List<People> findAllWithDetails();

	People findById(Long id);

	Long addPeople(String firstName, String middleName, String lastName,
			Date dateBirth, String sex, Long idContact);
}
