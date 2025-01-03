package org.project.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class OrdersRepoImpl extends DBConnections implements OrdersRepo {
	static Logger logger = Logger.getLogger(OrdersRepoImpl.class);
	static {
		PropertyConfigurator
				.configure("F:\\E-Commerce Cart System\\E_Commerse\\src\\main\\resources\\logApplication.properties");
	}
	@Override
	public boolean isTransaction(String uName, String pStatus, String pType) {
		// TODO Auto-generated method stub
		try {
			
			String tQuery = "insert into trans values ('0',(select uid from users where uname=?),?,(select sum(crt.quantity*p.price) from products p inner join carts crt on p.pid=crt.pid where crt.uid=(select uid from users where uname=?)),?);"; 
			
			stmt = conn.prepareStatement(tQuery);
			stmt.setString(1, uName);
			stmt.setString(2, pStatus);
			stmt.setString(3, uName);
			stmt.setString(4, pType);
			
			return stmt.executeUpdate()>0?true:false;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error Generated while add info in transaction table:"+e);
			return false;
		}
		
	}

	@Override
	public List<String> showTransaction(String uName) {
	    List<String> transactionDetails = new ArrayList<>();
	    try {
	        String query = "select group_concat(p.name), group_concat(crt.quantity * p.price), sum(crt.quantity * p.price) from products p inner join carts crt on p.pid = crt.pid where crt.uid = (select uid from users where uname = ?)";
	        		
	        
	        stmt = conn.prepareStatement(query);
	        stmt.setString(1, uName);
	        rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            String productNames = rs.getString(1);
	            String productPrices = rs.getString(2);
	            String totalPrice = rs.getString(3);

	            if (productNames != null) {
	                transactionDetails.add("Products: " + productNames);
	                transactionDetails.add("Prices: " + productPrices);
	                transactionDetails.add("Total Price: " + totalPrice);
	            } else {
	                transactionDetails.add("No transactions found for user: " + uName);
	            }
	        }
	    } catch (Exception e) {
	       logger.error("Error retrieving transaction details: " + e.getMessage());
	    }
	    return transactionDetails;
	}

	@Override
    public List<String> showFinalBill(String uName) {
        List<String> finalBillDetails = new ArrayList<>();
        try {
            String query = "select u.uname, sum(crt.quantity * p.price) as total_bill from users u join carts crt on u.uid = crt.uid join products p on crt.pid = p.pid where u.uname = ? group by u.uname";

            
            stmt = conn.prepareStatement(query);
            stmt.setString(1, uName);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                String userName = rs.getString("uname");
                String totalBill = rs.getString("total_bill");
                finalBillDetails.add("Username: " + userName);
                finalBillDetails.add("Final Bill: " + totalBill);
            } else {
                finalBillDetails.add("No data found for user: " + uName);
            }
        } catch (Exception e) {
           logger.error("Error fetching final bill details: " + e.getMessage());
        }
        return finalBillDetails;
    }


	


}
