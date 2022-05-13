package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.exceptions.UsernameNotUniqueException;
import com.revature.interFace.UserFunctions;
import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.exceptions.ExistException;
import com.revature.exceptions.NewUserHasNonZeroIdException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import com.revature.models.Role;
import com.revature.models.AbstractUser;
import com.revature.services.UserService;
import com.revature.exceptions.WrongPassword;
import com.revature.exceptions.UserNamePosessionException;

/**
 * The AuthService should handle login and registration for the ERS application.
 *
 * {@code login} and {@code register} are the minimum methods required; however,
 * additional methods can be added.
 *
 * Examples:
 * <ul>
 * <li>Retrieve Currently Logged-in User</li>
 * <li>Change Password</li>
 * <li>Logout</li>
 * </ul>
 */
public class AuthService {

	List<User> users = new ArrayList<>();
	Scanner scan = new Scanner(System.in);
	UserDAO userdao = new UserDAO();

	/**
	 * <ul>
	 * <li>Needs to check for existing users with username/email provided.</li>
	 * <li>Must throw exception if user does not exist.</li>
	 * <li>Must compare password provided and stored password for that user.</li>
	 * <li>Should throw exception if the passwords do not match.</li>
	 * <li>Must return user object if the user logs in successfully.</li>
	 * </ul>
	 */
	/**
	 * this method gets the input from user and checks if the username and password
	 * are the same as in the database
	 * @throws WrongPassword 
	 * @throws ExistException 
	 *
	 */

	public User login(String username, String password) throws WrongPassword, ExistException{
		System.out.println("1");
		UserDAO userdao = new UserDAO();
		User user = new User();
		Optional<User> optionalUser = userdao.getByUsername(username);
		System.out.println(optionalUser);
		// try {

		if (optionalUser.isPresent()) {
			if (optionalUser.get().getPassword().equals(password)) {
				return user;

			} else  
				throw new WrongPassword();
		}else
			throw new ExistException();
	}
	/**
	 * <ul>
	 * <li>Should ensure that the username/email provided is unique.</li>
	 * <li>Must throw exception if the username/email is not unique.</li>
	 * <li>Should persist the user object upon successful registration.</li>
	 * <li>Must throw exception if registration is unsuccessful.</li>
	 * <li>Must return user object if the user registers successfully.</li>
	 * <li>Must throw exception if provided user has a non-zero ID</li>
	 * </ul>
	 *
	 * Note: userToBeRegistered will have an id=0, additional fields may be null.
	 * After registration, the id will be a positive integer.
	 */

	public User register(User userToBeRegistered) throws UsernameNotUniqueException, RegistrationUnsuccessfulException, NewUserHasNonZeroIdException {
		User user = new User();
		System.out.println("cos");
		if (userToBeRegistered.getId() == 0) {
			Optional<User> regUser = userdao.getByUsername(userToBeRegistered.getUsername());
			if(regUser.isPresent()) {
				throw new UsernameNotUniqueException();
			}else {
				user = userdao.create(userToBeRegistered);
				if (user==null) {
					throw new RegistrationUnsuccessfulException();
				}else {
					return userToBeRegistered;}
			}}throw new NewUserHasNonZeroIdException();
			
	}

	/**
	 * This is an example method signature for retrieving the currently logged-in
	 * user. It leverages the Optional type which is a useful interface to handle
	 * the possibility of a user being unavailable.
	 */
	public Optional<User> exampleRetrieveCurrentUser(String username) {

		return Optional.empty();
	}

	public Optional<User> getByUsername(String username) {
		System.out.println("Please pass the username: ");
		UserDAO userdao = new UserDAO();
		Optional<User> user = userdao.getByUsername(username);
		if (user != null) {
			System.out.println(user);
			return user;
		} else {
			System.out.println("that user does not exist in the database.");
			return null;
		}
//		User usermodel = new User();
//		//usermodel.getUsername();
//		user.getByUsername(username);
//		//System.out.println(getByUsername(username));
		// return user.getByUsername(scan.nextLine());

	}

//    public Optional<User> getByUserId(int id){
//    	System.out.println("Please pass the user id: ");
//    	Integer userid = Integer.valueOf(scan.nextLine());
//    	UserDAO userdao = new UserDAO();
//    	Optional<User> user = userdao.getByUserId(userid);
//		if (user != null) {
//			System.out.println(user);
//			return user;
//		}else {
//			System.out.println("that user does not exist in the database.");
//			return null;
//		}}

	public User getByUserId(int id) {
		System.out.println("Please pass the user id: ");
		//Integer userid = Integer.valueOf(id);
		UserDAO userdao = new UserDAO();
		User user = userdao.getByUserId(id);
		if (user != null) {
			System.out.println(user);
			return user;
		} else {
			System.out.println("that user does not exist in the database.");
			return null;
		}
	}
	
	}


