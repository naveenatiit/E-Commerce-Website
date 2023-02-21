package com.casestudy.shoppingcart.controller;

import java.util.Collections;
import java.util.List;

import javax.management.AttributeNotFoundException;

import com.casestudy.shoppingcart.entities.UserLoginDetails;
import com.casestudy.shoppingcart.entities.UserProfile;
import com.casestudy.shoppingcart.entities.UserSignUpDetails;
import com.casestudy.shoppingcart.repository.UserAddressRepository;
import com.casestudy.shoppingcart.repository.UserLoginDetailsRepository;
import com.casestudy.shoppingcart.repository.UserSignUpDetailsRepository;
import com.casestudy.shoppingcart.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.shoppingcart.repository.UserProfileRepository;
import com.casestudy.shoppingcart.service.UserSignUpDetailsService;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class UserController {
	@Autowired
	UserProfileService userProfileService;
	@Autowired
	UserAddressRepository userAddressRepository;
	@Autowired
	UserSignUpDetailsService userSignUpDetailsService;
	@Autowired
    UserSignUpDetailsRepository userSignUpDetailsRepository;
	@Autowired
    UserLoginDetailsRepository userLoginDetailsRepository;
	@Autowired
	UserProfileRepository userProfileRepository;

	@GetMapping("/getAllUser")
	public List<UserSignUpDetails> getAllUser() {
		return userSignUpDetailsService.getAll();
	}

	@GetMapping("/allLoggedinUser")
	public List<UserLoginDetails> getAllLoggedinUser() {
		return userLoginDetailsRepository.findAll();
	}

	@PutMapping("/updateProfile/{userId}")
	public String updateProfile(@RequestBody UserProfile userProfile, @PathVariable("userId") int userId) {
		userProfileService.updateProfile(userProfile,userId);
		return "Your profile updated";
	}

	@GetMapping("/getProfile/{userId}")
	public ResponseEntity<UserProfile> getUserProfileById(@PathVariable(value = "userId" ) int userId)
			throws AttributeNotFoundException {
		UserProfile userProfile = userProfileService.getProfile(userId).orElseThrow();
		return ResponseEntity.ok().body(userProfile);
	}
	@GetMapping("/getProfileByEmail/{email}")
	public ResponseEntity<UserProfile> getUserProfileByEmail(@PathVariable String email)
			throws AttributeNotFoundException {
		UserProfile userProfile = userProfileService.getProfileByEmail(email).orElseThrow();

		return ResponseEntity.ok().body(userProfile);
	}

	@PutMapping("/forgotPassword/{email}")
	public void resetPassword(@RequestBody String password, @PathVariable String email) {
		userSignUpDetailsService.resetPassword(password, email);
	}

	@PostMapping("/signup")
	public int userSignup(@RequestBody UserSignUpDetails userSignUpDetails) {
		userSignUpDetailsService.addUser(userSignUpDetails);
		return userSignUpDetails.getId();
	}

	@PostMapping("/login")
	public String userLogin(@RequestBody UserLoginDetails userLoginDetails) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		List<UserLoginDetails> loginList=userLoginDetailsRepository.findAll();
		List<UserSignUpDetails> userList =  userSignUpDetailsService.getAll();
		String response="Either email or password is incorrect";
		
		for(UserSignUpDetails u : userList) {
			if(u.getEmail().equals(userLoginDetails.getEmail()) && encoder.matches(userLoginDetails.getPassword(), u.getPassword())) {
				response = "success";
			for(UserLoginDetails loginDetails: loginList) {
				if(loginDetails.getEmail().equals(u.getEmail())) {
					return response;
				}
			}
				userLoginDetails.setUserId(u.getId());
				userLoginDetails.setPassword(u.getPassword());
				userLoginDetailsRepository.save(userLoginDetails);
				break;
			}
		}
		return response;
	}

	@GetMapping("/logout/{userId}")
	public ResponseEntity<UserSignUpDetails> getUserById(@PathVariable("userId") int userid)
			throws AttributeNotFoundException {
		UserSignUpDetails userSignUpDetails = userSignUpDetailsRepository.findById(userid).orElseThrow();

		String userEmail = userSignUpDetails.getEmail();
		boolean isUserlogedIn = false;

		List<UserLoginDetails> ulist = userLoginDetailsRepository.findAll();

		for (UserLoginDetails loginUser : ulist) {
			if (loginUser.getEmail().equals(userEmail)) {
				isUserlogedIn = true;
				userLoginDetailsRepository.delete(loginUser);
				break;
			}
		}
		if (!isUserlogedIn)
			return (ResponseEntity<UserSignUpDetails>) ResponseEntity.badRequest();
		return ResponseEntity.ok().body(userSignUpDetails);
	}

	@GetMapping("/getUserByEmail/{email}")
	public UserSignUpDetails getUserByEmail(@PathVariable String email) throws AttributeNotFoundException {
		UserSignUpDetails user = userSignUpDetailsService.getUserByEmail(email);
		return user;
	}

	@DeleteMapping("/deleteAccount/{id}")
	public String deleteAccount(@PathVariable int id) {
		userSignUpDetailsRepository.deleteById(id);
		userProfileRepository.deleteById(id);
		userLoginDetailsRepository.deleteById(id);
		return "Account Deleted";

	}

}
