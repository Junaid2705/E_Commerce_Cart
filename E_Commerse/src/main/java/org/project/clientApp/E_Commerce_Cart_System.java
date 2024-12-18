package org.project.clientApp;

import java.util.Scanner;

import org.project.services.Login_Services;

public class E_Commerce_Cart_System {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login_Services loginService = new Login_Services();
        boolean running = true;

        while (running) {
            System.out.println("\n===== Welcome to the Application =====");
            System.out.print("Are you a registered user? (yes/no): ");
            String isRegistered = scanner.nextLine().trim().toLowerCase();

            if (isRegistered.equals("no")) {
                // Redirect to registration
                loginService.register(scanner);
                System.out.println("You can now proceed to log in.");
            } else if (!isRegistered.equals("yes")) {
                // Invalid input
                System.out.println("Invalid input! Please type 'yes' or 'no'.");
                continue;
            }

            // Show main menu after registration check
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Admin Login
                    loginService.login("admin", scanner);
                    break;

                case 2:
                    // User Login
                    loginService.login("user", scanner);
                    break;

                case 3:
                    // Exit the application
                    System.out.println("Exiting the application. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice! Please select from the menu.");
            }
        }

        scanner.close(); // Ensure scanner is closed after loop ends
    }
}
