package org.project.repository;

import org.apache.log4j.*;
import java.sql.*;
import java.util.*;
import org.project.models.Products;

public class ProductsRepoImp extends DBConnections implements ProductRepo {
	
	static Logger logger = Logger.getLogger(ProductsRepoImp.class);
	 static
	 {
		 PropertyConfigurator.configure("F:\\E-Commerce Cart System\\E_Commerse\\src\\main\\resources\\logApplication.properties");
	 }
    @Override
    public boolean isProduct(Products product) {
        String query = "SELECT * FROM products WHERE name = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, product.getName());
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            logger.fatal("Error in isProduct: " + ex);
            return false;
        }
    }

    @Override
    public List<Products> getAllProducts() {
        List<Products> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try {
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setId(rs.getInt("pid"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategoryId(rs.getInt("cid"));
                products.add(product);
                logger.info("Get all products Succesfully");
            }
        } catch (SQLException ex) {
            logger.error("Error in getAllProducts: " + ex.getMessage());
        }
        return products;
    }

    @Override
    public boolean addProduct(Products product) {
        String query = "insert into products (name, price, quantity, cid) values (?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getCategoryId());
            int rowsAffected = stmt.executeUpdate();
            logger.info("Product Added Succesfully");
            return rowsAffected > 0;
        } catch (SQLException ex) {
            logger.fatal("Error in adding product : "+ex); 
            return false;
        }
    }

    @Override
    public List<Products> getProductsByName(String name) {
        List<Products> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE name LIKE ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setId(rs.getInt("pid"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategoryId(rs.getInt("cid"));
                products.add(product);
            }
        } catch (SQLException ex) {
           logger.error("Error in getProductsByName: " + ex.getMessage());
        }
        return products;
    }

    @Override
    public Products getProductById(int id) {
        Products product = null;
        String query = "SELECT * FROM products WHERE pid = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                product = new Products();
                product.setId(rs.getInt("pid"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategoryId(rs.getInt("cid"));
            }
        } catch (SQLException ex) {
            logger.error("Error in getProductById: " + ex.getMessage());
        }
        return product;
    }

    @Override
    public boolean updateProduct(int id, Products product) {
        String query = "UPDATE products SET name = ?, price = ?, quantity = ?, cid = ? WHERE pid = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getCategoryId());
            stmt.setInt(5, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            logger.error("Error in updateProduct: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        String query = "DELETE FROM products WHERE pid = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            logger.error("Error in deleteProduct: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Products> filterProductsByName(String name) {
        List<Products> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE name LIKE ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategoryId(rs.getInt("cid"));
                products.add(product);
            }
        } catch (SQLException ex) {
            logger.error("Error in filterProductsByName: " + ex.getMessage());
        }
        return products;
    }

    @Override
    public List<Products> filterProductsByPriceRange(double minPrice, double maxPrice) {
        List<Products> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE price BETWEEN ? AND ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setDouble(1, minPrice);
            stmt.setDouble(2, maxPrice);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategoryId(rs.getInt("cid"));
                products.add(product);
                logger.info("Filtered by Price Succesfully");
            }
        } catch (SQLException ex) {
            logger.error("Error in filterProductsByPriceRange: " + ex.getMessage());
        }
        return products;
    }
}
