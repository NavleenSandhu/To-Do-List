package ca.sheridancollege.ca.sandnavl.beans;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {
	private long taskId;
	private String taskDescription;
	private LocalDate dueDate;
	private int userId;
}
