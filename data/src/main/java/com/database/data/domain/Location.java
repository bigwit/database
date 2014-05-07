package com.database.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "locations")
public class Location {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "country")
	private String country;

	@Column(name = "city")
	private String city;

	@Column(name = "description")
	private String description;

	public Location() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "[location id: " + id + " country: " + country + " city: "
				+ city + " description: " + description + "]";
	}
}
