package org.project.services;

import java.util.List;
import org.project.models.Carts;

public interface CartService 
{
	boolean addProductsToCart(String crtUser, List<String> crtCats, List<String> crtProds, List<Integer> qty);
    
	List<String[]> viewCart(String crtUser);
	
	boolean clearCart(String crtUser);
	
	boolean updateCart(String crtUser, String crtCat, String crtProd, int qty);
	//boolean addProductToCart(String crtUser, String crtCat, String crtProd, int qty);
}
