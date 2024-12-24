package org.project.repository;

import java.io.*;
import java.sql.*;
import java.util.Properties;

 public class DBConfig
 {
	 protected static Connection conn;
	 protected static PreparedStatement stmt;
	 protected static ResultSet rs;
	 private static DBConfig db=null;
	 private DBConfig()
	 { 
		 try
			 {
				  Class.forName("com.mysql.cj.jdbc.Driver");
				  File f =new File("."); 
				  String path = f.getAbsolutePath();
				  
				  FileInputStream fin = new FileInputStream(path+"\\src\\main\\resources\\application.properties");
				  
				  Properties p = new Properties();
				  p.load(fin);
				  String driver = p.getProperty("Driver");
				  String url = p.getProperty("url");
				  String username = p.getProperty("username");
				  String password = p.getProperty("password");
				  conn = DriverManager.getConnection(url,username,password);
				  
				  //System.out.println(driver+"\n"+username+"\n"+password+"\n"+url);
			 }
			 catch(Exception ex)
			 { 
				 System.out.println("Error is "+ex);
			 }
	
	 }
	 public static DBConfig getInstance()
	 {
		 if(db==null)
		 {
			 db = new DBConfig();
		 }
		return db;
		 
	 }
	 public static Connection getConnection()
	 {if(conn==null)
	 {
		System.out.println("I am Null");
	 }
	return conn;
	 }
	 public static PreparedStatement getStatement()
	 {
		 return  stmt;
	 }
	 public static ResultSet getResultSet()
	 {
		 return rs;
	 }
	 
//	public static void main(String[] args) {
//		DBConfig dbConfig = new DBConfig();
//		System.out.println("Connection "+conn.hashCode());
//	}
 }