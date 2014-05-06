package com.database.data.domain;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "serial_passport")
	private Long serialPassport;

	@Column(name = "number_passport")
	private Long numberPassport;

	@Column(name = "number_visa")
	private Long numberVisa;

	@Column(name = "issue_data_visa")
	private Date issueDataVisa;

	@Column(name = "period_valid_visa")
	private Date periodValidVisa;

	@ManyToOne
	@JoinColumn(name = "id_employee")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "id_people")
	private People people;
	
	public Client() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSerialPassport() {
		return serialPassport;
	}

	public void setSerialPassport(Long serialPassport) {
		this.serialPassport = serialPassport;
	}

	public Long getNumberPassport() {
		return numberPassport;
	}

	public void setNumberPassport(Long numberPassport) {
		this.numberPassport = numberPassport;
	}

	public Long getNumberVisa() {
		return numberVisa;
	}

	public void setNumberVisa(Long numberVisa) {
		this.numberVisa = numberVisa;
	}

	public Date getIssueDataVisa() {
		return issueDataVisa;
	}

	public void setIssueDataVisa(Date issueDataVisa) {
		this.issueDataVisa = issueDataVisa;
	}

	public Date getPeriodValidVisa() {
		return periodValidVisa;
	}

	public void setPeriodValidVisa(Date periodValidVisa) {
		this.periodValidVisa = periodValidVisa;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}
}
