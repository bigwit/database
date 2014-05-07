package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Contact;
import com.database.data.domain.Location;

public interface ContactService {

	List<Contact> findAll();
	
	List<Contact> findAllWithDetails();
	
	Contact findById(Long id);

	Long addContact(String phone, String email, String country, String city, String description);
}
