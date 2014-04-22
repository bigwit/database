package com.database.data.domain;

import javax.persistence.*;

@Entity
@Table(name = "food")
@NamedQueries({
		@NamedQuery(name = "Food.findAll", query = "select f from Food f"),
		@NamedQuery(name = "Food.findById", query = "select distinct f from Food f where f.id = :id") })
public class Food {

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

	public Food() {
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

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "[food id: " + getId() + " type: " + getType() + " price: "
				+ getPrice() + " currency: " + getCurrency() + "]";
	}
}
