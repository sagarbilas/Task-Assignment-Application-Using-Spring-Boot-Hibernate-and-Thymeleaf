package com.bilas.repositories;
import java.util.List;

import com.bilas.entities.Task;
import com.bilas.entities.User;

public interface TaskRepository{

	public List<Task> findByUser(String email);
	
	public void assignTask(Task task, User user);

}
