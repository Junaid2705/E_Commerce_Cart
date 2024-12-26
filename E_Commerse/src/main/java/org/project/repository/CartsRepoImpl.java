package org.project.repository;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.project.models.Products;

public class CartsRepoImpl extends DBConnections implements CartsRepo {
	static Logger logger = Logger.getLogger(ProductCatRepoImpl.class);
	 static
	 {
		 PropertyConfigurator.configure("F:\\E-Commerce Cart System\\E_Commerse\\src\\main\\resources\\logApplication.properties");
	 }
	@Override
	public boolean isAddCart(int ctId ,String crtUser, String crtCat, String crtProd, int qty) {
	    int ctid =0, uId = 0, cId = 0, pId = 0;
	    boolean isAdded = false;

	    String uIdQuery = "select uid from users where uname = ?";
	    String cIdQuery = "select cid from productCategories where name = ?";
	    String pIdQuery = "select pid from products where name = ?";
	    String insertCartQuery = "insert into carts (ctid ,uid, cid, pid, quantity) values (?,?, ?, ?, ?)";

	    try {
	        // Fetch User ID
	        stmt = conn.prepareStatement(uIdQuery);
	        stmt.setString(1, crtUser);
	        rs = stmt.executeQuery();
	        if (rs.next()) {
	            uId = rs.getInt("uid");
	        }
	        System.out.println("User ID: " + uId);

	        // Fetch Category ID
	        stmt = conn.prepareStatement(cIdQuery);
	        stmt.setString(1, crtCat);
	        rs = stmt.executeQuery();
	        if (rs.next()) {
	            cId = rs.getInt("cid");
	        }
	        System.out.println("Category ID: " + cId);

	        // Fetch Product ID
	        stmt = conn.prepareStatement(pIdQuery);
	        stmt.setString(1, crtProd);
	        rs = stmt.executeQuery();
	        if (rs.next()) {
	            pId = rs.getInt("pid");
	        }
	        System.out.println("Product ID: " + pId);

	        // Insert into Cart
	        stmt = conn.prepareStatement(insertCartQuery);
	        stmt.setInt(1, ctId);
	        stmt.setInt(2, uId);
	        stmt.setInt(3, cId);
	        stmt.setInt(4, pId);
	        stmt.setInt(5, qty);

	        int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            isAdded = true;
	            System.out.println("Product added to cart successfully.");
	        }
	    } catch (Exception e) {
	        logger.fatal("Error while adding to cart "+e);
	    }
	    return isAdded;
	}


}
