package org.jdbc.demo;

import java.sql.*;
import java.util.Scanner;
import com.mysql.cj.jdbc.Driver;
public class Demo {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/test";
	private static final String username = "root";
	private static final String password = "root";
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		//com.mysql.cj.jdbc.Driver d = new com.mysql.cj.jdbc.Driver();
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url,username,password);
		 if (conn!=null) {
			System.out.println("Connection Successful");
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Name");
			String name = sc.nextLine();
			System.out.println("Enter Contact");
			String contact = sc.nextLine();
			System.out.println("Enter Email");
			String email = sc.nextLine();
			Statement stmt = conn.createStatement();
			int value = stmt.executeUpdate("insert into  demo values('"+name+"','"+contact+"','"+email+"')");
			if(value>0)
			{
				System.out.println("Insert Successful");
			}
			else
			{
				System.out.println("Insert Failed Successfully");
			}
			
		} else {
			System.out.println("Connection not Successful");
		}
	}
}
