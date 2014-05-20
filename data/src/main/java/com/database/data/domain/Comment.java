package com.database.data.domain;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "text_comment")
	private String textComment;
	
	@ManyToOne
	@JoinColumn(name = "id_hotel")
	private Hotel hotel;
	
	@ManyToOne
	@JoinColumn(name = "id_people")
	private People people;
	
	@ManyToOne
	@JoinColumn(name = "id_place")
	private Place place;
	
	@ManyToOne
	@JoinColumn(name = "id_office")
	private Office office;
	
	public Comment() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTextComment() {
		return textComment;
	}

	public void setTextComment(String textComment) {
		this.textComment = textComment;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public People getClient() {
		return people;
	}

	public void setClient(People client) {
		this.people = client;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
}
