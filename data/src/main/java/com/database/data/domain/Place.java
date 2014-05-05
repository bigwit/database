//package com.database.data.domain;
//
//import javax.persistence.*;
//
//import org.hibernate.mapping.Set;
//
//@Entity
//@Table(name = "places")
//@NamedQueries({
//	@NamedQuery(name = "Place.findAll", query = "select p from Place p"),
//	@NamedQuery(name = "Place.findById", query = "select p from Place p right join fetch p.places where p.id = :id")
//})
//public class Place {
//
//	@Id
//	@Column(name = "id")
//	private Long id;
//	
//	@Column(name = "name")
//	private String name;
//	
//	@Column(name = "description")
//	private String description;
//	
////	@ManyToOne
//	@JoinColumn(name = "id_location")
//	private Location location;
//	
////	@OneToMany
//	@JoinColumn(name = "id_place")
//	private Set places;
//	
//	public Place() {
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public Location getLocation() {
//		return location;
//	}
//
//	public void setLocation(Location location) {
//		this.location = location;
//	}
//
//	public Set getPlaces() {
//		return places;
//	}
//
//	public void setPlaces(Set places) {
//		this.places = places;
//	}
//	
//}
