package com.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.company.util.DBUtil;

public class UserDaoImp implements UserDao {

    @Override
    public boolean isValidUser(int voter_id, String password) {
        
    	String query = "SELECT * FROM voters WHERE voter_id = ? AND password = ?"; // SQL query to check if a user exists with the provided username and password
        
        try ( // Try-with-resources ensures automatic closing of resources
        	
        	// 1. Load Driver
            Connection connection = DBUtil.getConnection(); // Establish a database connection (using a utility method DBUtil.getConnection)
            PreparedStatement preparedStatement = connection.prepareStatement(query)) { // Prepare the SQL query to prevent SQL injection
        	System.out.println("preparedStatement before: " + preparedStatement);
        	
            // Set the values for the placeholder in the query
            preparedStatement.setLong(1, voter_id);
            preparedStatement.setString(2, password);
            System.out.println("preparedStatement after: " + preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery(); // Execute the query and store the results in a ResultSet
            System.out.println("resultSet: " + resultSet);
            
            boolean userExists = resultSet.next(); // resultSet.next() will be TRUE if a matching record is found, otherwise false
            System.out.println("result of resultSet.next(): " + userExists);
            System.out.println("Query executed successfully!");            
            return userExists;
                    
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return FALSE if any error occurs during database operations
        }
    }

	@Override
	public boolean addUser(User user) {
		String query = "INSERT INTO voters (voter_id, name, password, DOB) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        	preparedStatement.setInt(1, user.getVoterID()); // Use setInt for integer field
        	preparedStatement.setString(2, user.getName()); // Use setString for name
        	preparedStatement.setString(3, user.getPassword()); // Use setString for password
        	preparedStatement.setDate(4, java.sql.Date.valueOf(user.getDob())); // Use setDate for DOB (convert LocalDate to java.sql.Date)

            int rowsAffected = preparedStatement.executeUpdate();

            System.out.println("User added");   
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	@Override
	public boolean isVoterIdUnique(int voter_id) {
	    String query = "SELECT * FROM voters WHERE voter_id = ?";
	    try (Connection connection = DBUtil.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setInt(1, voter_id); // Set the voter ID in the query
	        ResultSet resultSet = preparedStatement.executeQuery();

	        // If resultSet has any data, it means the voter ID already exists
	        return !resultSet.next(); // If there's no match, the voter ID is unique

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // In case of any error, assume voter ID is not unique
	    }
	}
	
	@Override
	public String getNameByVoterId(int voter_id) {
	    String query = "SELECT name FROM voters WHERE voter_id = ?";
	    String name = null; // Initialize userName to null

	    try (Connection connection = DBUtil.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	        preparedStatement.setInt(1, voter_id); // Set the voter ID in the query
	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) { // If a result is found
	            name = resultSet.getString("name"); // Retrieve the name from the result set
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return name; // Return the name (or null if not found)
	}
	
	
	@Override
	public boolean isValidAdmin(String admin_username, String security_key) {
		String query = "SELECT * FROM admin WHERE username = ? AND security_key = ?"; 
		
        try (
            Connection connection = DBUtil.getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        	System.out.println("preparedStatement before: " + preparedStatement);

            preparedStatement.setString(1, admin_username);
            preparedStatement.setString(2, security_key);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("resultSet: " + resultSet);
            
            boolean userExists = resultSet.next(); // resultSet.next() will be TRUE if a matching record is found, otherwise false
            System.out.println("result of resultSet.next(): " + userExists);
            System.out.println("Query executed successfully!");            
            return userExists;
                    
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return FALSE if any error occurs during database operations
        }
	}
	
	 @Override
	    public boolean hasVoted(int voterId) {
		 String query = "SELECT has_voted FROM voters WHERE voter_id = ?";
	        try {
	            Connection connection = DBUtil.getConnection();
	            PreparedStatement pstmt = connection.prepareStatement(query);
	            pstmt.setInt(1, voterId);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next() && rs.getInt(1) > 0) {
	                return true; // Voter has already voted
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false; // Voter has not voted
	    }
	
	 @Override
	 public void castVote(int voter_id, int party_id) {
	     if (hasVoted(voter_id)) {
	         throw new IllegalStateException("Voter has already cast their vote.");
	     }

	     String voteQuery = "UPDATE party SET vote_count = vote_count + 1 WHERE party_id = ?";
	     String voterUpdateQuery = "UPDATE voters SET has_voted = 1 WHERE voter_id = ?";

	     try (Connection connection = DBUtil.getConnection()) {
	         connection.setAutoCommit(false); // Start transaction

	         try (
	              PreparedStatement voteStmt = connection.prepareStatement(voteQuery);
	              PreparedStatement voterStmt = connection.prepareStatement(voterUpdateQuery)) {

	             // 2. Update the party vote count
	             voteStmt.setInt(1, party_id);
	             voteStmt.executeUpdate();

	             // 3. Mark the voter as having voted
	             voterStmt.setInt(1, voter_id);
	             voterStmt.executeUpdate();

	             connection.commit(); // Commit transaction
	         } catch (SQLException e) {
	             connection.rollback(); // Rollback transaction on error
	             e.printStackTrace();
	             throw new RuntimeException("Failed to cast vote.");
	         }
	     } catch (SQLException e) {
	         e.printStackTrace();
	         throw new RuntimeException("Database error.");
	     }
	 }




}
