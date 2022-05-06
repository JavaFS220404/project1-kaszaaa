 package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.exceptions.UsernameNotUniqueException;
import com.revature.interFace.UserFunctions;
import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.exceptions.ExistException;
import com.revature.exceptions.NewUserHasNonZeroIdException;
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
 * {@code login} and {@code register} are the minimum methods required; however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Retrieve Currently Logged-in User</li>
 *     <li>Change Password</li>
 *     <li>Logout</li>
 * </ul>
 */
public class AuthService implements UserFunctions{

	
	List<User> users = new ArrayList<>();
	Scanner scan = new Scanner(System.in);
	UserDAO userdao = new UserDAO();
	
    /**
     * <ul>
     *     <li>Needs to check for existing users with username/email provided.</li>
     *     <li>Must throw exception if user does not exist.</li>
     *     <li>Must compare password provided and stored password for that user.</li>
     *     <li>Should throw exception if the passwords do not match.</li>
     *     <li>Must return user object if the user logs in successfully.</li>
     * </ul>
     */
	/** this method gets the input from user and checks if the username and password 
	 * are the same as in the database */
	
    public User login(String username, String password) {

    	User user = new User();
    	
    	System.out.println("Hello, please pass the username: ");
   		String userName = scan.nextLine();
   		System.out.println("Please pass the password: ");
    	String passWord = scan.nextLine();
    	
		try {
			for (User iteratedUser : users) {
				if (iteratedUser.getUsername().equals(userdao.getByUsername(username)) && iteratedUser.getPassword().equals(passWord)) {
					System.out.println("Access Granted! Welcome!");
					return user;
				} else if (iteratedUser.getUsername().equals(userName)) {
					throw new WrongPassword();
				} else {
					throw new ExistException();
				}
			}
		} catch (WrongPassword ex) {
			System.out.println("Wrong username or password!");
			return null;
		} catch (ExistException ex) {
			System.out.println("User does not exist!");
			return null;
		}
		return null;
	}

    /**
     * <ul>
     *     <li>Should ensure that the username/email provided is unique.</li>
     *     <li>Must throw exception if the username/email is not unique.</li>
     *     <li>Should persist the user object upon successful registration.</li>
     *     <li>Must throw exception if registration is unsuccessful.</li>
     *     <li>Must return user object if the user registers successfully.</li>
     *     <li>Must throw exception if provided user has a non-zero ID</li>
     * </ul>
     *
     * Note: userToBeRegistered will have an id=0, additional fields may be null.
     * After registration, the id will be a positive integer.
     */
    
    public User register(User userToBeRegistered) {
    	
    	System.out.println("Please enter your first name: ");
    	userToBeRegistered.setFirstname(scan.nextLine());
    	System.out.println("Please enter your last name: ");
    	userToBeRegistered.setLastname(scan.nextLine());
        System.out.println("Please set the Username ");
        userToBeRegistered.setUsername(scan.nextLine());
        System.out.println("Please set the Email ");
        userToBeRegistered.setEmail(scan.nextLine());
        
        try {
	        for (User iteratedUser : users) {
	    		if (iteratedUser.getUsername().equals(userToBeRegistered.getUsername())) {
	    			throw new UsernameNotUniqueException();
	    		}
	        }
        } catch (UsernameNotUniqueException ex) {
        	System.out.println("The user already exist!");
        	return null;
        } catch (RegistrationUnsuccessfulException ex) {
    		System.out.println("Registration unsuccessful!");
    		return null;
    	}
       
        System.out.println("Please set the Password");
        userToBeRegistered.setPassword(scan.nextLine());
        System.out.println("Please choose role: \n"
        		+"1. Employee \n"
        		+"2. Finance manager \n");
        int userRole = Integer.valueOf(scan.nextLine());
       
        if (userRole == 1) {
        	userToBeRegistered.setRole(Role.EMPLOYEE);
        	userToBeRegistered.setRoleId(1);
        }else if (userRole == 2) {
        	userToBeRegistered.setRole(Role.FINANCE_MANAGER);
        	userToBeRegistered.setRoleId(2);
        }else {
        	userToBeRegistered.setRole(null);
        	System.out.println("You pass wrong role");
        }
       
        users.add(userToBeRegistered);
        System.out.println(users);
        UserService.create(userToBeRegistered); 
      return userToBeRegistered;}
    
    /**
     * This is an example method signature for retrieving the currently logged-in user.
     * It leverages the Optional type which is a useful interface to handle the
     * possibility of a user being unavailable.
     */
    public Optional<User> exampleRetrieveCurrentUser(String username) {
    	
        return Optional.empty();
    }

    public Optional<User> getByUsername(String username){
    	System.out.println("Please pass the username: ");
    	UserDAO userdao = new UserDAO();
    	Optional<User> user = userdao.getByUsername(scan.nextLine());
		if (user != null) {
			System.out.println(user);
			return user;
		}else {
			System.out.println("that user does not exist in the database.");
			return null;
		}
//		User usermodel = new User();
//		//usermodel.getUsername();
//		user.getByUsername(username);
//		//System.out.println(getByUsername(username));
		//return user.getByUsername(scan.nextLine());
    	
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
    
    public User getByUserId(int id){
    	System.out.println("Please pass the user id: ");
    	Integer userid = Integer.valueOf(scan.nextLine());
    	UserDAO userdao = new UserDAO();
    	User user = userdao.getByUserId(userid);
		if (user != null) {
			System.out.println(user);
			return user;
		}else {
			System.out.println("that user does not exist in the database.");
			return null;
		}}
    
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findByUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User create(User userToBeRegistered) {
		// TODO Auto-generated method stub
		return null;
	}

}
	
	
