package ca.sheridancollege.ca.sandnavl.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	private int userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
}
