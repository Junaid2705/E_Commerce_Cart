package org.project.services;

import org.project.repository.DBConnections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Login_Services extends DBConnections {

    // Register a new user
    public void register(Scanner scanner) {
        System.out.println("\n--- Registration ---");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Are you registering as Admin or User? (Type 'admin' or 'user'): ");
        String userType = scanner.nextLine();

        String query = "INSERT INTO users (uname, uemail, password, usertype) VALUES (?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, userType);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Registration successful! You can now log in.");
            } else {
                System.out.println("Registration failed. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
        }
    }

    // Login an existing user
    public void login(String userType, Scanner scanner) {
        System.out.println("\n--- Login ---");

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (validateLogin(email, password, userType)) {
            System.out.println("Login successful! Welcome, " + userType);
        } else {
            System.out.println("Invalid credentials! Please try again.");
        }
    }

    // Validate login credentials
    private boolean validateLogin(String email, String password, String userType) {
        String query = "select * from users where uemail = ? and password = ? and usertype = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3, userType);

            rs = pstmt.executeQuery();
            return rs.next(); // Returns true if a matching record is found
        } catch (Exception e) {
            System.out.println("Error during login validation: " + e.getMessage());
        }
        return false;
    }
}
