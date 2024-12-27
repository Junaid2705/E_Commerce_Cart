package org.project.repository;

import java.util.List;
import org.project.models.Carts;

public interface CartsRepo {

    // Method to add a product to the cart
    

	boolean addProductToCart(String crtUser, String crtCat, String crtProd, int qty);

//    // Method to retrieve a user's cart by user ID
//    List<Carts> getCartByUserId(int uid);
//
//    // Method to remove a product from the cart by product ID and user ID
//    boolean removeProductFromCart(int removeProductId, int uid);
}
