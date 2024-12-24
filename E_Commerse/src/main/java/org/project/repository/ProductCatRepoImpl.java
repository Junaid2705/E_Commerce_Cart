
package org.project.repository;

import org.project.models.ProductCatModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCatRepoImpl extends DBConnections implements ProductCatRepo {

	@Override
	public boolean isProductCat(ProductCatModel procat) {
		// Query to check the category name and stuff
		String query = "select  * from productcategories where name = ?";
		try {

			stmt = conn.prepareStatement(query);

			// Set the query para/
			stmt.setString(1, procat.getName());

			rs = stmt.executeQuery();

			// If the result set contains data, return true (category exists)
			return rs.next();

		} catch (SQLException ex) {
			System.out.println("Error in isProductCat: " + ex.getMessage());
			return false;
		}
	}

	public List<ProductCatModel> getAllProductCats() {
		List<ProductCatModel> categories = new ArrayList<>();
		String query = "select  * from  productcategories";
		try {
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				ProductCatModel category = new ProductCatModel();
				category.setName(rs.getString("name"));

				// here we add product cat list in list ok met
				categories.add(category);
			}
		} catch (SQLException ex) {
			System.out.println("error in product catageries : " + ex.getMessage());
		}
		return categories;
	}

	public boolean addProductCat(ProductCatModel procat) {
		// only add new category
		String query = "insert into  productcategories (name) values  (?)";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, procat.getName()); // Set the category name
			int values = stmt.executeUpdate();

			return values > 0; // Return true if the category is added successfully
		} catch (SQLException ex) {
			System.out.println("Error in addProductCat: " + ex.getMessage());
			return false; // Return false if there's an error
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
            System.out.println("Error in getProductCatsByName: " + ex.getMessage());
        }
        return categories;
	}

	@Override
	public ProductCatModel getProductCatById(int id) {
		ProductCatModel category = null;
        String query = "select * from productcategories where id = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                category = new ProductCatModel();
                category.setName(rs.getString("name"));
            }
        } catch (SQLException ex) {
            System.out.println("Error in getProductCatById: " + ex.getMessage());
        }
        return category;

	}

	@Override
	public boolean updateProductCat(int id, ProductCatModel procat) {
		 String query = "update productcategories set name = ? where id = ?";
	        try {
	            stmt = conn.prepareStatement(query);
	            stmt.setString(1, procat.getName());
	            stmt.setInt(2, id);
	            int values = stmt.executeUpdate();
	            return values > 0;
	        } catch (SQLException ex) {
	            System.out.println("Error in updateProductCat: " + ex.getMessage());
	            return false;
	        }
	}

	@Override
	public boolean deleteProductCat(int id) {
		String query = "delete from productcategories where id = ?";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            int values = stmt.executeUpdate();
            return values > 0;
        } catch (SQLException ex) {
            System.out.println("Error in deleteProductCat: " + ex.getMessage());
            return false;
        }
        }
        
}