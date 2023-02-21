package com.casestudy.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.casestudy.shoppingcart.entities.UserLoginDetails;

public interface UserLoginDetailsRepository extends JpaRepository<UserLoginDetails, Integer>, CrudRepository<UserLoginDetails, Integer> {

	UserLoginDetails findByemail(String email);


}
