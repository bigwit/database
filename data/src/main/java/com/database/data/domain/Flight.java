package com.database.data.domain;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "flights")
public class Flight {

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "leaving_date")
	private Date leavingDate;
	
	@Column(name = "arrival_date")
	private Date arrivalDate;
	
	@Column(name = "type_transport")
	private String typeTransport;
	
	@Column(name = "price")
	private Float price;
	
	@ManyToOne
	@JoinColumn(name = "id_tour")
	private Tour tour;
	
	@ManyToOne
	@JoinColumn(name = "id_location_to")
	private Location locationTo;
	
	@ManyToOne
	@JoinColumn(name = "id_location_from")
	private Location locationFrom;
	
	@ManyToOne
	@JoinColumn(name = "id_currency")
	private Currency currency;
	
	public Flight() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(Date leavingDate) {
		this.leavingDate = leavingDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getTypeTransport() {
		return typeTransport;
	}

	public void setTypeTransport(String typeTransport) {
		this.typeTransport = typeTransport;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public Location getLocationTo() {
		return locationTo;
	}

	public void setLocationTo(Location locationTo) {
		this.locationTo = locationTo;
	}

	public Location getLocationFrom() {
		return locationFrom;
	}

	public void setLocationFrom(Location locationFrom) {
		this.locationFrom = locationFrom;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
