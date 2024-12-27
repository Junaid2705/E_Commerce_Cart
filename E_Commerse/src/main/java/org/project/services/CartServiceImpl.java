
//Service Carts Class Implementation
package org.project.services;

import org.project.repository.*;

public class CartServiceImpl implements CartService {

	CartsRepo crtRepo = new CartsRepoImpl();
	@Override
	public boolean isAddCart(String crtUser, String crtCat, String crtProd, int qty) {
		// TODO Auto-generated method stub
		return crtRepo.isAddCart(crtUser,crtCat, crtProd,5);
	}

}
