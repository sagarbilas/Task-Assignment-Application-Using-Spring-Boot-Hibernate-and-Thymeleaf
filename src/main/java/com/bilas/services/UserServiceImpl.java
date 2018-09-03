package com.bilas.services;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bilas.entities.Role;
import com.bilas.entities.User;
import com.bilas.repositories.UserRepository;

//The idea behind creating a Service layer is to separate the UI logic and Business logic. 
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public void user_creation(User user) {
		// TODO Auto-generated method stub
		userRepository.user_creation(user);
	}
	
	@Override
	@Transactional
	public void admin_creation(User user) {
		// TODO Auto-generated method stub
		userRepository.admin_creation(user);
	}


	@Override
	@Transactional
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.getUserByEmail(email);
	}
	
	
	public boolean isUserPresent(String email) {
		User u = userRepository.getUserByEmail(email);

		if (u != null)
			return true;
		return false;
	}


	@Override
	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}
	

	@Override
	@Transactional
	public List<User> findByName(String theSearchName) {
		return userRepository.findByName(theSearchName);
	}
	
	@Override
	@Transactional
	public void deleteCustomer(String email) {
		userRepository.deleteCustomer(email);
	}
	
}
