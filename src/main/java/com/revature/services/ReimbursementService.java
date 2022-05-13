package com.revature.services;

import com.revature.exceptions.ReimbursementExistanceException;
import com.revature.exceptions.UnsuccessfulCreationofReimbursement;
import com.revature.exceptions.WrongRoleException;
import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDAO;
import com.revature.repositories.UserDAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * The ReimbursementService should handle the submission, processing,
 * and retrieval of Reimbursements for the ERS application.
 *
 * {@code process} and {@code getReimbursementsByStatus} are the minimum methods required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create Reimbursement</li>
 *     <li>Update Reimbursement</li>
 *     <li>Get Reimbursements by ID</li>
 *     <li>Get Reimbursements by Author</li>
 *     <li>Get Reimbursements by Resolver</li>
 *     <li>Get All Reimbursements</li>
 * </ul>
 */
public class ReimbursementService {
	
	Scanner scan = new Scanner(System.in);
	AuthService authserv = new AuthService();
	private ReimbursementDAO reimbdao = new ReimbursementDAO();
	
	
	

    /**
     * <ul>
     *     <li>Should ensure that the user is logged in as a Finance Manager</li>
     *     <li>Must throw exception if user is not logged in as a Finance Manager</li>
     *     <li>Should ensure that the reimbursement request exists</li>
     *     <li>Must throw exception if the reimbursement request is not found</li>
     *     <li>Should persist the updated reimbursement status with resolver information</li>
     *     <li>Must throw exception if persistence is unsuccessful</li>
     * </ul>
     *
     * Note: unprocessedReimbursement will have a status of PENDING, a non-zero ID and amount, and a non-null Author.
     * The Resolver should be null. Additional fields may be null.
     * After processing, the reimbursement will have its status changed to either APPROVED or DENIED.
     */
    public Reimbursement process(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver) throws WrongRoleException{
    	
    	List<Reimbursement> list = reimbdao.getByStatus(finalStatus);
    		if (resolver.getRole().equals(Role.FINANCE_MANAGER)) {
    			
    			if (unprocessedReimbursement.getStatus().equals(Status.PENDING)) {
    				unprocessedReimbursement.setResolver(resolver);
    				unprocessedReimbursement.setStatus(finalStatus);
    				return unprocessedReimbursement;
    			}else {
    				throw new ReimbursementExistanceException();
    				
    		}
    		}else {
    			throw new WrongRoleException();
    			}}
			
    	
    /**
     * Should retrieve all reimbursements with the correct status.
     */
    public List<Reimbursement> getReimbursementsByStatus(Status status) {
    	
    	List<Reimbursement> reimbursement = reimbdao.getByStatus(status);
    	//Status statusid = Status.valueOf(scan.nextLine());
		if (status != null) {
			System.out.println(reimbursement);
			return reimbursement;
		}else {
			System.out.println("1 that reimbursement does not exist in the database.");
			return null;
		}
    }
    public Optional<ReimbursementDAO> getById(int id) {
		if (reimbdao.getById(id) != null) {
			System.out.println(reimbdao.getById(id));
			return Optional.ofNullable(reimbdao);
		}else {
			System.out.println("2 that reimbursement does not exist in the database.");
			return null;
		}}

    public List<Reimbursement> getAllReimb(){
    	return reimbdao.getAllReimb();
    }




    public boolean createReimbursement(Reimbursement reimbursement) throws UnsuccessfulCreationofReimbursement{
    	return reimbdao.createReimbursement(reimbursement);
//    }
//		final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		Reimbursement reimb = new Reimbursement(reimbursement.getAmount(), timestamp, reimbursement.getDescription(), reimbursement.getReimbType());
//		if (reimbdao.createReimbursement(reimb)) {
//			return true;
//		}throw new UnsuccessfulCreationofReimbursement();
//		
    }
	}
	
    	
    	


