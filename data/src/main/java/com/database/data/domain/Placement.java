package com.database.data.domain;

import javax.persistence.*;

@Entity
@Table(name = "placement")
@NamedQueries({
		@NamedQuery(name = "Placement.findAll", query = "select p from Placement p"),
		@NamedQuery(name = "Placement.findById", query = "select distinct p from Placement p left join fetch p.currency where p.id = :id"),
		@NamedQuery(name = "Placement.findAllWithDetails", query = "select distinct p from Placement p left join fetch p.currency") })
public class Placement {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "type")
	private String type;

	@Column(name = "price")
	private Float price;

	@ManyToOne
	@JoinColumn(name = "id_currency")
	private Currency currency;

	public Placement() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "[placement id: " + getId() + " type: " + getType() + " price: "
				+ getPrice() + " currency: " + getCurrency() + "]";
	}

}
