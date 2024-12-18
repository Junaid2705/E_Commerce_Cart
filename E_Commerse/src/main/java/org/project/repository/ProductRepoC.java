package org.project.repository;

import org.project.models.Products;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepoC implements ProductRepoI {

    private Connection conn;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet rs;

    public ProductRepoC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean addProduct(Products product) {
        String query = "INSERT INTO products (name, price, quantity, cid) VALUES (?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, product.getPname());
            pstmt.setInt(2, product.getPrice());
            pstmt.setInt(3, product.getQty());
            //pstmt.setInt(3, product.getCid());
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateProduct(Products product) {
        String query = "UPDATE products SET name = ?, price = ?, quantity = ?, cid = ? WHERE pid = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, product.getPname());
            pstmt.setInt(2, product.getPrice());
            pstmt.setInt(3, product.getQty());
            //pstmt.setInt(4, product.getCid());
            pstmt.setInt(4, product.getProdid());
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean removeProduct(int productId) {
        String query = "DELETE FROM products WHERE pid = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, productId);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error removing product: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Products> getProductsByCategory(int categoryId) {
        List<Products> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE cid = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, categoryId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setProdid(rs.getInt("pid"));
                product.setPname(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setQty(rs.getInt("quantity"));
                //product.setCid(rs.getInt("cid"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching products by category: " + e.getMessage());
        }
        return products;
    }

    @Override
    public List<Products> getAllProducts() {
        List<Products> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Products product = new Products();
                product.setProdid(rs.getInt("pid"));
                product.setPname(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setQty(rs.getInt("quantity"));
                //product.setCid(rs.getInt("cid"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching products: " + e.getMessage());
        }
        return products;
    }

}

