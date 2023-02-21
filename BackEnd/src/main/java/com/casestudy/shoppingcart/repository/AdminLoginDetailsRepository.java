package com.casestudy.shoppingcart.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.casestudy.shoppingcart.entities.AdminLoginDetails;


public interface AdminLoginDetailsRepository extends JpaRepository<AdminLoginDetails, Integer>, CrudRepository<AdminLoginDetails, Integer> {

}
