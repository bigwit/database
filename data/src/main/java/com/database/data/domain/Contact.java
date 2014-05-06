package com.database.data.domain;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@ManyToOne
	@JoinColumn(name = "id_location")
	private Location location;

	public Contact() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "[contact id: " + id + " email: " + email + " phone: " + phone
				+ " location: " + location + "]";
	}
}
