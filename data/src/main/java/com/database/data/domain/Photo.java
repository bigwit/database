package com.database.data.domain;

import javax.persistence.*;

import oracle.sql.BFILE;

@Entity
@Table(name = "photo")
public class Photo {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "photo")
	private BFILE photo;

	@Column(name = "text")
	private String text;

	@ManyToOne
	@JoinColumn(name = "id_people")
	private People people;

	@ManyToOne
	@JoinColumn(name = "id_hotel")
	private Hotel hotel;

	@ManyToOne
	@JoinColumn(name = "id_place")
	private Place place;

	public Photo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BFILE getPhoto() {
		return photo;
	}

	public void setPhoto(BFILE photo) {
		this.photo = photo;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}
}
