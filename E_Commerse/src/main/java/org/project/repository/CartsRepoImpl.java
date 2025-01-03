package org.project.repository;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CartsRepoImpl extends DBConnections implements CartsRepo {
    static Logger logger = Logger.getLogger(CartsRepoImpl.class);
    static {
        PropertyConfigurator
                .configure("F:\\E-Commerce Cart System\\E_Commerse\\src\\main\\resources\\logApplication.properties");
    }

    @Override
    public boolean addProductsToCart(String crtUser, List<String> crtCats, List<String> crtProds, List<Integer> qtys) {
        int uId = 0;
        String uIdQuery = "select uid from users where uname=?";

        try {
            stmt = conn.prepareStatement(uIdQuery);
            stmt.setString(1, crtUser);
            rs = stmt.executeQuery();

            if (rs.next()) {
                uId = rs.getInt("uid");
            }
            //System.out.println("User Id:" + uId);
        } catch (Exception e) {
            logger.fatal("Exception generated when we fetch User id from table :" + e);
            return false;
        }

        if (crtCats.size() != crtProds.size() || crtProds.size() != qtys.size()) {
            logger.error("Input lists size mismatch: crtCats, crtProds, and qtys must have the same size.");
            return false;
        }

        int result = 0;

        for (int i = 0; i < crtCats.size(); i++) {
            int cId = 0, pId = 0;
            String crtCat = crtCats.get(i);
            String crtProd = crtProds.get(i);
            int qty = qtys.get(i);

            String cIdQuery = "select cid from productCategories where name=?";
            String pIdQuery = "select pid from products where name=?";

            try {
                stmt = conn.prepareStatement(cIdQuery);
                stmt.setString(1, crtCat);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    cId = rs.getInt("cid");
                }
                //System.out.println("Category Id:" + cId);
            } catch (Exception e) {
                logger.fatal("Exception generated when we fetch Category id from table :" + e);
                return false;
            }

            try {
                stmt = conn.prepareStatement(pIdQuery);
                stmt.setString(1, crtProd);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    pId = rs.getInt("pid");
                }
                //System.out.println("Product Id:" + pId);
            } catch (Exception e) {
                logger.fatal("Exception generated when we fetch Product id from table :" + e);
                return false;
            }

            try {
                stmt = conn.prepareStatement("insert into carts (ctid ,uid, pid, cid, quantity, purchase_date) values ('0',?,?,?,?,NOW())");
                stmt.setInt(1, uId);
                stmt.setInt(2, pId);
                stmt.setInt(3, cId);
                stmt.setInt(4, qty);
                result += stmt.executeUpdate();
            } catch (Exception e) {
                logger.fatal("Exception generated when we Insert Data in Cart Table :" + e);
                return false;
            }
        }

        return result == crtCats.size();
    }

    @Override
    public List<String[]> viewCart(String crtUser) {
        List<String[]> cartItems = new ArrayList<>();
        String query = "select p.name as product_name, pc.name as category_name, "
        		+ "c.quantity, c.purchase_date from carts c join products p on "
        		+ "c.pid = p.pid join productCategories pc on c.cid = pc.cid join "
        		+ "users u on c.uid = u.uid where u.uname = ?";

        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, crtUser);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String[] cartItem = new String[4];
                cartItem[0] = rs.getString("category_name");  
                cartItem[1] = rs.getString("product_name");    
                cartItem[2] = String.valueOf(rs.getInt("quantity"));
                cartItem[3] = rs.getString("purchase_date");   
                cartItems.add(cartItem);
            }
        } catch (Exception e) {
            logger.fatal("Exception occurred while fetching the cart for user: " + crtUser + " - " + e);
        }

        return cartItems;
    }

    @Override
    public boolean clearCart(String crtUser) {
        int uId = 0;
        String uIdQuery = "select uid from users where uname=?";
        String deleteQuery = "delete from carts where uid=?";
        try {
            stmt = conn.prepareStatement(uIdQuery);
            stmt.setString(1, crtUser);
            rs = stmt.executeQuery();

            if (rs.next()) {
                uId = rs.getInt("uid");
            }
        } catch (Exception e) {
            logger.fatal("Exception occurred while fetching User ID for clearing the cart: " + e);
            return false;
        }
        try {
            stmt = conn.prepareStatement(deleteQuery);
            stmt.setInt(1, uId);
            int result = stmt.executeUpdate();

            if (result > 0) {
                logger.info("Cart cleared for user: " + crtUser);
                return true;
            } else {
                logger.warn("Cart was already empty for user: " + crtUser);
                return false;
            }
        } catch (Exception e) {
            logger.fatal("Exception occurred while clearing the cart for user: " + crtUser + " - " + e);
            return false;
        }
    }

    @Override
    public boolean updateCart(String crtUser, String crtCat, String crtProd, int qty) {
        int uId = 0, cId = 0, pId = 0;

        String uIdQuery = "select uid from users where uname=?";
        try {
            stmt = conn.prepareStatement(uIdQuery);
            stmt.setString(1, crtUser);
            rs = stmt.executeQuery();
            if (rs.next()) {
                uId = rs.getInt("uid");
            }
        } catch (Exception e) {
            logger.fatal("Exception occurred while fetching User ID: " + e);
            return false;
        }

        String cIdQuery = "select cid from productCategories where name=?";
        try {
            stmt = conn.prepareStatement(cIdQuery);
            stmt.setString(1, crtCat);
            rs = stmt.executeQuery();
            if (rs.next()) {
                cId = rs.getInt("cid");
            }
        } catch (Exception e) {
            logger.fatal("Exception occurred while fetching Category ID: " + e);
            return false;
        }

        String pIdQuery = "select pid from products where name=?";
        try {
            stmt = conn.prepareStatement(pIdQuery);
            stmt.setString(1, crtProd);
            rs = stmt.executeQuery();
            if (rs.next()) {
                pId = rs.getInt("pid");
            }
        } catch (Exception e) {
            logger.fatal("Exception occurred while fetching Product ID: " + e);
            return false;
        }

        String checkQuery = "select quantity from carts where uid=? and pid=? and cid=?";
        try {
            stmt = conn.prepareStatement(checkQuery);
            stmt.setInt(1, uId);
            stmt.setInt(2, pId);
            stmt.setInt(3, cId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                
                String updateQuery = "update carts set quantity=? where uid=? and pid=? and cid=?";
                stmt = conn.prepareStatement(updateQuery);
                stmt.setInt(1, qty);
                stmt.setInt(2, uId);
                stmt.setInt(3, pId);
                stmt.setInt(4, cId);
                stmt.executeUpdate();
                logger.info("Updated product quantity in the cart for user: " + crtUser);
            } else {
                String insertQuery = "insert into carts (ctid ,uid, pid, cid, quantity, purchase_date) values ('0',?,?,?,?,NOW())";
                stmt = conn.prepareStatement(insertQuery);
                stmt.setInt(1, uId);
                stmt.setInt(2, pId);
                stmt.setInt(3, cId);
                stmt.setInt(4, qty);
                stmt.executeUpdate();
                logger.info("Added new product to the cart for user: " + crtUser);
            }
        } catch (Exception e) {
            logger.fatal("Exception occurred while updating or adding product to the cart: " + e);
            return false;
        }

        return true;
    }


}
