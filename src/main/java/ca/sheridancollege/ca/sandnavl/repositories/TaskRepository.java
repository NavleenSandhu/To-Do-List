package ca.sheridancollege.ca.sandnavl.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.ca.sandnavl.beans.Task;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class TaskRepository {
	private NamedParameterJdbcTemplate jdbc;
	
	public List<Task> getTasksByUserName(String userName) {
		MapSqlParameterSource parameters= new MapSqlParameterSource();
		String query= "SELECT * FROM TASKS WHERE USER_ID=(SELECT USER_ID FROM USERS WHERE USER_NAME=:userName) ORDER BY DUE_DATE";
		parameters.addValue("userName", userName);
		List<Task> tasks= jdbc.query(query, parameters, new BeanPropertyRowMapper<Task>(Task.class));
		return tasks;
	}
	
	public List<Long> getTaskIdsByUserName(String userName) {
		List<Long> taskIds= new ArrayList<Long>();
		for(Task task: getTasksByUserName(userName)) {
			taskIds.add(task.getTaskId());
		}
		return taskIds;
	}

	public void addTask(Task task) {
		MapSqlParameterSource parameters= new MapSqlParameterSource();
		String query="INSERT INTO Tasks (TASK_DESCRIPTION, DUE_DATE, USER_ID) VALUES (:taskDescription, :dueDate, :userId)";
		parameters.addValue("taskDescription", task.getTaskDescription());
		parameters.addValue("dueDate", task.getDueDate());
		parameters.addValue("userId", task.getUserId());
		jdbc.update(query, parameters);
	}

	public Task getTaskByTaskId(long taskId) {
		MapSqlParameterSource parameters= new MapSqlParameterSource();
		String query= "SELECT * FROM TASKS WHERE TASK_ID=:taskId";
		parameters.addValue("taskId", taskId);
		return jdbc.query(query, parameters, new BeanPropertyRowMapper<Task>(Task.class)).get(0);
	}

	public void editTask(Task task) {
		MapSqlParameterSource parameters= new MapSqlParameterSource();
		String query="UPDATE Tasks SET TASK_DESCRIPTION=:taskDescription, DUE_DATE=:dueDate WHERE TASK_ID=:taskId";
		parameters.addValue("taskDescription", task.getTaskDescription());
		parameters.addValue("dueDate", task.getDueDate());
		parameters.addValue("taskId", task.getTaskId());
		jdbc.update(query, parameters);
	}

	public void deleteTaskByTaskId(long taskId) {
		MapSqlParameterSource parameters= new MapSqlParameterSource();
		String query="DELETE FROM TASKS WHERE TASK_ID=:taskId";
		parameters.addValue("taskId", taskId);
		jdbc.update(query, parameters);
	}

}
