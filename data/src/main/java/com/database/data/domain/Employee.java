package com.database.data.domain;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "position")
	private String position;

	@Column(name = "salary")
	private Float salary;

	@Column(name = "bonus")
	private Long bonus;

	@Column(name = "status")
	private String status;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "id_office")
	private Office office;

	@ManyToOne
	@JoinColumn(name = "id_people")
	private People people;

	@ManyToOne
	@JoinColumn(name = "id_currency")
	private Currency currency;

	public Employee() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Long getBonus() {
		return bonus;
	}

	public void setBonus(Long bonus) {
		this.bonus = bonus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
