package com.revature.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserDAO;

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
	private UserDAO userdao = new UserDAO();
	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */
	
		
	
	public Optional<UserDAO> getByUsername(String username) {
		UserDAO userdao = new UserDAO();
		userdao.getByUsername(username);
		return Optional.ofNullable(userdao);
		}
	
	public Optional<UserDAO> getByPassword(String password) {
		UserDAO userdao = new UserDAO();
		userdao.getByUsername(password);
		return Optional.ofNullable(userdao);
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
		

	
	
	}

	
	}
	
	

