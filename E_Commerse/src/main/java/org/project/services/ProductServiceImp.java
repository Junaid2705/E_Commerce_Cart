//package org.project.services;
//
//import java.util.List;
//import java.sql.*;
//import org.project.models.Products;
//import org.project.repository.*;
//public class ProductServiceImp implements ProductService{
//	private ProductServiceImp productRepo;
//	  
//
//	public ProductServiceImp(Connection connection) { 
//
//	this.productRepo = new ProductServiceImp(DBConfig.getInstance().getConnection());
//
//	}
//	@Override
//	public boolean addProduct(Products product) {
//		// TODO Auto-generated method stub
//		return productRepo.addProduct(product);
//	}
//
//	@Override
//	public boolean updateProduct(Products product) {
//		// TODO Auto-generated method stub
//		return productRepo.updateProduct(product);
//
//
//	}
//
//	@Override
//	public boolean removeProduct(int productId) {
//		// TODO Auto-generated method stub
//		return productRepo.removeProduct(productId);
//	}
//
//	@Override
//	public List<Products> getProductsByCategory(int categoryId) {
//		// TODO Auto-generated method stub
//		return productRepo.getProductsByCategory(categoryId);
//
//	}
//
//	@Override
//	public List<Products> getAllProducts() {
//		// TODO Auto-generated method stub
//		return productRepo.getAllProducts();
//	}
//
//}
