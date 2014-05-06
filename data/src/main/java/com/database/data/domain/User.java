package com.database.data.domain;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "hash_passwd")
	private String hashPasswd;
	
	@Column(name = "role")
	private String role;
	
	@ManyToOne
	@JoinColumn(name = "id_people")
	private People people;
	
	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHashPasswd() {
		return hashPasswd;
	}

	public void setHashPasswd(String hashPasswd) {
		this.hashPasswd = hashPasswd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}
}
