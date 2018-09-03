package com.bilas.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Role {

	@Id
	private String name;

	/*
	 * an User can have many Role and many Role also Belong To one User. so there is
	 * a many to many relationship between USER & Role table
	 */

	@ManyToMany(mappedBy = "roles")
	/*
	 * @JoinTable(name= "USER_ROLES", joinColumns= {
	 * 
	 * @JoinColumn(name= "USER_EMAIL", referencedColumnName="email") },
	 * inverseJoinColumns= {@JoinColumn(name= "ROLE_NAME",
	 * referencedColumnName="name")} )
	 */

	private List<User> users;

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	// Constructor
	public Role(String name, List<User> users) {
		this.name = name;
		this.users = users;
	}

	// Default Constructor
	public Role() {

	}

	public Role(String name) {
		this.name = name;
	}

}
