package com.bk.kabow.service.user;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bk.kabow.entity.User;
import com.bk.kabow.entity.userRegistration;

public interface UserService extends UserDetailsService{

	public List<User> findAll();
	
	public User findById(int theId);
	
	public void save(User theUser);
	
	public void deleteById(int theId);
	
	public User registration(userRegistration userRegistration);
	
	public User findByEmail(String email);
	
	public void updateInfo(String name, int yearOfBirth, String address, int phoneNunber, int id);
	
	public void changePassword(String password, int id);
}
