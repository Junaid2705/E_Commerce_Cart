package org.project.services;

import org.project.models.Products;
import org.project.repository.ProductRepo;
import org.project.repository.ProductsRepoImp;
import java.util.List;

public class ProductServiceImp implements ProductService {
    private ProductRepo productRepository = new ProductsRepoImp();

	@Override
	public boolean isProduct(Products product) {
		// TODO Auto-generated method stub
		return productRepository.isProduct(product);
	}

	@Override
	public List<Products> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.getAllProducts();
	}

	@Override
	public boolean addProduct(Products product) {
		// TODO Auto-generated method stub
		return productRepository.addProduct(product);
	}

	@Override
	public List<Products> getProductsByName(String name) {
		// TODO Auto-generated method stub
		return productRepository.getAllProducts();
	}

	@Override
	public Products getProductById(int id) {
		// TODO Auto-generated method stub
		return productRepository.getProductById(id);
	}

	@Override
	public boolean updateProduct(int id, Products product) {
		// TODO Auto-generated method stub
		return productRepository.updateProduct(id, product);
	}

	@Override
	public boolean deleteProduct(int id) {
		// TODO Auto-generated method stub
		return productRepository.deleteProduct(id);
	}

	@Override
	public List<Products> filterProductsByName(String name) {
		return productRepository.filterProductsByName(name);
	}

	public List<Products> filterProductsByPriceRange(int minPrice, int maxPrice) {
		// TODO Auto-generated method stub
		return productRepository.filterProductsByPriceRange(minPrice, maxPrice);
	}

	@Override
	public List<Products> getProductsByCategory(String categoryName) {
        if (categoryName == null || categoryName.isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        return productRepository.getProductsByCategory(categoryName);
    }

}