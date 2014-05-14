package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Client;

public interface ClientService {

	List<Client> findAll();
	
	Client findById(Long id);
	
	Client findByPeopleId(Long peopleId);
	
}
