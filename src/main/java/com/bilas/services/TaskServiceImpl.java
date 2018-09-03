package com.bilas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bilas.entities.Task;
import com.bilas.entities.User;
import com.bilas.repositories.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Override
	@Transactional
	public void assignTask(Task task, User user)
	{
		taskRepository.assignTask(task, user);
	}
	
	
	@Override
	@Transactional
	public List<Task> findUserTask(String email)
	{
		return taskRepository.findByUser(email);
	}
	
	
	
}
