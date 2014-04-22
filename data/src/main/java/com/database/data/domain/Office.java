package com.database.data.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "offices")
@NamedQueries({
	@NamedQuery(name = "Office.findAll", query = "select o from Office o"),
	@NamedQuery(name = "Office.findById", query = "select distinct o from Office o left join fetch o.contact c left join fetch c.location l where o.id = :id"),
	@NamedQuery(name = "Office.findAllWithDetails", query = "select distinct o from Office o left join fetch o.contact c left join fetch c.location l")
})
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
}
