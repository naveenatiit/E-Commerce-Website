package com.casestudy.shoppingcart.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserLoginDetails")
public class UserLoginDetails {

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int userId;
	private String email;
	private String password;
	
	public UserLoginDetails(String email, String password,int userId) {
		super();
		this.userId=userId;
		this.email = email;
		this.password = password;
	}

	public UserLoginDetails() {
		super();
		// TODO Auto-generated constructor stub
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
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int i) {
		this.userId = i;
	}
}
