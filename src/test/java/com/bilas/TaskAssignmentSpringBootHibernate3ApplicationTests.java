package com.bilas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bilas.entities.Task;
import com.bilas.entities.User;
import com.bilas.services.TaskService;
import com.bilas.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskAssignmentSpringBootHibernate3ApplicationTests {

	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;

	@Before
	public void initDb() {
		{
			// insert some test User in the Database
			User newUser = new User("testUser@mail.com", "testUser", "123456");
			userService.user_creation(newUser);
		}

		{
			User newUser = new User("admin@gmail.com", "admin", "123456");
			userService.admin_creation(newUser);
		}

		Task userTask = new Task("Trial Title", "20/08/2018", "25/09/2018", "you need to work today");
		User user = userService.getUserByEmail("testUser@mail.com");
		taskService.assignTask(userTask, user);
	}

	@Test
	public void testUser() {
		User user = userService.getUserByEmail("testUser@mail.com");

		assertNotNull(user);

		// User admin = userService.findOne("testAdmin@mail.com");
		User admin = userService.getUserByEmail("testAdmin@mail.com");
		assertEquals(admin.getEmail(), "testAdmin@mail.com");
	}

}
