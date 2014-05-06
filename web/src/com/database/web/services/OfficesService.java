package com.database.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.database.data.jpa.OfficeService;
import com.database.data.domain.Office;
import com.database.web.services.stereotypes.OfficesProvider;

public class OfficesService implements OfficesProvider {

	@Autowired
	private OfficeService os;
	
	@Override
	public List<Office> getAllOffices() {
		return os.findAll();
	}
	
}
