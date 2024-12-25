package org.project.repository;

import java.sql.*;
import org.project.models.LoginModel;

public class LoginRepoImp extends DBConnections implements LoginRepo {

    @Override
    public boolean isUser(LoginModel login) {
        String query = "SELECT * FROM users WHERE uemail = ? AND password = ?";

        try {
            // Create a new PreparedStatement for the specific query
            stmt = conn.prepareStatement(query);

            // Set the parameters for the query
            stmt.setString(1, login.getEmail());
            stmt.setString(2, login.getPassword());

            // Execute the query and check if a user exists
            rs = stmt.executeQuery();
            if (rs.next()) {
                // If a result exists, set the user details
                login.setUserType(rs.getString("usertype")); // Fetch and set userType
                login.setName(rs.getString("uname")); // Optional: set name if needed
                return true;
            }
            return false; // No matching user found
        } catch (SQLException ex) {
            System.out.println("Error in isUser: " + ex.getMessage());
            return false;
        }
    }


	
	@Override
	public boolean registerUser(LoginModel login) {
	    String query = "INSERT INTO users (uname, uemail, password, usertype) VALUES (?, ?, ?, ?)";

	    try {
	        stmt = conn.prepareStatement(query);
	        stmt.setString(1, login.getName());  // Set the user's name
	        stmt.setString(2, login.getEmail()); // Set the user's email
	        stmt.setString(3, login.getPassword()); // Set the user's password
	        stmt.setString(4, login.getUserType()); // Set the user's type (e.g., admin, user)

	        int result = stmt.executeUpdate(); // Insert the user into the database
	        return result > 0; // Return true if at least one row was inserted (successful registration)
	    } catch (SQLException ex) {
	        System.out.println("Error in registerUser: " + ex.getMessage());
	        return false; // Registration failed due to an error
	    }
	}

}








//	@Override
//	public boolean registerUser(LoginModel login) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//	
