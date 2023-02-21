package com.casestudy.shoppingcart.repository;

import com.casestudy.shoppingcart.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer>, CrudRepository<Admin, Integer>{

	Admin findByemail(String email);
}
