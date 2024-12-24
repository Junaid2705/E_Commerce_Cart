package org.project.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnections
{
		protected DBConfig config = DBConfig.getInstance();
		protected PreparedStatement stmt=config.getStatement();
		protected ResultSet rs=config.getResultSet();
		protected Connection conn=config.getConnection();	
}
