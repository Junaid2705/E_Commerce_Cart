package org.project.services;

import org.project.models.Products;
import java.util.List;

public interface ProductServiceI {

    boolean addProduct(Products product);

    boolean updateProduct(Products product);

    boolean removeProduct(int productId);

    List<Products> getProductsByCategory(int categoryId);

    List<Products> getAllProducts();
}
