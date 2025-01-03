package org.jdbc.demo;

import java.sql.*;
import java.util.Scanner;
import com.mysql.cj.jdbc.Driver;
public class Demo2 {
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
//			Statement stmt = conn.createStatement();
//			int value = stmt.executeUpdate("insert into  demo values('"+name+"','"+contact+"','"+email+"')");
			
			PreparedStatement pstmt = conn.prepareStatement("insert into  demo values(?,?,?)");
			pstmt.setString(1, name);
			pstmt.setString(2, contact);
			pstmt.setString(3, email);
			
			int value = pstmt.executeUpdate();
			if(value>0)
			{
				System.out.println("Insert Successful");
			}
			else
			{
				System.out.println("Insert Failed Successfully");
			}
			ResultSet rs = pstmt.executeQuery("select *from demo");
			while(rs.next())
			{
				System.out.println(rs.getString("name")+"\t"+rs.getString("email")+"\t"+rs.getString("contact"));
			}
		} else {
			System.out.println("Connection not Successful");
		}
	}
}
