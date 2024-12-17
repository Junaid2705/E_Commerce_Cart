package org.project.services;

import org.project.repository.*;

public class ProductServiceC implements ProductServiceI {
	
    ProductRepoI pri = new ProductRepoC();

	public boolean addProdUct() {
		// TODO Auto-generated method stub
		return pri.addProdUct();
	}


}
