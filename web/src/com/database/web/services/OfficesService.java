package com.database.web.services;

import com.database.web.services.providers.Office;
import com.database.web.services.stereotypes.OfficesProvider;

public class OfficesService implements OfficesProvider {

	@Override
	public Office[] getAllOffices() {
		// TODO Auto-generated method stub
		return stub();
	}
	
	private Office[] stub() {
		Office[] offices = new Office[3];
		
		offices[0] = new Office();
		offices[0].setName("SPb department");
		offices[0].setEmail("example@mail.com");
		offices[0].setPhone("8 981 3213218");
		offices[0].setAdress("Nevskiy prospect 55/a");
		
		offices[1] = new Office();
		offices[1].setName("NewYork department");
		offices[1].setEmail("example2@mail.com");
		offices[1].setPhone("9874568528");
		offices[1].setAdress("Main street 18");

		offices[2] = new Office();
		offices[2].setName("Asia tours");
		offices[2].setEmail("example3@mail.com");
		offices[2].setPhone("6546544558");
		offices[2].setAdress("Dundofmvi kra Abudabi 21");
		
		return offices;
	}
	
}
