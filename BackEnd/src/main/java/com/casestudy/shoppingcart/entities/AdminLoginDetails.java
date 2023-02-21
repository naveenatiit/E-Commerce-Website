package com.casestudy.shoppingcart.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AdminLoginDetails {
	@Id
	private String email;
	private String password;

	public AdminLoginDetails() {
	super();
	}
	public AdminLoginDetails(String email, String password) {
	super();
	this.email = email;
	this.password = password;
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
