package org.project.clientApp;

import java.util.Scanner;
import org.project.models.LoginModel;
import org.project.services.LoginService;
import org.project.services.LoginServiceImp;
import org.project.models.ProductCatModel;
import org.project.services.ProductCatService;
import org.project.services.ProductCatServiceImpl;
import java.util.List;

public class E_Commerce_Cart_System {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoginService loginService = new LoginServiceImp();
        LoginModel loginModel = new LoginModel();
        ProductCatService productCatService = new ProductCatServiceImpl();
        ProductCatModel productCatModel = new ProductCatModel();
        boolean value = true;
        boolean isAdminLoggedIn = false; // Track if an admin is logged in

        while (value) {
            System.out.println("Please choose an option:");
            System.out.println("1. Login");
            System.out.println("2. Check Product Category Availability");
            System.out.println("3. Show All Product Categories");
            if (isAdminLoggedIn) {
                System.out.println("4. Admin Options");
            }
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Do you want to login or register?");
                    System.out.println("1. Login");
                    System.out.println("2. Register");
                    System.out.print("Enter choice: ");
                    int actionChoice = sc.nextInt();

                    switch (actionChoice) {
                    case 1: // Login
                        System.out.print("Enter email: ");
                        String email = sc.next();
                        System.out.print("Enter password: ");
                        String password = sc.next();

                        loginModel.setEmail(email);
                        loginModel.setPassword(password);

                        boolean isUser = loginService.isUser(loginModel);

                        if (isUser) {
                            System.out.println("Login successful");

                            // Ensure userType is fetched correctly
                            String userType = loginModel.getUserType();
                            System.out.println("Debug: UserType fetched is: " + userType); // Debugging step

                            if ("admin".equalsIgnoreCase(userType)) {
                                System.out.println("Welcome Admin! You have full access.");
                                isAdminLoggedIn = true; // Set admin login flag
                            } else {
                                System.out.println("Welcome " + loginModel.getName() + ", you have limited access."); // Corrected name
                                isAdminLoggedIn = false; // Ensure admin flag is false for regular users
                            }
                        } else {
                            System.out.println("Invalid information.");
                        }

                        break;


                        case 2:
                            System.out.print("Enter your name: ");
                            String name = sc.next();
                            System.out.print("Enter your email: ");
                            String regEmail = sc.next();
                            System.out.print("Enter your password: ");
                            String regPassword = sc.next();

                            System.out.println("Please choose your user type:");
                            System.out.println("1. Admin");
                            System.out.println("2. User");
                            System.out.print("Enter choice: ");
                            int userTypeChoice = sc.nextInt();

                            String userType = (userTypeChoice == 1) ? "admin" : "user";

                            loginModel.setName(name);
                            loginModel.setEmail(regEmail);
                            loginModel.setPassword(regPassword);
                            loginModel.setUserType(userType);

                            boolean registrationSuccessful = loginService.registerUser(loginModel);
                            if (registrationSuccessful) {
                                System.out.println("Registration successful!");
                            } else {
                                System.out.println("Registration failed. Please try again.");
                            }
                            break;

                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                    }
                    break;

                case 2:
                    System.out.print("Enter product category name to check availability: ");
                    String categoryName = sc.next();
                    productCatModel.setName(categoryName);
                    boolean isCategoryAvailable = productCatService.isProductCat(productCatModel);

                    if (isCategoryAvailable) {
                        System.out.println("The product category \"" + categoryName + "\" is available.");
                    } else {
                        System.out.println("The product category \"" + categoryName + "\" is not available.");
                    }
                    break;

                case 3:
                    List<ProductCatModel> allCategories = productCatService.getAllProductCats();

                    if (allCategories.isEmpty()) {
                        System.out.println("No product categories are available.");
                    } else {
                        System.out.println("All product categories:");
                        for (ProductCatModel category : allCategories) {
                            System.out.println(category.getName());
                        }
                    }
                    break;

                case 4:
                    if (!isAdminLoggedIn) {
                        System.out.println("Admin options are only available to logged-in admins.");
                        break;
                    }

                    System.out.println("Admin Options:");
                    System.out.println("1. Add New Product Category");
                    System.out.println("2. Update Product Category");
                    System.out.println("3. Delete Product Category");
                    System.out.println("4. Back to Main Menu");
                    System.out.print("Enter your choice: ");
                    int adminChoice = sc.nextInt();

                    switch (adminChoice) {
                        case 1:
                            System.out.print("Enter new product category name: ");
                            String newCategory = sc.next();
                            productCatModel.setName(newCategory);
                            boolean categoryAdded = productCatService.addProductCat(productCatModel);

                            if (categoryAdded) {
                                System.out.println("Product category \"" + newCategory + "\" has been added successfully.");
                            } else {
                                System.out.println("Failed to add product category.");
                            }
                            break;

                        case 2:
                            System.out.print("Enter the ID of the category to update: ");
                            int updateId = sc.nextInt();
                            System.out.print("Enter the new name for the category: ");
                            String updatedName = sc.next();
                            productCatModel.setName(updatedName);

                            boolean categoryUpdated = productCatService.updateProductCat(updateId, productCatModel);
                            if (categoryUpdated) {
                                System.out.println("Product category updated successfully.");
                            } else {
                                System.out.println("Failed to update product category.");
                            }
                            break;

                        case 3:
                            System.out.print("Enter the ID of the category to delete: ");
                            int deleteId = sc.nextInt();

                            boolean categoryDeleted = productCatService.deleteProductCat(deleteId);
                            if (categoryDeleted) {
                                System.out.println("Product category deleted successfully.");
                            } else {
                                System.out.println("Failed to delete product category.");
                            }
                            break;

                        case 4:
                            System.out.println("Returning to the main menu...");
                            break;

                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting the application...");
                    value = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
