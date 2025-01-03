package org.project.services;

import java.util.List;

import org.project.models.ProductCatModel;

public interface ProductCatService {

	 boolean isProductCat(ProductCatModel procat);

//	 get all categories  ==>
	 
	 List<ProductCatModel> getAllProductCats();  
	 
	  boolean addProductCat(ProductCatModel procat); 
	  List<ProductCatModel> getProductCatsByName(String name);
	    ProductCatModel getProductCatById(int id);
	    boolean updateProductCat(int id, ProductCatModel procat);
	    boolean deleteProductCat(int id);
	    List<ProductCatModel> filterProductCatsByName(String name);
	    List<ProductCatModel> filterProductCatsByPriceRange(double minPrice, double maxPrice);
		
}