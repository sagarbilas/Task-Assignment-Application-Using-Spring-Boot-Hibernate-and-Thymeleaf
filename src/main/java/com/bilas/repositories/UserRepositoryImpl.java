package com.bilas.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.bilas.entities.Role;
import com.bilas.entities.User;


@Repository
public class UserRepositoryImpl implements UserRepository{
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public void user_creation(User user) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction transaction = session.beginTransaction();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		Role userRole = new Role("USER");

		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		
		// save or update the customer
		session.saveOrUpdate(user);
		transaction.commit();
	}

	@Override
	public void admin_creation(User user) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction transaction = session.beginTransaction();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		Role userRole = new Role("ADMIN");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);

		session.saveOrUpdate(user);
		transaction.commit();
	}
	
	
	
	@Override
	public User getUserByEmail(String email) {

		Session currentSession = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction transaction = currentSession.beginTransaction();
		User theUser = currentSession.get(User.class, email);
		transaction.commit();
		return theUser;
		
	}

	@Override
	public List<User> findByName(String theSearchName) {
		Session currentSession = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction transaction = currentSession.beginTransaction();
		
		Query theQuery = null;
		if (theSearchName != null && theSearchName.trim().length() > 0) {
			theQuery = currentSession.createQuery("from User where lower(name) like :theName");
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		} else {
			theQuery = currentSession.createQuery("from User");
		}
		List users = theQuery.list();
		transaction.commit();
		
		return users;	
	}
	
	
	@Override
	public List<User> findAll(){
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Query theQuery = session.createQuery("from User order by name");
		List<User> users = theQuery.list();
		return users;
	}
	
	
	@Override
	public void deleteCustomer(String email) {
		Session currentSession = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Query theQuery = currentSession.createQuery("delete from User where user_email=:theEmail");

		Transaction transaction = currentSession.beginTransaction();
		theQuery.setParameter("theEmail", email);
		theQuery.executeUpdate();

		transaction.commit();
	}
	
	
	
	
}
