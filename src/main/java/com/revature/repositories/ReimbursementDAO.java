package com.revature.repositories;

import com.revature.models.Reimbursement;
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
				reimbursement.setReceipt(result.getByte("reimb_receipt"));
				reimbursement.setStatusId(result.getInt("reimb_status_id"));
				reimbursement.setTypeId(result.getInt("reimb_type_id"));
				User user = new User();
        		UserDAO userDAO = new UserDAO();
        		int authorId = result.getInt("reimb_author");
        		user = userDAO.getByUserId(authorId);
        		reimbursement.setAuthor(user);
        		int resolverId = result.getInt("reimb_resolver");
        		user = userDAO.getByUserId(resolverId);
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
    public List<Reimbursement> getByStatus(Status status) {
    	try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_status_id = ?;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Reimbursement> list = new ArrayList<>();
			
			while(result.next()) {
				Reimbursement reimbursement = new Reimbursement();
				reimbursement.setId(result.getInt("reimb_id"));
				reimbursement.setAmount(result.getDouble("reimb_amount"));
				reimbursement.setSubmitted(result.getTimestamp("reimb_submitted"));
				reimbursement.setResolved(result.getTimestamp("reimb_resolved"));
				reimbursement.setDescription(result.getString("reimb_description"));
				reimbursement.setReceipt(result.getByte("reimb_receipt"));
				reimbursement.setStatusId(result.getInt("reimb_status_id"));
				reimbursement.setTypeId(result.getInt("reimb_type_id"));
				User user = new User();
        		UserDAO userDAO = new UserDAO();
        		int authorId = result.getInt("reimb_author");
        		user = userDAO.getByUserId(authorId);
        		reimbursement.setAuthor(user);
        		int resolverId = result.getInt("reimb_resolver");
        		user = userDAO.getByUserId(resolverId);
        		reimbursement.setResolver(user);
//				reimbursement.setAuthor(result.getInt("reimb_author"));
//				reimbursement.setResolver(result.getInt("reimb_resolver"));
							
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		

	
    	
        return Collections.emptyList();
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
			
			int count = 0;
			
			statement.setInt(++count, unprocessedReimbursement.getId());
			statement.setDouble(++count, unprocessedReimbursement.getAmount());
			statement.setTimestamp(++count, unprocessedReimbursement.getSubmitted());
			statement.setTimestamp(++count, unprocessedReimbursement.getResolved());
			statement.setString(++count, unprocessedReimbursement.getDescription());
			statement.setByte(++count, unprocessedReimbursement.getReceipt());
			
//			User user = new User();
//    		UserDAO userDAO = new UserDAO();
//    		int authorId = unprocessedReimbursement.getInt("reimb_author");
//    		user = userDAO.getByUserId(authorId);
//    		reimbursement.setAuthor(user);
//    		int resolverId = result.getInt("reimb_resolver");
//    		user = userDAO.getByUserId(resolverId);
//    		reimbursement.setResolver(user);
			
			statement.setInt(++count, unprocessedReimbursement.getAuthorId());
			statement.setInt(++count, unprocessedReimbursement.getRelolverId());
			statement.setInt(++count, unprocessedReimbursement.getStatusId());
			statement.setInt(++count, unprocessedReimbursement.getTypeId());
			
			statement.execute();
			
			return unprocessedReimbursement;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
    	return null;
    }
}
