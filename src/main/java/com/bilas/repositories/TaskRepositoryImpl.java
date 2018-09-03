package com.bilas.repositories;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bilas.entities.Task;
import com.bilas.entities.User;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public List<Task> findByUser(String email) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction transaction = session.beginTransaction();

		Query theQuery = null;
		theQuery = session.createQuery("from Task where lower(user_email) like :email");
		theQuery.setParameter("email", "%" + email.toLowerCase() + "%");
		List<Task> tasks = theQuery.list();
		transaction.commit();

		return tasks;

	}

	@Override
	public void assignTask(Task task, User user) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction transaction = session.beginTransaction();

		task.setUser(user);
		session.saveOrUpdate(task);

		transaction.commit();
	}

}
