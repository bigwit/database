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

	/**
	 * @return оплачена ли поездка
	 */
	public boolean isPayment() {
		return (getDatePayment() != null);
	}

	/**
	 * @return название офиса, где была куплена поездка
	 */
	public String getOfficeName() {
		return getTour().getOffice().getName();
	}

	/**
	 * @return имя сотрудника, продавшего путевку
	 */
	public String getFirstNameEmployee() {
		return getClient().getEmployee().getPeople().getFirstName();
	}

	/**
	 * @return отчество сотрудника, продавшего путевку
	 */
	public String getMiddleNameEmployee() {
		return getClient().getEmployee().getPeople().getMiddleName();
	}

	/**
	 * @return фамилия сотрудника, продавшего путевку
	 */
	public String getLastNameEmployee() {
		return getClient().getEmployee().getPeople().getLastName();
	}

	/**
	 * @return название тура
	 */
	public String getTourName() {
		return getTour().getName();
	}

	/**
	 * @return имя целевого места
	 */
	public String getTargetPlaceName() {
		return getTour().getPlace().getName();
	}
}
