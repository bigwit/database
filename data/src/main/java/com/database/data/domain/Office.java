package com.database.data.domain;

import javax.persistence.*;

@Entity
@Table(name = "offices")
public class Office {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "id_contact")
	private Contact contact;

	public Office() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "[office id: " + id + ", name: " + name + ", contact: "
				+ contact + "]";
	}
}
