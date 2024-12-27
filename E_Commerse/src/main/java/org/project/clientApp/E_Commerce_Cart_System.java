package org.project.clientApp;

import java.util.List;
import java.util.Scanner;
import org.project.models.LoginModel;
import org.project.services.CartService;
import org.project.services.CartServiceImpl;
import org.project.services.LoginService;
import org.project.services.LoginServiceImp;
import org.project.models.ProductCatModel;
import org.project.services.ProductCatService;
import org.project.services.ProductCatServiceImpl;
import org.project.models.Products;
import org.project.services.ProductService;
import org.project.services.ProductServiceImp;
import org.apache.log4j.*;

public class E_Commerce_Cart_System {
	static Logger logger = Logger.getLogger(E_Commerce_Cart_System.class);
	static {
		PropertyConfigurator
				.configure("F:\\E-Commerce Cart System\\E_Commerse\\src\\main\\resources\\logApplication.properties");
	}

	public static void main(String[] args) 
	{

		Scanner sc = new Scanner(System.in);
		LoginService loginService = new LoginServiceImp();
		LoginModel loginModel = new LoginModel();
		ProductCatService productCatService = new ProductCatServiceImpl();
		ProductCatModel productCatModel = new ProductCatModel();
		ProductService productService = new ProductServiceImp();
		CartService crtService = new CartServiceImpl();
		boolean value = true;
		boolean isAdminLoggedIn = false;
		boolean isUserLoggedIn = false;
		logger.info("Main Method Started");
		System.out.println("Welcome to E-Commerce Cart...");
		while (value) {
			System.out.println("Please choose an option:");
			System.out.println("1. Login");
			System.out.println("2. Check Product Category Availability");
			System.out.println("3. Show All Product Categories");
			if (isAdminLoggedIn) {
				System.out.println("4. Admin Options");
			}
			if (isUserLoggedIn) {
				System.out.println("5. User Options");
			}
			System.out.println("0. Exit");
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
					logger.info("Login Succesful");
					System.out.print("Enter email: ");
					String email = sc.next();
					System.out.print("Enter password: ");
					String password = sc.next();

					loginModel.setEmail(email);
					loginModel.setPassword(password);

					boolean isUser = loginService.isUser(loginModel);

					if (isUser) {
						System.out.println("Login successful");

						String userType = loginModel.getUserType();
						if ("admin".equalsIgnoreCase(userType)) {
							System.out.println("Welcome Admin! You have full access.");
							isAdminLoggedIn = true;
						} else {
							isAdminLoggedIn = false;
						}
						if ("user".equalsIgnoreCase(userType)) {
							System.out.println("Welcome " + loginModel.getName() + ", you have limited access.");
							isUserLoggedIn = true;
						} else {
							isUserLoggedIn = false;
						}
					} else {
						System.out.println("Invalid information.");
					}
					break;

				case 2:
					logger.info("Registration Succesful");
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
				logger.info("Product Availability Checked");
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
				logger.info("All Categories Showed");
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
				logger.info("Admin is Logged in");
				if (!isAdminLoggedIn) {
					System.out.println("Admin options are only available to logged-in admins.");
					break;
				}

				System.out.println("Admin Options:");
				System.out.println("1. Add New Product Category");
				System.out.println("2. Update Product Category");
				System.out.println("3. Delete Product Category");
				System.out.println("4. Add New Product");
				System.out.println("5. Update Product");
				System.out.println("6. Delete Product");
				System.out.println("7. Show All Products");
				System.out.println("8. Filter Products by Price Range");
				System.out.println("9. Filter Products by Category");
				System.out.println("10. Back to Main Menu");
				System.out.print("Enter your choice: ");
				int adminChoice = sc.nextInt();

				switch (adminChoice) {
				case 1:
					logger.info("Category Added Succssfully");
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
					logger.info("Category Updated Succssfully");
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
					logger.info("Category Deleted Succssfully");
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
					logger.info("Product Added successfully");
					System.out.print("Enter new product name: ");
					String productName = sc.next();
					System.out.print("Enter product price: ");
					double productPrice = sc.nextDouble();
					System.out.print("Enter product quantity: ");
					int productQuantity = sc.nextInt();
					System.out.print("Enter product category: ");
					int prodcategory = sc.nextInt();
					Products newProduct = new Products();
					newProduct.setName(productName);
					newProduct.setPrice(productPrice);
					newProduct.setQuantity(productQuantity);
					newProduct.setCategoryId(prodcategory);

					boolean productAdded = productService.addProduct(newProduct);
					if (productAdded) {
						System.out.println("Product \"" + productName + "\" has been added successfully.");
					} else {
						System.out.println("Failed to add product.");
					}
					break;

				case 5:
					logger.info("Product updated successfully");
					System.out.print("Enter the ID of the product to update: ");
					int productIdToUpdate = sc.nextInt();
					System.out.print("Enter new product name: ");
					String updatedProductName = sc.next();
					System.out.print("Enter new product price: ");
					double updatedPrice = sc.nextDouble();
					System.out.print("Enter new product quantity: ");
					int updatedQuantity = sc.nextInt();
					System.out.print("Enter product category: ");
					int updatecategory = sc.nextInt();
					Products updatedProduct = new Products();
					updatedProduct.setName(updatedProductName);
					updatedProduct.setPrice(updatedPrice);
					updatedProduct.setQuantity(updatedQuantity);
					updatedProduct.setCategoryId(updatecategory);
					boolean productUpdated = productService.updateProduct(productIdToUpdate, updatedProduct);
					if (productUpdated) {
						System.out.println("Product updated successfully.");
					} else {
						System.out.println("Failed to update product.");
					}
					break;

				case 6:
					logger.info("Deleted product successfully");
					System.out.print("Enter the ID of the product to delete: ");
					int productIdToDelete = sc.nextInt();

					boolean productDeleted = productService.deleteProduct(productIdToDelete);
					if (productDeleted) {
						System.out.println("Product deleted successfully.");
					} else {
						System.out.println("Failed to delete product.");
					}
					break;

				case 7:
					List<Products> allProducts = productService.getAllProducts();
					if (allProducts.isEmpty()) {
						System.out.println("No products available.");
					} else {
						System.out.println("All Products:");
						for (Products product : allProducts) {
							System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: "
									+ product.getPrice() + ", Quantity: " + product.getQuantity());
						}
					}
					break;
				case 8:
					logger.info("Filtered by Price Range");
					System.out.print("Enter minimum price: ");
					int minPrice = sc.nextInt();
					System.out.print("Enter maximum price: ");
					int maxPrice = sc.nextInt();
					List<Products> filteredByPriceRange = productService.filterProductsByPriceRange(minPrice, maxPrice);
					System.out.println(filteredByPriceRange);
					if (filteredByPriceRange.isEmpty()) {
						System.out
								.println("No products found in the price range " + minPrice + " to " + maxPrice + ".");
					} else {
						System.out.println("Filtered Products by Price Range:");
						for (Products product : filteredByPriceRange) {
							System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: "
									+ product.getPrice() + ", Quantity: " + product.getQuantity());
						}
					}
					break;

				case 9:
					logger.info("Filtered by Category Name");
					System.out.print("Enter category name to filter products: ");
					String adminCategoryFilter = sc.next();
					List<Products> productsByAdminCategory = productService.getProductsByCategory(adminCategoryFilter);
					if (productsByAdminCategory.isEmpty()) {
						System.out.println("No products found for category: " + adminCategoryFilter);
					} else {
						System.out.println("Filtered Products by Category:");
						for (Products product : productsByAdminCategory) {
							System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Price: "
									+ product.getPrice() + ", Quantity: " + product.getQuantity());
						}
					}
					break;

