package org.project.services;

import org.project.repository.CartsRepo;
import org.project.repository.CartsRepoImpl;

public class CartServiceImpl implements CartService {

    CartsRepo cartRepo = new CartsRepoImpl();
    
	@Override
	public boolean addProductToCart(String crtUser, String crtCat, String crtProd, int qty) {
		
		return cartRepo.addProductToCart(crtUser, crtCat, crtProd, qty);
	}
}
