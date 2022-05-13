package com.revature.servlets.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.ExistException;
import com.revature.exceptions.WrongPassword;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;


public class UserController {
	private AuthService authService = new AuthService();
	private ObjectMapper mapper = new ObjectMapper();
	private UserService userService = new UserService();

	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, WrongPassword, ExistException{
		BufferedReader reader = req.getReader();
		StringBuilder stBuilder = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line!=null) {
			stBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stBuilder);
		
		User user = mapper.readValue(body, User.class);
		Optional<User> authUser = Optional.of(authService.login(user.getUsername(), user.getPassword()));
		
		if(authUser.isPresent()) {
			HttpSession session = req.getSession();
			session.setAttribute("user", authUser.get());
			resp.setStatus(200);
		}else {
			resp.setStatus(401);
		}
		
	}

public void createUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		BufferedReader reader = req.getReader();
		StringBuilder stBuilder = new StringBuilder();
		String line = reader.readLine();

		while (line != null) {
			stBuilder.append(line);
			line = reader.readLine();
		}
		String body = new String(stBuilder);
		System.out.println(body);

		User newuser = mapper.readValue(body, User.class);
		
		
			User regUser = authService.register(newuser);
			HttpSession session = req.getSession();
			session.setAttribute("newuser", regUser);
			resp.setStatus(201);
			
			String json = mapper.writeValueAsString(regUser);
			PrintWriter print = resp.getWriter();
			print.print(json);
	
		
			resp.setStatus(400);	
		
		
	}}




