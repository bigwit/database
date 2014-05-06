package com.database.data.domain;

import javax.persistence.*;

@Entity
@Table(name = "currency")
public class Currency {

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "rate")
	private Float rate;

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

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}
	
	@Override
	public String toString() {
		return "[currency id: " + getId() + " description: " + getDescription() + "]";
	}
}
