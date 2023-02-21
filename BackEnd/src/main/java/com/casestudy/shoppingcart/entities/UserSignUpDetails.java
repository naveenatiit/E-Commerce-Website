package com.casestudy.shoppingcart.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usersignupdetails")
public class UserSignUpDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int Id;
	private String name;
	private String email;
	private String password;
	
	public UserSignUpDetails(int id, String name, String email, String password) {
		super();
		Id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public UserSignUpDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
