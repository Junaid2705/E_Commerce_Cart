package org.project.services;

import java.util.List;

import org.project.repository.CartsRepo;
import org.project.repository.CartsRepoImpl;

public class CartServiceImpl implements CartService {

    CartsRepo cartRepo = new CartsRepoImpl();
	@Override
	public boolean addProductsToCart(String crtUser, List<String> crtCats, List<String> crtProds, List<Integer> qty) {
		// TODO Auto-generated method stub
		return cartRepo.addProductsToCart(crtUser, crtCats, crtProds, qty);
	}
	@Override
	public List<String[]> viewCart(String crtUser) {
		// TODO Auto-generated method stub
		return cartRepo.viewCart(crtUser);
	}
	@Override
	public boolean clearCart(String crtUser) {
		// TODO Auto-generated method stub
		return cartRepo.clearCart(crtUser);
	}
	@Override
	public boolean updateCart(String crtUser, String crtCat, String crtProd, int qty) {
		// TODO Auto-generated method stub
		return cartRepo.updateCart(crtUser, crtCat, crtProd, qty);
	}
}