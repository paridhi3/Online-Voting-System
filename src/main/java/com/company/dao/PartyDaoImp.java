package com.company.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.company.dao.PartyDaoImp;

import com.company.util.DBUtil;

import jakarta.servlet.http.Part;

public class PartyDaoImp implements PartyDao {
	
	@Override
	public List<Party> getAllParties() {
        List<Party> partyList = new ArrayList<>();
        String query = "SELECT * FROM party ORDER BY vote_count DESC"; 

        try (
        		Connection connection = DBUtil.getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        		ResultSet resultSet = preparedStatement.executeQuery();

        		while (resultSet.next()) {
        			int party_id = resultSet.getInt("party_id");
        			String party_name = resultSet.getString("party_name");
        			byte[] party_symbol = resultSet.getBytes("party_symbol");
        			int vote_count = resultSet.getInt("vote_count");
        			partyList.add(new Party(party_id, party_name, party_symbol, vote_count));
        			
        			System.out.println("Fetched Party: id=" + party_id + 
                            ", name=" + party_name + 
                            ", vote_count=" + vote_count + 
                            ", party_symbol_length=" + (party_symbol != null ? party_symbol.length : 0));
        		}
        		
        		System.out.println("Reached partyList logging section.");
        		
        		
        } catch (Exception e) {
            e.printStackTrace();
        }

        return partyList;
    }
	
	@Override
	public int addPartyToDatabase(int party_id, String party_name, Part partySymbolPart) {
	    String query = "INSERT INTO party (party_id, party_name, party_symbol) VALUES (?, ?, ?)";
	    try (
	         Connection connection = DBUtil.getConnection(); 
	         PreparedStatement preparedStatement = connection.prepareStatement(query);
	         InputStream symbolStream = partySymbolPart.getInputStream()) { // Define symbolStream here
	         
	        preparedStatement.setInt(1, party_id);
	        preparedStatement.setString(2, party_name);
	        preparedStatement.setBinaryStream(3, symbolStream, (int) partySymbolPart.getSize());
	        
	        int rowsInserted = preparedStatement.executeUpdate();
	        return rowsInserted;
	        
	    } catch (Exception e) { 
	        e.printStackTrace();
	        return -1; // Return -1 if any error occurs during database operations
	    }
	}


	@Override
	public boolean deleteParty(int party_id) {
		String query = "DELETE FROM party WHERE party_id = ?";
	    try (
	         Connection connection = DBUtil.getConnection(); 
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	         
	        preparedStatement.setInt(1, party_id);
	        
	        int rowsDeleted = preparedStatement.executeUpdate();
	        if (rowsDeleted > 0) {
	            System.out.println("Party with ID " + party_id + " was successfully deleted.");
	            return true;
	        } else {
	            System.out.println("No party found with ID " + party_id + ".");
	            return false;
	        }
	        
	    } catch (Exception e) { 
	        e.printStackTrace();
	        return false;
	    }
		
	}


}
