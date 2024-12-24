package org.project.services;

import java.util.List;

import org.project.models.Products;

public interface ProductService {
	 

	boolean addProduct(Products product);

	  

	boolean updateProduct(Products product);

	  

	boolean removeProduct(int productId);

	  

	List<Products> getProductsByCategory(int categoryId);

	  

	List<Products> getAllProducts();

}
