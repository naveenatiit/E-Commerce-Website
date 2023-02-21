package com.casestudy.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import com.casestudy.shoppingcart.entities.UserSignUpDetails;
import com.casestudy.shoppingcart.repository.UserAddressRepository;
import com.casestudy.shoppingcart.repository.UserProfileRepository;
import com.casestudy.shoppingcart.repository.UserSignUpDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.shoppingcart.entities.UserProfile;

@Service
public class UserProfileService {
	
	@Autowired
    UserProfileRepository userProfileRepository;
	@Autowired
    UserAddressRepository userAddressRepository;
	
	@Autowired
    UserSignUpDetailsRepository userSignUpDetailsRepository;


	public UserProfile updateProfile(UserProfile userProfile, int userId) {
		UserSignUpDetails user=userSignUpDetailsRepository.getById(userId);
		user.setName(userProfile.getName());
		user.setEmail(userProfile.getEmail());
		userSignUpDetailsRepository.save(user);
		try {
			UserProfile u= userProfileRepository.getProfileByUserId(userId);
			System.out.println(u);
			u.getAddress().setStreet(userProfile.getAddress().getStreet());
			u.getAddress().setCity(userProfile.getAddress().getCity());
			u.getAddress().setState(userProfile.getAddress().getState());
			u.getAddress().setPincode(userProfile.getAddress().getPincode());
			u.setEmail(userProfile.getEmail());
			u.setName(userProfile.getName());
			u.setPhone(userProfile.getPhone());
		return userProfileRepository.save(u);
		}
		catch(Exception e) {
		UserProfile u1=new UserProfile();
		u1.setAddress(userProfile.getAddress());
		u1.setEmail(userProfile.getEmail());
		u1.setName(userProfile.getName());
		u1.setPhone(userProfile.getPhone());
		userAddressRepository.save(u1.getAddress());
		return userProfileRepository.save(u1);
		}
	}


	public Optional<UserProfile> getProfile(int userId) {
		return userProfileRepository.findById(userId);
	}


	public Optional<UserProfile> getProfileByEmail(String email) {
		UserProfile currUserProfile=new UserProfile();
		List<UserProfile> allProfiles=	userProfileRepository.findAll();
		for (UserProfile userProfile : allProfiles) {
			if(userProfile.getEmail().equalsIgnoreCase(email)) {
				currUserProfile=userProfile;
				return Optional.ofNullable(currUserProfile);
				
			}
		}
		return Optional.ofNullable(currUserProfile);
	

	}

}
