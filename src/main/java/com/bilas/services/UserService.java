package com.bilas.services;

import java.util.List;

import com.bilas.entities.User;

public interface UserService {
	
	public void user_creation(User user);
	
	public void admin_creation(User user);
	
	public User getUserByEmail(String email);
	
	public boolean isUserPresent(String email);
	
	public List<User> findAll();
	
	public List<User> findByName(String theSearchName);
	
	public void deleteCustomer(String email);

}
