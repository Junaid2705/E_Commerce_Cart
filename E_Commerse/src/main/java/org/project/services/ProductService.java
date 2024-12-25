package org.project.services;

import org.project.models.Products;
import java.util.List;

public interface ProductService {
	boolean isProduct(Products product);
    List<Products> getAllProducts();
    boolean addProduct(Products product);
    List<Products> getProductsByName(String name);
    Products getProductById(int id);
    boolean updateProduct(int id, Products product);
    boolean deleteProduct(int id);
    List<Products> filterProductsByName(String name);
    List<Products> filterProductsByPriceRange(double minPrice, double maxPrice);

}