package com.bilas.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Task {
	@Id
	@GeneratedValue
	private long id;
	@NotEmpty
	private String taskTitle;
	@NotEmpty

	private String startDate;
	@NotEmpty

	private String stopDate;
	@NotEmpty
	@Column(length=1000)
	private String description;

	@ManyToOne
	@JoinColumn(name="USER_EMAIL")
	private User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startTDate) {
		this.startDate = startTDate;
	}

	public String getStopDate() {
		return stopDate;
	}

	public void setStopDate(String stopDate) {
		this.stopDate = stopDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	//constructors
	public Task(String taskTitle, String startTDate, String stopDate, String description, User user) {
		this.taskTitle = taskTitle;
		this.startDate = startTDate;
		this.stopDate = stopDate;
		this.description = description;
		this.user = user;
	}

	public Task(String taskTitle, String startTDate, String stopDate, String description) {
		this.taskTitle = taskTitle;
		this.startDate = startTDate;
		this.stopDate = stopDate;
		this.description = description;
	}

	public Task() {
	}
	
	
	
	
	
}
