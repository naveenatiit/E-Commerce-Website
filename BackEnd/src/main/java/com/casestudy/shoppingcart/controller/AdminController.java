package com.casestudy.shoppingcart.controller;

import java.util.List;

import javax.management.AttributeNotFoundException;

import com.casestudy.shoppingcart.entities.Admin;
import com.casestudy.shoppingcart.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.casestudy.shoppingcart.repository.AdminLoginDetailsRepository;
import com.casestudy.shoppingcart.repository.AdminRepository;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/signup")
	public String signup(@RequestBody Admin admin) {
		return adminService.signup(admin);
		}
	
   @PostMapping("/login/{email}")
   public List<String>  login(@PathVariable String email,@RequestBody String password) {
	   return adminService.login(email, password);
   }
   @GetMapping("/logout/{email}")
   public String logout(@PathVariable String email) {
	   return adminService.logout(email);
   }
	
	@GetMapping("/getAll")
	public List<Admin> getAll(){
		return adminService.getAllAdmin();
	}
	@GetMapping("/getAdmin/{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable(value = "id") int adminId)
	 throws AttributeNotFoundException {
		return adminService.getAdminById(adminId);
	}
	
	@GetMapping("/removeAdmin/{id}")
	public String removeAdmin(@PathVariable(value = "id") int adminId)  throws AttributeNotFoundException{
		return adminService.removeAdmin(adminId);
	}
	
	@PostMapping("/getAdminByEmail")
	public Admin getAdminByEmail(@RequestBody String email) {
		return adminService.getAdminByEmail(email);
	}
	
}
