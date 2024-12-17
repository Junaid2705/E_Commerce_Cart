package org.project.repository;
//import java.sql.*;
public class ProductRepoC extends DBConnections implements ProductRepoI
{
	@Override
	public boolean addProdUct()
	{
		if(conn!=null)
		{
			System.out.println("connection is succefull");
			return true;
		}
		else
		{
			System.out.println("Connection is failed");
			return false;
		}
		
	}

}
