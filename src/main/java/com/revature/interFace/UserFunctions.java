package com.revature.interFace;

import java.util.List;

import com.revature.models.User;

public interface UserFunctions {
	
	public List<User> findAll();
	public User findById(Integer id);
	public boolean findByUsername(String username);
	public User create(User userToBeRegistered);
	

}
