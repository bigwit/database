package com.database.data.domain;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "hotels")
public class Hotel {

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "rating_hotel")
	private String ratingHotel;
	
	@Column(name = "arrival_date")
	private Date arrivalDate;
	
	@Column(name = "leaving_date")
	private Date leavingDate;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "id_tour")
	private Tour tour;
	
	@ManyToOne
	@JoinColumn(name = "id_location")
	private Location location;
	
	public Hotel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRatingHotel() {
		return ratingHotel;
	}

	public void setRatingHotel(String ratingHotel) {
		this.ratingHotel = ratingHotel;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(Date leavingDate) {
		this.leavingDate = leavingDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
