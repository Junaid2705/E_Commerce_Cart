package org.project.repository;

import org.project.models.Products;
import java.util.List;

public interface ProductRepo {
    boolean isProduct(Products product);
    List<Products> getAllProducts();
    boolean addProduct(Products product);
    List<Products> getProductsByName(String name);
    Products getProductById(int id);
    boolean updateProduct(int id, Products product);
    boolean deleteProduct(int id);
    List<Products> filterProductsByName(String name);
    List<Products> filterProductsByPriceRange(double minPrice, double maxPrice);
    List<Products> getProductsByCategory(String categoryName);
}
