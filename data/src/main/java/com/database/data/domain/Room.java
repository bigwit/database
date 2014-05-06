package com.database.data.domain;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

	@Id
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_hotel")
	private Hotel hotel;
	
	@ManyToOne
	@JoinColumn(name = "id_food")
	private Food food;
	
	@ManyToOne
	@JoinColumn(name = "id_placement")
	private Placement placement;

	public Room() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Placement getPlacement() {
		return placement;
	}

	public void setPlacement(Placement placement) {
		this.placement = placement;
	}
	
}
