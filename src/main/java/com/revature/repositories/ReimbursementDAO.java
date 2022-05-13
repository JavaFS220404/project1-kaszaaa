package com.revature.repositories;

import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ReimbursementDAO {
	
	User user = new User();
	UserDAO userDAO = new UserDAO();

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
    public Optional<Reimbursement> getById(int id) {
    	try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
		
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setId(result.getInt("reimb_id"));
				reimbursement.setAmount(result.getDouble("reimb_amount"));
				reimbursement.setSubmitted(result.getTimestamp("reimb_submitted"));
				reimbursement.setResolved(result.getTimestamp("reimb_resolved"));
				reimbursement.setDescription(result.getString("reimb_description"));
				
				reimbursement.setStatusId(result.getInt("reimb_status_id"));
				reimbursement.setTypeId(result.getInt("reimb_type_id"));
//				User user = new User();
//        		UserDAO userDAO = new UserDAO();
        		int authId = result.getInt("reimb_author");
        		user = userDAO.getByUserId(authId);
        		reimbursement.setAuthor(user);
        		int resId = result.getInt("reimb_resolver");
        		user = userDAO.getByUserId(resId);
        		reimbursement.setResolver(user);
//				reimbursement.setAuthor(result.getInt("reimb_author"));
//				reimbursement.setResolver(result.getInt("reimb_resolver"));
				
				
				return Optional.of(reimbursement);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return Optional.empty();
	}
       
    

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public static List<Reimbursement> getByStatus(Status status) {
    	int tempValue = 1;
    		if(status == Status.PENDING) {tempValue = 1;}
    		else if(status == Status.DENIED) {tempValue = 2;}
    		else {tempValue = 3;}
    		System.out.println(tempValue);
    	
    	List<Reimbursement> list = new ArrayList<>();
    	try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT reimb_id, reimb_amount, reimb_description, reimb_status_id, reimb_type_id, reimb_submitted, reimb_status_id , reimb_resolved, reimb_author, reimb_resolver FROM ers_reimbursement WHERE reimb_status_id = "+tempValue+";";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				User user = new User();
        		UserDAO userDAO = new UserDAO();
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setId(result.getInt("reimb_id"));
				reimbursement.setAmount(result.getDouble("reimb_amount"));
				reimbursement.setDescription(result.getString("reimb_description"));
				reimbursement.setStatusId(result.getInt("reimb_status_id"));
				reimbursement.setTypeId(result.getInt("reimb_type_id"));
				reimbursement.setSubmitted(result.getTimestamp("reimb_submitted"));
				reimbursement.setStatusId(result.getInt("reimb_status_id"));
        		int authId = result.getInt("reimb_author");
        		user = userDAO.getByUserId(authId);
        		reimbursement.setAuthor(user);
        		int resId = result.getInt("reimb_resolver");
				reimbursement.setResolved(result.getTimestamp("reimb_resolved"));
        		user = userDAO.getByUserId(resId);
        		reimbursement.setResolver(user);
        		int reimbStatId = result.getInt("reimb_status_id");
	        		if (reimbStatId == 1) {
						reimbursement.setStatus(Status.PENDING);
	        		}else if(reimbStatId == 2) {
	        			reimbursement.setStatus(Status.DENIED);
	        		}else {
	        			reimbursement.setStatus(Status.APPROVED);
	        		}
        		int reimbTypeId = result.getInt("reimb_type_id");
	        		if (reimbTypeId == 1) {
	        			reimbursement.setType("TRAVEL");
	        		}else if(reimbTypeId == 2) {
	        			reimbursement.setType("FOOD");
	        		}else if(reimbTypeId == 3) {
	        			reimbursement.setType("LODGING");
	        		}else {
	        			reimbursement.setType("OTHER");
	        		}

				list.add(reimbursement);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
    	
    	return list;
    }
    public List<Reimbursement> getAllReimb(){
	    try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM reimbursement ORDER BY reim_id;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			List<Reimbursement> reimblist = new ArrayList<>();
			
			while(result.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setId(result.getInt("reimb_id"));
				reimbursement.setAmount(result.getDouble("reimb_amount"));
				reimbursement.setDescription(result.getString("reimb_description"));

				reimbursement.setSubmitted(result.getTimestamp("reimb_submitted"));
				reimbursement.setResolved(result.getTimestamp("reimb_resolved"));
				
				reimbursement.setStatusId(result.getInt("reimb_status_id"));
				reimbursement.setTypeId(result.getInt("reimb_type_id"));
				User user = new User();
        		UserDAO userDAO = new UserDAO();
        		int authId = result.getInt("reimb_author");
        		user = userDAO.getByUserId(authId);
        		reimbursement.setAuthor(user);
        		int resId = result.getInt("reimb_resolver");
        		user = userDAO.getByUserId(resId);
        		reimbursement.setResolver(user);
        		int reimbStatId = result.getInt("reimb_status_id");
	        		if (reimbStatId == 1) {
						reimbursement.setStatus(Status.PENDING);
	        		}else if(reimbStatId == 2) {
	        			reimbursement.setStatus(Status.DENIED);
	        		}else {
	        			reimbursement.setStatus(Status.APPROVED);
	        		}
        		int reimbTypeId = result.getInt("reimb_type_id");
	        		if (reimbTypeId == 1) {
	        			reimbursement.setType("TRAVEL");
	        		}else if(reimbTypeId == 2) {
	        			reimbursement.setType("FOOD");
	        		}else if(reimbTypeId == 3) {
	        			reimbursement.setType("LODGING");
	        		}else {
	        			reimbursement.setType("OTHER");
	        		}

				reimblist.add(reimbursement);
			}return reimblist;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) {
    	try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "UPDATE ers_reimbursement SET reimb_amount=?, reimb_submitted=?, "
					+ "reimb_resolved=?, reimb_description=?, reimb_receipt=?, reimb_status_id=?, reimb_type_id=?, reimb_author=?, reimb_resolver=? "
					+ "WHERE reimb_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			//Statement statement1 = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			int count = 0;
			
			statement.setInt(++count, unprocessedReimbursement.getId());
			statement.setDouble(++count, unprocessedReimbursement.getAmount());
			statement.setTimestamp(++count, unprocessedReimbursement.getSubmitted());
			statement.setTimestamp(++count, unprocessedReimbursement.getResolved());
			statement.setString(++count, unprocessedReimbursement.getDescription());
			
			
//			User user = new User();
//    		UserDAO userDAO = new UserDAO();
    		int authId = result.getInt("reimb_author");
    		user = userDAO.getByUserId(authId);
    		unprocessedReimbursement.setAuthor(user);
    		int resId = result.getInt("reimb_resolver");
    		user = userDAO.getByUserId(resId);
    		unprocessedReimbursement.setResolver(user);
			
//			statement.setInt(++count, unprocessedReimbursement.getAuthorId());
//			statement.setInt(++count, unprocessedReimbursement.getRelolverId());
			statement.setInt(++count, unprocessedReimbursement.getStatusId());
			statement.setInt(++count, unprocessedReimbursement.getTypeId());
			
			statement.execute();
			
			return unprocessedReimbursement;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
    	return null;
    }



	public boolean createReimbursement(Reimbursement reimbursement) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "INSERT INTO ers_reimbursement(reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_status_id, reimb_type_id, reimb_author, reimb_resolver) VALUES (?,?,?,?,?,?,?,?,?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery(sql);
			
			int count = 0;
			statement.setInt(++count, reimbursement.getId());
			statement.setDouble(++count, reimbursement.getAmount());
			statement.setTimestamp(++count, reimbursement.getSubmitted());
			statement.setTimestamp(++count, reimbursement.getResolved());
			statement.setString(++count, reimbursement.getDescription());
			
			int authId = result.getInt("reimb_author");
    		user = userDAO.getByUserId(authId);
    		reimbursement.setAuthor(user);
    		int resId = result.getInt("reimb_resolver");
    		user = userDAO.getByUserId(resId);
    		reimbursement.setResolver(user);
    		int reimbStatId = result.getInt("reimb_status_id");
    		if (reimbStatId == 1) {
				reimbursement.setStatus(Status.PENDING);
    		}else if(reimbStatId == 2) {
    			reimbursement.setStatus(Status.DENIED);
    		}else {
    			reimbursement.setStatus(Status.APPROVED);
    		}
    		int reimbTypeId = result.getInt("reimb_type_id");
    		if (reimbTypeId == 1) {
    			reimbursement.setType("TRAVEL");
    		}else if(reimbTypeId == 2) {
    			reimbursement.setType("FOOD");
    		}else if(reimbTypeId == 3) {
    			reimbursement.setType("LODGING");
    		}else {
    			reimbursement.setType("OTHER");
    		}

			
			statement.execute();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	
	}



	public List<Reimbursement> getAllReimb(int id) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT ers_reimbursement.*, ers_users.ers_username FROM ers_reimbursement JOIN ers_users ON ers_reimbursement.reimb_author =ers_users.ers_users_id;";
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}}



	
