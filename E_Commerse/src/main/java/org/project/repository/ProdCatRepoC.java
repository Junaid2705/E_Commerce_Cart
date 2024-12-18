package org.project.repository;

import org.project.models.ProductCategories;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdCatRepoC implements ProdCatRepoI {

    private Connection conn;
    private PreparedStatement pstmt;
    private Statement stmt;
    private ResultSet rs;

    public ProdCatRepoC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean addCategory(String categoryName) {
        String query = "INSERT INTO productcategories (name) VALUES (?)";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, categoryName);
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Error adding category: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<ProductCategories> getAllCategories() {
        List<ProductCategories> categories = new ArrayList<>();
        String query = "SELECT * FROM productcategories";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                ProductCategories category = new ProductCategories();
                //category.setCid(rs.getInt("cid"));
                category.setCname(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching categories: " + e.getMessage());
        }
        return categories;
    }

    @Override
    public boolean removeCategory(int categoryId) {
        String query = "DELETE FROM productcategories WHERE cid = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, categoryId);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error removing category: " + e.getMessage());
        }
        return false;
    }
}
