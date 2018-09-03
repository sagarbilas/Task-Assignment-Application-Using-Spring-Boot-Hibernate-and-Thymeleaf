package com.bilas.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bilas.entities.Task;
import com.bilas.entities.User;
import com.bilas.services.TaskService;
import com.bilas.services.UserService;

@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;

	@GetMapping("/addTask")
	public String showTaskForm(String email, Model model, HttpSession session) {
		session.setAttribute("email", email);
		Task theTask = new Task();
		model.addAttribute("task", theTask);

		return "views/addNewTask";
	}

	@PostMapping("/addTask")
	public String assignNewTask(@Valid Task task, BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "views/addNewTask";
		}

		String email = (String) session.getAttribute("email");
		taskService.assignTask(task, userService.getUserByEmail(email));

		return "redirect:/users";
	}

	/* an admin can view the tasks of a specific user */
	@GetMapping("/assignedTask")
	public String displayAssignedTask(Model model, String email) {
		User user = userService.getUserByEmail(email);
		model.addAttribute("viewTasks", taskService.findUserTask(email));

		return "views/alreadyAssignedTasks";
	}

}
