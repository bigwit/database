package com.database.jpa.test;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.database.data.domain.Location;
import com.database.data.jpa.LocationService;

public class LocationServiceTest {

	private static GenericXmlApplicationContext context;
	
	private LocationService locationService;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		context = new GenericXmlApplicationContext("spring/db-context.xml");
	}
	
	@Before
	public void setUp() {
		locationService = context.getBean("locationService", LocationService.class);
	}
	
	@Test
	public void testAddLocation() {
		Location location = new Location();
		location.setCity("Orel");
		location.setCountry("Russia");
		location.setDescription("");
		
		Long ident = locationService.addLocation(location);
		assertNotNull(ident);
		location.setId(ident);
		
		Location loaded = locationService.findById(ident);
		assumeNotNull(loaded);
		
		assertThat(location.toString(), is(loaded.toString()));
	}

	@AfterClass
	public static void tearDownAfterClass() {
		context.close();
	}
}
