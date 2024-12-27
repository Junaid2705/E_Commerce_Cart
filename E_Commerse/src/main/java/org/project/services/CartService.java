package org.project.services;

import java.util.List;
import org.project.models.Carts;

public interface CartService {

    // Add a product to the cart
	boolean addProductToCart(String crtUser, String crtCat, String crtProd, int qty);

}
