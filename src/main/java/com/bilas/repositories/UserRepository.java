package com.bilas.repositories;
import java.util.List;
import com.bilas.entities.User;

public interface UserRepository {

	public void user_creation(User user);
	public void admin_creation(User user);
	public User getUserByEmail(String email);
	public List<User> findByName(String theSearchName);
	
	public List<User> findAll();
	
	public void deleteCustomer(String email);
}
