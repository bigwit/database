package com.database.data.domain;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "travels")
public class Travel {

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "date_payment")
	private Date datePayment;
	
	@Column(name = "number_adults")
	private Long numberAdults;
	
	@Column(name = "number_child")
	private Long numberChild;
	
	@ManyToOne
	@JoinColumn(name = "id_client")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "id_tour")
	private Tour tour;
	
	public Travel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}

	public Long getNumberAdults() {
		return numberAdults;
	}

	public void setNumberAdults(Long numberAdults) {
		this.numberAdults = numberAdults;
	}

	public Long getNumberChild() {
		return numberChild;
	}

	public void setNumberChild(Long numberChild) {
		this.numberChild = numberChild;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}
}
