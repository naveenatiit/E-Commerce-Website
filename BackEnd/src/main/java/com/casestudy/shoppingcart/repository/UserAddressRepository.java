package com.casestudy.shoppingcart.repository;

import com.casestudy.shoppingcart.entities.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Integer> {

}
