package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.exceptions.ExistException;
import com.revature.exceptions.WrongPassword;
import com.revature.util.ConnectionFactory;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {
	List<User> users = new ArrayList<>();
	
	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */
	public Optional<UserDAO> getByUsername(String username) {
		UserDAO userdao = new UserDAO();
		userdao.getByUsername(username);
		return Optional.ofNullable(userdao);
	//return Optional.empty();
			}
			
//	public Optional<UserDAO> getByUserId(int id) {
//		UserDAO userdao = new UserDAO();
//		userdao.getByUserId(id);
//		return Optional.ofNullable(userdao);
//	}
	
	public UserDAO getByUserId(int id) {
		UserDAO userdao = new UserDAO();
		userdao.getByUserId(id);
		return userdao;
	}
    	
	
	public static User create(User userToBeRegistered) {
		UserDAO userdao = new UserDAO();
		userdao.create(userToBeRegistered);
		return userToBeRegistered;
		

	}}
	
	

