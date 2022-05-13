package com.revature.servlets.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.UnsuccessfulCreationofReimbursement;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.services.ReimbursementService;

public class ReimbursementController{

	private ReimbursementService reimbServ = new ReimbursementService();
	private ObjectMapper objectMapper = new ObjectMapper();
//	private Reimbursement reimb = new Reimbursement();
	
	public void getReimbursementByStatus(HttpSession session, HttpServletResponse resp) throws IOException {
		
		Status status = Status.DENIED;
		
	
		List<Reimbursement> reimbList = reimbServ.getReimbursementsByStatus(status);
		
		if(reimbList.size()==0) {
			resp.setStatus(204);
		}else {
			resp.setStatus(200);
			String json = objectMapper.writeValueAsString(reimbList);
			PrintWriter print = resp.getWriter();
			print.print(json);
		}
	}
		

	public void createReimbursement(Reimbursement reimb, HttpServletResponse resp) throws UnsuccessfulCreationofReimbursement {
			try {
				if(reimbServ.createReimbursement(reimb)) {
					resp.setStatus(201);
				}else {
					resp.setStatus(400);
				}
			} catch (UnsuccessfulCreationofReimbursement e) {
				e.printStackTrace();
			}
		
		
	}


	public  void pocess(Reimbursement reimb, HttpServletResponse resp) {
//		if(reimbServ.process(reimb, status, ) {
//			resp.setStatus(200);
//		}else {
//			resp.setStatus(400);
		}
	
		
	}


