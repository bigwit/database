package com.database.web.services;

import java.util.List;

import com.database.data.jpa.OfficeService;
import com.database.data.jpa.impl.OfficeServiceImpl;
import com.database.data.domain.Office;
import com.database.web.services.stereotypes.OfficesProvider;

public class OfficesService implements OfficesProvider {

	private OfficeService os;
	
	@Override
	public List<Office> getAllOffices() {
		return getOfficeService().findAll();
	}
	
	private OfficeService getOfficeService() {
		if(os == null) {
			os = new OfficeServiceImpl();
		}
		return os;
	}
	
}
