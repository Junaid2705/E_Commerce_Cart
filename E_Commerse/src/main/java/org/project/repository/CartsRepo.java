package org.project.repository;

import java.util.List;

public interface CartsRepo {
	
	    boolean addProductsToCart(String crtUser, List<String> crtCats, List<String> crtProds, List<Integer> qty);

	    List<String[]> viewCart(String crtUser);
	    
	    boolean clearCart(String crtUser);
	    
	    boolean updateCart(String crtUser, String crtCat, String crtProd, int qty);
	    
	//boolean addProductsToCart(String crtUser, String crtCat, String crtProd, int qty);
  
    //List<Carts> getCartByUserId(int uid);

   
     //boolean removeProductFromCart(int removeProductId, int uid);
}
