package org.project.repository;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.project.models.Carts;

public class CartsRepoImpl extends DBConnections implements CartsRepo {
	static Logger logger = Logger.getLogger(CartsRepoImpl.class);
	static {
		PropertyConfigurator
				.configure("F:\\E-Commerce Cart System\\E_Commerse\\src\\main\\resources\\logApplication.properties");
	}
	@Override
	public boolean addProductToCart(String crtUser, String crtCat, String crtProd, int qty) {
		//System.out.println(crtUser + "\t" + crtCat + "\t" + crtProd + "\t" + qty);
		int uId = 0, cId = 0, pId = 0;

		String uIdQuery = "select uid from users where uname=?";
		String cIdQuery = "select cid from productCategories where name=?";
		String pIdQuery = "select pid from products where name=?";

		// fetch UserId from table

		try {
			stmt = conn.prepareStatement(uIdQuery);
			stmt.setString(1, crtUser);

			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				uId = rs.getInt("uid");
			}
			System.out.println("User Id:" + uId);

		} catch (Exception e) {
			logger.fatal("Exception generated when we fetch User id from table :" + e);
		}

		try {
			stmt = conn.prepareStatement(cIdQuery);
			stmt.setString(1, crtCat);
			rs = stmt.executeQuery();
			if (rs.next()) {
				cId = rs.getInt("cid");
			}
			System.out.println("Category Id:" + cId);

		} catch (Exception e) {
			logger.fatal("Exception generated when we fetch Category id from table :" + e);
		}

		try {
			
			stmt = conn.prepareStatement(pIdQuery);
			stmt.setString(1, crtProd);

			rs = stmt.executeQuery();
			if (rs.next()) {
				pId = rs.getInt("pid");
			}
			System.out.println("Product Id:" + pId);
			
		} catch (Exception e) {
			logger.fatal("Exception generated when we fetch product id from table :" + e);
		}
		int result=0;
		
		try {
			
			stmt = conn.prepareStatement("INSERT INTO carts (ctid ,uid, pid, cid, quantity, purchase_date) VALUES ('0',?,?,?,?,NOW())");
			
			stmt.setInt(1, uId);
			stmt.setInt(2, pId);
			stmt.setInt(3, cId);
			stmt.setInt(4, qty);
			 result = stmt.executeUpdate();
			
			
		} catch (Exception e) {
			
			logger.fatal("Exception generated when we Insert Data in Cart Table :" + e);
		}
	
		return result>0?true:false;
	}

}