package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.ExistException;
import com.revature.exceptions.WrongPassword;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;
import com.revature.services.UserService;
import com.revature.servlets.controllers.ReimbursementController;
import com.revature.servlets.controllers.UserController;

public class ServletLogin extends HttpServlet {
	
	private ObjectMapper mapper = new ObjectMapper();

	
	private UserController userController = new UserController();
	private ReimbursementController reimbursementController = new ReimbursementController(); //nie wiem czy to jest potrzebne i czy ok
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setStatus(401);

		final String URL = req.getRequestURI().replace("/check/core/", "");
		System.out.println(URL);
		String[] UrlSections = URL.split("/");
		resp.setStatus(404);
		
		switch (UrlSections[0]) {
		case "login":
			if (req.getMethod().equals("POST")) {
				try {
					userController.login(req, resp);
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (WrongPassword e) {
					
					e.printStackTrace();
				} catch (ExistException e) {
					
					e.printStackTrace();
				}
				break;}
		case "register":
			System.out.println("ds");
				if (req.getMethod().equals("POST")) {
					userController.createUser(req, resp);
			break;}	
		case "reimreimbursementfunctions":
				HttpSession session = req.getSession(false);
				System.out.println(req.getParameter("sessionId"));
				
				if (session != null) {
					if (req.getMethod().equals("GET")) {	
						System.out.println(req.getAttribute("searchforstatus"));
						System.out.println(req.getParameter("searchforstatus"));
						reimbursementController.getReimbursementByStatus(session, resp);
						break;
					}else if(req.getMethod().equals("POST")) {
						
						BufferedReader reader = req.getReader();
						
						StringBuilder stBuilder = new StringBuilder();
						
						String line = reader.readLine();
						while(line!=null) {
							stBuilder.append(line);
							line = reader.readLine();
						}

						String body = new String(stBuilder);
						System.out.println(body);
						
						Reimbursement reimb = mapper.readValue(body.toString(), Reimbursement.class);
						reimb.setAuthor((User)session.getAttribute("user"));
						reimbursementController.createReimbursement(reimb, resp);
						System.out.println();
					}else if(req.getMethod().equals("PUT")) {
						BufferedReader reader = req.getReader();
						
						StringBuilder stBuilder = new StringBuilder();
						
						String line = reader.readLine();
						
						while(line!=null) {
							stBuilder.append(line);
							line = reader.readLine();
						}
						
						String body = new String(stBuilder);
						System.out.println(body);
						
						Reimbursement reimb = mapper.readValue(body, Reimbursement.class);
						reimb.setAuthor((User)session.getAttribute("user"));
						reimbursementController.pocess(reimb, resp);
					}
				 else {
					resp.setStatus(401);
				}
				break;
		}}}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}

