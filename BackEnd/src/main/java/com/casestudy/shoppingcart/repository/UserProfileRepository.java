package com.casestudy.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casestudy.shoppingcart.entities.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

	UserProfile getProfileByUserId(int userId);



}
