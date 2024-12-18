package org.project.clientApp;

import java.util.Scanner;
import org.project.services.Login_Services;
import org.project.services.ProdCatSerC;
import org.project.services.ProductServiceC;

import org.project.models.Products;
import org.project.models.ProductCategories;
import org.project.repository.DBConfig;

public class E_Commerce_Cart_System {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Login_Services loginService = new Login_Services();
            ProdCatSerC categoryService = new ProdCatSerC(DBConfig.getInstance().getConnection());
            ProductServiceC productService = new ProductServiceC(null);
            boolean running = true;

            while (running) {
                System.out.println("\n===== Welcome to the Application =====");
                System.out.print("Are you a registered user? (yes/no): ");
                String isRegistered = scanner.nextLine().trim().toLowerCase();

                if (isRegistered.equals("no")) {
                    loginService.register(scanner);
                    System.out.println("You can now proceed to log in.");
                } else if (!isRegistered.equals("yes")) {
                    System.out.println("Invalid input! Please type 'yes' or 'no'.");
                    continue;
                }

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
                        loginService.login("admin", scanner);

                        boolean adminRunning = true;
                        while (adminRunning) {
                            System.out.println("\n===== Admin Menu =====");
                            System.out.println("1. Add Product Category");
                            System.out.println("2. View Product Categories");
                            System.out.println("3. Add Product");
                            System.out.println("4. View All Products");
                            System.out.println("5. Remove Product");
                            System.out.println("6. Remove Category");
                            System.out.println("7. Logout");
                            System.out.print("Enter your choice: ");

                            int adminChoice;
                            try {
                                adminChoice = Integer.parseInt(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input! Please enter a valid number.");
                                continue;
                            }

                            switch (adminChoice) {
                                case 1:
                                    System.out.print("Enter category name: ");
                                    String categoryName = scanner.nextLine();
                                    if (categoryService.addCategory(categoryName)) {
                                        System.out.println("Category added successfully!");
                                    } else {
                                        System.out.println("Failed to add category.");
                                    }
                                    break;

                                case 2:
                                    System.out.println("Viewing all product categories...");
                                    for (ProductCategories category : categoryService.getAllCategories()) {
                                        System.out.println("Category ID: " + category.getCid() + " | Name: " + category.getCname());
                                    }
                                    break;

                                case 3:
                                    try {
                                        System.out.print("Enter product name: ");
                                        String productName = scanner.nextLine();
                                        System.out.print("Enter product price: ");
                                        int price = Integer.parseInt(scanner.nextLine());
                                        System.out.print("Enter product quantity: ");
                                        int quantity = Integer.parseInt(scanner.nextLine());
                                        System.out.print("Enter category ID: ");
                                        int categoryId = Integer.parseInt(scanner.nextLine());

                                        Products newProduct = new Products();
                                        newProduct.setPname(productName);
                                        newProduct.setPrice(price);
                                        newProduct.setQty(quantity);
                                        //newProduct.setCid(categoryId);

                                        if (productService.addProduct(newProduct)) {
                                            System.out.println("Product added successfully!");
                                        } else {
                                            System.out.println("Failed to add product.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input! Please enter valid numbers for price, quantity, and category ID.");
                                    }
                                    break;

                                case 4:
                                    System.out.println("Viewing all products...");
                                    for (Products product : productService.getAllProducts()) {
                                        System.out.println("Product ID: " + product.getProdid() + " | Name: " + product.getPname() + " | Price: " + product.getPrice() + " | Quantity: " + product.getQty());
                                    }
                                    break;

                                case 5:
                                    try {
                                        System.out.print("Enter product ID to remove: ");
                                        int productId = Integer.parseInt(scanner.nextLine());
                                        if (productService.removeProduct(productId)) {
                                            System.out.println("Product removed successfully!");
                                        } else {
                                            System.out.println("Failed to remove product.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input! Please enter a valid product ID.");
                                    }
                                    break;

                                case 6:
                                    try {
                                        System.out.print("Enter category ID to remove: ");
                                        int categoryIdToRemove = Integer.parseInt(scanner.nextLine());
                                        if (categoryService.removeCategory(categoryIdToRemove)) {
                                            System.out.println("Category removed successfully!");
                                        } else {
                                            System.out.println("Failed to remove category.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input! Please enter a valid category ID.");
                                    }
                                    break;

                                case 7:
                                    System.out.println("Logging out...");
                                    adminRunning = false;
                                    break;

                                default:
                                    System.out.println("Invalid choice! Please select from the menu.");
                            }
                        }
                        break;

                    case 2:
                        loginService.login("user", scanner);
                        break;

                    case 3:
                        System.out.println("Exiting the application. Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice! Please select from the menu.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
