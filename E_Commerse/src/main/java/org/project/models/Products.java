package org.project.models;

public class Products {
	
	private int prodid;
	private String pname;
	private int price;
	private int qty;
	private int categoryId;
	
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public void setProdid1(int categoryId) {
		// TODO Auto-generated method stub
		this.categoryId=categoryId;
	}
	public int getProdid1() {
		return categoryId;
	}
}
