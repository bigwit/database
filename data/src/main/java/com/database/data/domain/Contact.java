package com.database.data.domain;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
@NamedQueries({
	@NamedQuery(name = "Contact.findAll", query = "select c from Contact c"),
	@NamedQuery(name = "Contact.findById", query = "select c from Contact c where c.id = :id"),
	@NamedQuery(name = "Contact.findAllWithDetails", query = "select distinct c from Contact c left join fetch ")
})
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
}
