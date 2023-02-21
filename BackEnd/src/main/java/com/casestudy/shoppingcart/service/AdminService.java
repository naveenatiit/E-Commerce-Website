package com.casestudy.shoppingcart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.casestudy.shoppingcart.entities.AdminLoginDetails;
import com.casestudy.shoppingcart.repository.AdminLoginDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateOperations;
import org.springframework.stereotype.Service;

import com.casestudy.shoppingcart.entities.Admin;
import com.casestudy.shoppingcart.repository.AdminRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.AttributeNotFoundException;

@Service
public class AdminService{

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	AdminLoginDetailsRepository adminLoginDetailsRepository;

	public String signup(Admin admin) {
		if(admin.getName() == null || admin.getEmail() == null || admin.getPassword() == null) {
			return "Please Enter all value";
		}
		else if(adminRepository.findByemail(admin.getEmail()) != null) return "Your email already exist";

		adminRepository.save(admin);
		return "new Admin added ! your id is: " + admin.getId();
	}

	public List<String> login(String email, String password) {
		AdminLoginDetails adminLoginDetails=new AdminLoginDetails();
		Admin admin = adminRepository.findByemail(email);
		List<String> list = new ArrayList<>();
		if(admin == null || !admin.getPassword().equals(password)) {
			list.add("wrong");
			return list;
		}
		adminLoginDetails.setEmail(email);
		adminLoginDetails.setPassword(password);
		adminLoginDetailsRepository.save(adminLoginDetails);
		list.add("login working");
		return list;
	}

	public String logout(String email) {
		List<AdminLoginDetails> allLoggedin =adminLoginDetailsRepository.findAll();
		for(AdminLoginDetails adminLoginDetails:allLoggedin) {
			if(adminLoginDetails.getEmail().equalsIgnoreCase(email)){
				adminLoginDetailsRepository.delete(adminLoginDetails);
			}
		}
		return "logout successfull";
	}

	public ResponseEntity<Admin> getAdminById(int adminId)
			throws AttributeNotFoundException {
		Admin admin = adminRepository.findById(adminId)
				.orElseThrow();
		return ResponseEntity.ok().body(admin);
	}

	public String removeAdmin(int adminId)  throws AttributeNotFoundException{
		try {
			adminRepository.deleteById(adminId);
			return "admin removed from";
		}catch(Exception e){
			System.out.print("No Id present");
		}
		return "Admin removed";
	}
	public Admin getAdminByEmail(String email) {
		Admin currAdmin = adminRepository.findByemail(email);
		return currAdmin;
	}

	
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	
	public List<Admin> getAllAdmin() {
		return adminRepository.findAll();
	}

	
	public Optional<Admin> getAdmin(Integer id) {
		return adminRepository.findById(id);
	}
	
	
	
}
