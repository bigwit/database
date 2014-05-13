package com.database.data.jpa;

import java.util.List;

import com.database.data.domain.Contact;

public interface ContactService {

	List<Contact> findAll();
	
	List<Contact> findAllWithDetails();
	
	Contact findById(Long id);

	Long addContact(String phone, String email, String country, String city, String description);
}
