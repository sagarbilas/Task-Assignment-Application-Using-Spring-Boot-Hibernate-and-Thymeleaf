package com.bilas.services;

import java.util.List;

import com.bilas.entities.Task;
import com.bilas.entities.User;

public interface TaskService {

	public void assignTask(Task task, User user);
	
	public List<Task> findUserTask(String email);
	
}
