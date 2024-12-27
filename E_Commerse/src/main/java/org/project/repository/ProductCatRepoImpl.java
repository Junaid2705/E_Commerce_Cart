package org.project.repository;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.project.clientApp.E_Commerce_Cart_System;
import org.project.models.ProductCatModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCatRepoImpl extends DBConnections implements ProductCatRepo {
	static Logger logger = Logger.getLogger(ProductCatRepoImpl.class);
	 static
	 {
		 PropertyConfigurator.configure("F:\\E-Commerce Cart System\\E_Commerse\\src\\main\\resources\\logApplication.properties");
	 }
    @Override
    public boolean isProductCat(ProductCatModel procat) {
        String query = "select * from productcategories where name = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, procat.getName());
            rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.out.println("Error in isProductCat: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<ProductCatModel> getAllProductCats() {
        List<ProductCatModel> categories = new ArrayList<>();
        String query = "select * from productcategories";
        try {
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ProductCatModel category = new ProductCatModel();
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException ex) {
            logger.error("Error in getAllProductsCats is "+ex);
        }
        return categories;
    }

    @Override
    public boolean addProductCat(ProductCatModel procat) {
        String query = "insert into productcategories (name) values (?)";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, procat.getName());
            int values = stmt.executeUpdate();
            return values > 0;
        } catch (SQLException ex) {
        	logger.error("Error in addProduct is "+ex);
            return false;
        }
    }

    @Override
    public List<ProductCatModel> getProductCatsByName(String name) {
        List<ProductCatModel> categories = new ArrayList<>();
        String query = "select * from productcategories where name like ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ProductCatModel category = new ProductCatModel();
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException ex) {
            logger.error("Error in getProductCatsByName: " + ex.getMessage());
        }
        return categories;
    }

    @Override
    public ProductCatModel getProductCatById(int id) {
        ProductCatModel category = null;
        String query = "select * from productcategories where cid = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                category = new ProductCatModel();
                category.setName(rs.getString("name"));
            }
        } catch (SQLException ex) {
            logger.error("Error in getProductCatById: " + ex.getMessage());
        }
        return category;
    }

    @Override
    public boolean updateProductCat(int id, ProductCatModel procat) {
        String query = "update productcategories set name = ? where cid = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, procat.getName());
            stmt.setInt(2, id);
            int values = stmt.executeUpdate();
            return values > 0;
        } catch (SQLException ex) {
            logger.error("Error in updateProductCat: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteProductCat(int id) {
        String query = "delete from productcategories where cid = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            int values = stmt.executeUpdate();
            return values > 0;
        } catch (SQLException ex) {
            logger.error("Error in deleteProductCat: " + ex.getMessage());
            return false;
        }
    }

    // Filter methods

    public List<ProductCatModel> filterProductCatsByName(String name) {
        List<ProductCatModel> categories = new ArrayList<>();
        String query = "select * from productcategories where name like ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ProductCatModel category = new ProductCatModel();
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException ex) {
            logger.error("Error in filterProductCatsByName: " + ex.getMessage());
        }
        logger.info("Filtered by Category Succesfully");
        return categories;
    }

    public List<ProductCatModel> filterProductCatsByPriceRange(double minPrice, double maxPrice) {
        List<ProductCatModel> categories = new ArrayList<>();
        String query = "select * from productcategories where price >= ? and price <= ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setDouble(1, minPrice);
            stmt.setDouble(2, maxPrice);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ProductCatModel category = new ProductCatModel();
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException ex) {
            logger.error("Error in filterProductCatsByPriceRange: " + ex.getMessage());
        }
        logger.info("Filtered by Price Succesfully");
        return categories;
    }
}
