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
        String query = "select * from products where name = ?";
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
        String query = "select * from products";
        try {
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setId(rs.getInt("pid"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategoryId(rs.getInt("cid"));
                products.add(product);
                
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
            
            return rowsAffected > 0;
        } catch (SQLException ex) {
            logger.fatal("Error in adding product : "+ex); 
            return false;
        }
    }

    @Override
    public List<Products> getProductsByName(String name) {
        List<Products> products = new ArrayList<>();
        String query = "select * from products where name like ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + name + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setId(rs.getInt("pid"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
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
        String query = "select * from products where pid = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                product = new Products();
                product.setId(rs.getInt("pid"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategoryId(rs.getInt("cid"));
            }
        } catch (Exception ex) {
            logger.error("Error in getProductById: " + ex.getMessage());
        }
        return product;
    }

    @Override
    public boolean updateProduct(int id, Products product) {
        String query = "update products set name = ?, price = ?, quantity = ?, cid = ? where pid = ?";
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
        String query = "delete from products where pid = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception ex) {
            logger.error("Error in deleteProduct: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Products> filterProductsByName(String name) {
        List<Products> products = new ArrayList<>();
        String query = "select * from products where name like ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1,name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategoryId(rs.getInt("cid"));
                products.add(product);
            }
        } catch (Exception ex) {
            logger.error("Error in filterProductsByName: " + ex.getMessage());
        }
        return products;
    }

    @Override
    public List<Products> filterProductsByPriceRange(int minPrice, int maxPrice) 
    {
        List<Products> products = new ArrayList<>();
        String query = "select * from products where price between ? and ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, minPrice);
            stmt.setInt(2, maxPrice);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategoryId(rs.getInt("cid"));
                products.add(product);
                
            }
        } catch (Exception ex) {
            logger.error("Error in filterProductsByPriceRange: " + ex.getMessage());
        }
        return products;
    }

	@Override
	public List<Products> getProductsByCategory(String categoryName) {
	    List<Products> products = new ArrayList<>();
	    String query = 
	        "select p.pid, p.name, p.price, p.quantity, p.cid from products p inner join productcategories c on p.cid = c.cid where c.name = ? ";
	
	    try {
	        stmt = conn.prepareStatement(query);
	        stmt.setString(1, categoryName);
	        rs = stmt.executeQuery();
	        while (rs.next()) {
	            Products product = new Products();
	            product.setId(rs.getInt("pid"));
	            product.setName(rs.getString("name"));
	            product.setPrice(rs.getInt("price"));
	            product.setQuantity(rs.getInt("quantity"));
	            product.setCategoryId(rs.getInt("cid"));
	            products.add(product);
	        }
	        logger.info("Retrieved products for category: " + categoryName);
	    } catch (Exception ex) {
	        logger.error("Error in getProductsByCategory: " + ex.getMessage());
	    }
	    return products;
	}

}
