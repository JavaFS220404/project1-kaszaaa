package com.revature;

//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
import java.util.Scanner;

import com.revature.models.AbstractReimbursement;
import com.revature.models.AbstractUser;
import com.revature.models.Reimbursement;
//import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;

public class Driver {

    public static void main(String[] args) {
//    	AuthService app = new AuthService();
//    	List<User> users = new ArrayList<>();
//    	User user = new User(1, "test", "test", Role.FINANCE_MANAGER);
//    	
//    	User user2 = new User(2, "test2", "test2", Role.FINANCE_MANAGER);
//    	users.add(user);
//    	users.add(user2);
//    	
    	
    	
//    	System.out.println(user.getUsername());
    	
//    	app.login("test","test");
//    	
//    }
//}
		AuthService authserv = new AuthService();
		ReimbursementService reimbserv = new ReimbursementService();
		UserDAO userdao = new UserDAO();
		String username = new String();
		String password = new String();

    	boolean exit = false;
		while(!exit) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Hello, what would you like to do? \n"
					+"1. Log in \n"
					+"2. Create new account \n"
					+"3. Print informations about user \n"
//					+"4. See all accounts \n"
					+"0. Exit");
			String choice = scan.nextLine();
			AbstractUser user = new AbstractUser();
			
			
			choiceSwitch:
				
				switch(choice) {
				
					case "0":
						exit = true;
						System.out.println("Goodbye! Thanks for using the application!");
						break; 
					case "1":
						authserv.login(username, password);
						break choiceSwitch;
					case "2":
						User newuser = new User();
						authserv.register(newuser);
//						System.out.println(newuser);
						break choiceSwitch;
					case "3":
						authserv.getByUsername(username);
					case "4":
						Integer userid = Integer.valueOf(scan.nextLine());
						authserv.getByUserId(userid);
					case "5":
						ReimbursementDAO reimbdao = new ReimbursementDAO();
						Reimbursement reimb = new Reimbursement();
						//AbstractReimbursement reimbab = new AbstractReimbursement();
						System.out.println("Please enter your first name: ");
				    	reimb.reimbdao.update(setAmount(Double.valueOf(scan.nextLine())));
				    	System.out.println("Please enter your last name: ");
				    	userToBeRegistered.setLastname(scan.nextLine());
				        System.out.println("Please set the Username ");
				        System.out.println("Please set the Email ");
				        userToBeRegistered.setEmail(scan.nextLine())
						System.out.println("Please pass the id: ");
						reimbserv.getById(Integer.valueOf(scan.nextLine()));
						
						
    	}
    }
 } 	}