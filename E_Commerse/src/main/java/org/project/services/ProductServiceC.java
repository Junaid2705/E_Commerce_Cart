package org.project.services;

import java.sql.Connection;
import java.util.List;
import org.project.models.Products;
import org.project.repository.DBConfig;

public class ProductServiceC implements ProductServiceI {

    private ProductServiceC productRepo; // Assuming the class name is ProductCatRepoC

    public ProductServiceC(Connection connection) { // Constructor name matches the class name
        this.productRepo = new ProductServiceC(DBConfig.getInstance().getConnection());
    }

    @Override
    public boolean addProduct(Products product) {
        return productRepo.addProduct(product);
    }

    @Override
    public boolean updateProduct(Products product) {
        return productRepo.updateProduct(product);
    }

    @Override
    public boolean removeProduct(int productId) {
        return productRepo.removeProduct(productId);
    }

    @Override
    public List<Products> getProductsByCategory(int categoryId) {
        return productRepo.getProductsByCategory(categoryId);
    }

    @Override
    public List<Products> getAllProducts() {
        return productRepo.getAllProducts();
    }
}
