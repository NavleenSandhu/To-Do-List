package ca.sheridancollege.ca.sandnavl.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.ca.sandnavl.beans.User;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Repository
public class UserRepository {
	private NamedParameterJdbcTemplate jdbc;

	public int getUserIdByUserName(String userName) {
		MapSqlParameterSource parameters= new MapSqlParameterSource();
		String query= "SELECT USER_ID FROM USERS WHERE USER_NAME=:userName";
		parameters.addValue("userName", userName);
		return (int)jdbc.queryForList(query, parameters).get(0).get("USER_ID");
	}
	public User findUserByUserName(String userName) {
		MapSqlParameterSource parameters= new MapSqlParameterSource();
		String query= "SELECT * FROM USERS WHERE USER_NAME=:un";
		parameters.addValue("un", userName);
		ArrayList<User> users=(ArrayList<User>) jdbc.query(query, parameters, new BeanPropertyRowMapper<User>(User.class));
		return users.get(0);
	
	}
	public ArrayList<String> getRolesById(int id) {
		ArrayList<String> roles= new ArrayList<String>();
		MapSqlParameterSource parameters= new MapSqlParameterSource();
		String query="SELECT USER_ROLE.USER_ID, SEC_ROLE.ROLE_NAME "
				+ "FROM SEC_ROLE, USER_ROLE "
				+ "WHERE USER_ROLE.ROLE_ID = SEC_ROLE.ROLE_ID "
				+ "AND USER_ID=:id;";
		parameters.addValue("id", id);
		List<Map<String,Object>> rows= jdbc.queryForList(query, parameters);
		for(Map row:rows) {
			roles.add((String)row.get("role_Name"));
		}
		return roles;
	}
	public void addUser(String username,String firstName, String lastName, String password) {
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		MapSqlParameterSource parameters= new MapSqlParameterSource();
		String query= "insert into USERS (user_Name,FIRST_NAME,LAST_NAME,Password) "
				+ "values (:username,:fName,:lName, :password);";
		parameters.addValue("username", username);
		parameters.addValue("fName", firstName);
		parameters.addValue("lName", lastName);
		parameters.addValue("password", encoder.encode(password));
		jdbc.update(query, parameters);
	}
	
	public void addRoles(int userId,int roleId) {
		MapSqlParameterSource parameters= new MapSqlParameterSource();
		String query= "insert into USER_ROLE (user_id, role_id) "
				+ "values (:uid, :rid);";
		parameters.addValue("uid", userId);
		parameters.addValue("rid", roleId);
		jdbc.update(query, parameters);
	}
}
