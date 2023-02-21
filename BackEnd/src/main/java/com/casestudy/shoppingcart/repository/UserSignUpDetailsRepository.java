package com.casestudy.shoppingcart.repository;

import com.casestudy.shoppingcart.entities.UserSignUpDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserSignUpDetailsRepository extends JpaRepository<UserSignUpDetails, Integer> , CrudRepository<UserSignUpDetails, Integer>{

	public UserSignUpDetails getById(int userId);

	@Query(value = "select max(id) from usersignupdetails" , nativeQuery = true)
	public int getMaxId();

	public UserSignUpDetails getUserSignUpdetailsByEmail(String email);




}