				case 10:
					System.out.println("Returning to the main menu...");
					break;

				default:
					logger.info("Selected Invalid option");
					System.out.println("Invalid choice. Please select a valid option.");
				}
				break;
			case 5:
			    logger.info("User is logged in");
			    if (!isUserLoggedIn) {
			        System.out.println("These options are for logged-in users only.");
			        break;
			    }
			    System.out.println("User Options:");
			    System.out.println("1. Show All Products");
			    System.out.println("2. Filter Products by Category");
			    System.out.println("3. Add Product to Cart");
			    System.out.println("4. Remove Product from Cart");
			    System.out.println("5. View Cart");
			    System.out.println("6. Back to Main Menu");
			    System.out.print("Enter your choice: ");
			    int userChoice = sc.nextInt();
			    switch (userChoice) {
			        case 1:
			            logger.info("Get all products successfully");
			            List<Products> allProducts = productService.getAllProducts();
			            if (allProducts.isEmpty()) {
			                System.out.println("No products available.");
			            } else {
			                System.out.println("All Products:");
			                for (Products product : allProducts) {
			                    System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + 
			                        ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity());
			                }
			            }
			            break;

			        case 2:
			            System.out.print("Enter category name to filter products: ");
			            String userCategoryFilter = sc.next();
			            List<Products> productsByUserCategory = productService.getProductsByCategory(userCategoryFilter);
			            if (productsByUserCategory.isEmpty()) {
			                System.out.println("No products found for category: " + userCategoryFilter);
			            } else {
			                System.out.println("Filtered Products by Category:");
			                for (Products product : productsByUserCategory) {
			                    System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + 
			                        ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity());
			                }
			            }
			            break;

			        case 3:
			        	sc.nextLine();
			        	System.out.println("Enter User name");
						String crtUser = sc.nextLine();
						System.out.println("Enter Category name");
						String crtCat = sc.nextLine();
						System.out.println("Enter Product name");
						String crtProd = sc.nextLine();
						System.out.println("Enter Quantity");
						int qty = sc.nextInt();
						// public boolean isAddCart(String crtUser,String crtCat,String crtProd,int
						// qty);
						boolean b = crtService.addProductToCart(crtUser, crtCat, crtProd, qty);
						logger.info(b);
						System.out.println(crtUser+"\t"+crtCat+"\t"+crtProd+"\t"+qty);
			        	break;

			        case 6: // Back to Main Menu
			            System.out.println("Returning to the main menu...");
			            break;

			        default:
			            logger.info("Selected invalid option");
			            System.out.println("Invalid choice. Please select a valid option.");
			    }
			    break;

			case 0:
				logger.info("System Existed...");
				System.out.println("Exiting the application...");
				value = false;
				break;

			default:
				System.out.println("Invalid choice. Please select a valid option.");
			}
		}
		sc.close();
	}
}