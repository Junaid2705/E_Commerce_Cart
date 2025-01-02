 package org.project.models;

public class ProductCatModel {
	
//	mysql> desc  productcategories;
//	+-------+--------------+------+-----+---------+----------------+
//	| Field | Type         | Null | Key | Default | Extra          |
//	+-------+--------------+------+-----+---------+----------------+
//	| cid   | int          | NO   | PRI | NULL    | auto_increment |
//	| name  | varchar(200) | NO   | UNI | NULL    |                |

	private  int cid ;
	private String name;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}