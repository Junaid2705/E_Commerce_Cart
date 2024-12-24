//package org.project.repository;
//
//import java.util.List;
//
//import org.project.models.Products;
//import java.util.*;
//import java.sql.*;
//
//public class ProductsRepoImp implements ProductRepo{
//	private Connection conn;
//
//	private PreparedStatement pstmt;
//
//	private Statement stmt;
//
//	private ResultSet rs;
//	public ProductsRepoImp(Connection conn) {
//
//		this.conn = conn;
//
//		}
//	@Override
//	public boolean addProduct(Products product) {
//		String query = "insert into products (name, price, quantity, cid) values (?, ?, ?, ?)";
//
//		try {
//
//		pstmt = conn.prepareStatement(query);
//
//		pstmt.setString(1, product.getPname());
//
//		pstmt.setInt(2, product.getPrice());
//
//		pstmt.setInt(3, product.getQty());
//
//		//pstmt.setInt(3, product.getCid());
//
//		int val = pstmt.executeUpdate();
//
//		return val > 0;
//
//		} catch (SQLException e) {
//
//		System.out.println("Error adding product: " + e.getMessage());
//
//		}
//		return false;
//	}
//
//	@Override
//	public boolean updateProduct(Products product) {
//		String query = "update products set name = ?, price = ?, quantity = ?, cid = ? where pid = ?";
//
//		try {
//
//		pstmt = conn.prepareStatement(query);
//
//		pstmt.setString(1, product.getPname());
//
//		pstmt.setInt(2, product.getPrice());
//
//		pstmt.setInt(3, product.getQty());
//
//		//pstmt.setInt(4, product.getCid());
//
//		pstmt.setInt(4, product.getProdid());
//
//		int val = pstmt.executeUpdate();
//
//		return val > 0;
//
//		} catch (SQLException e) {
//
//		System.out.println("Error updating product: " + e.getMessage());
//
//		}
//
//		return false;
//	}
//
//	@Override
//	public boolean removeProduct(int productId) {
//		String query = "delete from products where pid = ?";
//
//		try {
//
//		pstmt = conn.prepareStatement(query);
//
//		pstmt.setInt(1, productId);
//
//		int val = pstmt.executeUpdate();
//
//		return val > 0;
//
//		} catch (SQLException e) {
//
//		System.out.println("Error removing product: " + e.getMessage());
//
//		}
//		return false;
//	}
//
//	@Override
//	public List<Products> getProductsByCategory(int categoryId) {
//		List<Products> products = new ArrayList<>();
//
//		String query = "select * from products where cid = ?";
//
//		try {
//
//		pstmt = conn.prepareStatement(query);
//
//		pstmt.setInt(1, categoryId);
//
//		rs = pstmt.executeQuery();
//
//		while (rs.next()) {
//
//		Products product = new Products();
//
//		product.setProdid(rs.getInt("pid"));
//
//		product.setPname(rs.getString("name"));
//
//		product.setPrice(rs.getInt("price"));
//
//		product.setQty(rs.getInt("quantity"));
//
//		//product.setCid(rs.getInt("cid"));
//
//		products.add(product);
//
//		}
//
//		} catch (SQLException e) {
//
//		System.out.println("Error fetching products by category: " + e.getMessage());
//
//		}
//		return products;
//	}
//
//	@Override
//	public List<Products> getAllProducts() {
//		List<Products> products = new ArrayList<>();
//
//		String query = "select * from products";
//
//		try {
//
//		stmt = conn.createStatement();
//
//		rs = stmt.executeQuery(query);
//
//		while (rs.next()) {
//
//		Products product = new Products();
//
//		product.setProdid(rs.getInt("pid"));
//
//		product.setPname(rs.getString("name"));
//
//		product.setPrice(rs.getInt("price"));
//
//		product.setQty(rs.getInt("quantity"));
//
//		//product.setCid(rs.getInt("cid"));
//
//		products.add(product);
//
//		}
//
//		} catch (SQLException e) {
//
//		System.out.println("Error fetching products: " + e.getMessage());
//
//		}
//		return products;
//	}
//
//}
