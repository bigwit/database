package com.database.data.domain;

import javax.persistence.*;

@Entity
@Table(name = "currency")
@NamedQueries({
	@NamedQuery(name = "Currency.findAll", query = "select c from Currency c"),
	@NamedQuery(name = "Currency.findById", query = "select c from Currency c where c.id = :id")
})
public class Currency {

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "description")
	private String description;

	public Currency() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "[currency id: " + getId() + " description: " + getDescription() + "]";
	}
}
