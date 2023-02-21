package com.casestudy.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.casestudy.shoppingcart.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>, CrudRepository<Order, Integer> {


}
