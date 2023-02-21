package com.casestudy.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.casestudy.shoppingcart.entities.UserAddress;
import com.casestudy.shoppingcart.entities.UserProfile;
import com.casestudy.shoppingcart.entities.UserSignUpDetails;
import com.casestudy.shoppingcart.repository.UserAddressRepository;
import com.casestudy.shoppingcart.repository.UserProfileRepository;
import com.casestudy.shoppingcart.repository.UserSignUpDetailsRepository;

@Service
public class UserSignUpDetailsService {
	
	@Autowired
	UserSignUpDetailsRepository userSignUpDetailsRepository;

	@Autowired
	UserProfileRepository userProfileRepository;
	
	@Autowired
	UserAddressRepository userAddressRepository;

	public void addUser(UserSignUpDetails userSignUpDetails) {
		BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
		userSignUpDetails.setPassword(passwordEncoder.encode(userSignUpDetails.getPassword()));
		userSignUpDetailsRepository.save(userSignUpDetails);
		UserAddress userAddress = new UserAddress();
		userAddress.setCity("default");
		userAddress.setStreet("default");
		userAddress.setState("default");
		userAddress.setPincode("default");
		
		UserProfile userProfile = new UserProfile();
		userProfile.setUserId(userSignUpDetailsRepository.getMaxId());
		userProfile.setName(userSignUpDetails.getName());
		userProfile.setEmail(userSignUpDetails.getEmail());
		
		userAddressRepository.save(userAddress);
		userProfile.setAddress(userAddress);

		userProfileRepository.save(userProfile);

	}

	public List<UserSignUpDetails> getAll() {
		// TODO Auto-generated method stub
		return userSignUpDetailsRepository.findAll();
	}

	public Optional<UserSignUpDetails> getUserById(Integer userId) {
		return userSignUpDetailsRepository.findById(userId);

	}

	public void resetPassword(String password, String email) {
		UserSignUpDetails u = getUserByEmail(email);
		u.setPassword(password);
		userSignUpDetailsRepository.save(u);
	}

	public UserSignUpDetails getUserByEmail(String email) {
		UserSignUpDetails currUser = new UserSignUpDetails();
		List<UserSignUpDetails> allUsers = userSignUpDetailsRepository.findAll();
		for (UserSignUpDetails user : allUsers) {
			if (user.getEmail().equalsIgnoreCase(email)) {
				currUser = user;
			break;
			}
		}
		return currUser;
	}

}
