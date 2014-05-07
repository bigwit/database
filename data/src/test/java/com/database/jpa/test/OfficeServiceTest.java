package com.database.jpa.test;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.database.data.domain.Contact;
import com.database.data.domain.Location;
import com.database.data.domain.Office;
import com.database.data.jpa.OfficeService;

public class OfficeServiceTest {
	
	private static GenericXmlApplicationContext context;
	
	private OfficeService officeService;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		context = new GenericXmlApplicationContext("spring/db-context.xml");
	}
	
	@Before
	public void setUp() {
		officeService = context.getBean("officeService", OfficeService.class);
	}

	@Ignore
	@Test
	public void testAddOffice() {
		Office office = new Office();
		office.setId(0L);
		office.setName("myTestOffice");
		Contact contact = new Contact();
		contact.setId(0L);
		contact.setEmail("test@email.com");
		contact.setPhone("8(888)888-88-88");
		
		Location location = new Location();
		location.setId(0L);
		location.setCountry("Russia");
		location.setCity("Orel");
		location.setDescription("");
		
		contact.setLocation(location);
		office.setContact(contact);
		System.out.println("\n\n\n\n\n" + office);
		Long ident = officeService.addOffice(office);
		assertNotNull(ident);
		
		Office loaded = officeService.findById(ident);
		assumeNotNull(loaded);

		assertThat(loaded.getId(), is(ident));
		assertThat(loaded.getName(), is(office.getName()));
	}

	@AfterClass
	public static void tearDownAfterClass() {
		context.close();
	}
}
