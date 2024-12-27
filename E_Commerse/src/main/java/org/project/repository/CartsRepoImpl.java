
//Repository Carts Class Implementation
package org.project.repository;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class CartsRepoImpl extends DBConnections implements CartsRepo {
	static Logger logger = Logger.getLogger(CartsRepoImpl.class);
	 static
	 {
		 PropertyConfigurator.configure("F:\\E-Commerce Cart System\\E_Commerse\\src\\main\\resources\\logApplication.properties");
	 }
	@Override
	public boolean isAddCart(String crtUser, String crtCat, String crtProd, int qty) {
		// TODO Auto-generated method stub
		
		int uId=0,cId=0,pId=0;
		
		String uIdQuery = "select uid from users where uname=?"; 
		String cIdQuery = "select cid from productCategories where name=?";
		String pIdQuery = "select pid from products where name=?";
		
		
		try {
			stmt = conn.prepareStatement(uIdQuery);
	        stmt.setString(1, crtUser);
	        rs = stmt.executeQuery();
	        while (rs.next()) {
	           uId=rs.getInt("uid");
	        }
			System.out.println("User Id:"+uId);
			
			stmt = conn.prepareStatement(cIdQuery);
	        stmt.setString(1, crtCat);
	        rs = stmt.executeQuery();
	        while (rs.next()) {
	           cId=rs.getInt("cid");
	        }
	        
			System.out.println("Category Id:"+cId);
			stmt = conn.prepareStatement(cIdQuery);
	        stmt.setString(1, crtCat);
	        rs = stmt.executeQuery();
	        while (rs.next()) {
	           cId=rs.getInt("cid");
	        }
			System.out.println("Category Id:"+cId);
			
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println("When Perfrom Insert Operation on Cart Table"+e);
	
		}

		return false;
	}

}
