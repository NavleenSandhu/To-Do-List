package ca.sheridancollege.ca.sandnavl.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.ca.sandnavl.beans.Task;
import ca.sheridancollege.ca.sandnavl.repositories.TaskRepository;
import ca.sheridancollege.ca.sandnavl.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class TaskController {
	private TaskRepository taskRepo;
	private UserRepository userRepo;
	
	@GetMapping("/{userName}")
	public String home(@PathVariable String userName, Model model) {
		model.addAttribute("userName", userName);		
		model.addAttribute("tasks",taskRepo.getTasksByUserName(userName));
		try {
		model.addAttribute("firstName",userRepo.findUserByUserName(userName).getFirstName());
		}catch(Exception e) {
			model.addAttribute("firstName","");
		}
		for(Long id: taskRepo.getTaskIdsByUserName(userName)) { //setting flags for each taskid
			model.addAttribute("editFlagFor"+id,false);
		}
		return "home.html";
	}
	
	@GetMapping("/{userName}/addTask")
	public String addTaskPage(@PathVariable String userName, Model model) {
		model.addAttribute("task",new Task());
		model.addAttribute("todaysDate",LocalDate.now());
		model.addAttribute("userId", userRepo.getUserIdByUserName(userName));
		return "addTask.html";
	}
	
	@PostMapping("/{userName}/addTask")
	public String addTask(@PathVariable String userName, @ModelAttribute Task task) {
		taskRepo.addTask(task);
		return "redirect:/"+userName;
	}
	
	@GetMapping("/{userName}/edit/{taskId}")
	public String editTaskPage(@PathVariable String userName, @PathVariable long taskId, Model model) {
		model.addAttribute("userName", userName);		
		model.addAttribute("tasks",taskRepo.getTasksByUserName(userName));
		model.addAttribute("firstName",userRepo.findUserByUserName(userName).getFirstName());
		model.addAttribute("taskToEdit",taskRepo.getTaskByTaskId(taskId));
		model.addAttribute("todaysDate",LocalDate.now());
		for(Long id: taskRepo.getTaskIdsByUserName(userName)) {  //setting flags for each taskid
			if(id==taskId) {
				model.addAttribute("editFlagFor"+id,true);
			}else {
				model.addAttribute("editFlagFor"+id,false);
			}
		}
		return "home.html";
	}
	
	@PostMapping("/{userName}/edit")
	public String editTask(@ModelAttribute Task task,@PathVariable String userName) {
		taskRepo.editTask(task);
		return "redirect:/"+userName;
	}
	
	@PostMapping("/{userName}")
	public String filteredTasks(@PathVariable String userName, @RequestParam String filteredTasks, Model model) {
		model.addAttribute("lastCheckedButton",filteredTasks);
		model.addAttribute("userName", userName);		
		model.addAttribute("firstName",userRepo.findUserByUserName(userName).getFirstName());
		model.addAttribute("tasks",taskRepo.getTasksByUserName(userName));
		for(Long id: taskRepo.getTaskIdsByUserName(userName)) { //setting flags for each taskid
			model.addAttribute("editFlagFor"+id,false);
		}
		return "home.html";
	}
	@GetMapping("/{userName}/delete/{taskId}")
	public String deleteTask(@PathVariable String userName,@PathVariable long taskId) {
		taskRepo.deleteTaskByTaskId(taskId);
		return "redirect:/"+userName;
	}
}
