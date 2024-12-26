package org.project.repository;

import java.io.*;
import java.sql.*;
import java.util.Properties;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;
import org.project.clientApp.E_Commerce_Cart_System;

public class DBConfig {
	protected static Connection conn;
	protected static PreparedStatement stmt;
	protected static ResultSet rs;
	private static DBConfig db = null;
	//private static Logger logger = Logger.getLogger(DBConfig.class);
	private static Logger logger = Logger.getLogger(DBConfig.class);
	 static
	 {
		 PropertyConfigurator.configure("F:\\E-Commerce Cart System\\E_Commerse\\src\\main\\resources\\logApplication.properties");
	 }
	private DBConfig() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			File f = new File(".");
			String path = f.getAbsolutePath();

			FileInputStream fin = new FileInputStream(path + "\\src\\main\\resources\\application.properties");

			Properties p = new Properties();
			p.load(fin);
			String driver = p.getProperty("Driver");
			String url = p.getProperty("url");
			String username = p.getProperty("username");
			String password = p.getProperty("password");
			conn = DriverManager.getConnection(url, username, password);

			if (conn != null) {
				logger.info("Database is Connected");
			} else {
				logger.info("Database is connection failed");
			}
		} catch (Exception ex) {
			logger.error("Database connection failed "+ex);
		}

	}

	public static DBConfig getInstance() {
		if (db == null) {
			db = new DBConfig();
		}
		return db;

	}

	public static Connection getConnection() {
		return conn;
	}

	public static PreparedStatement getStatement() {
		return stmt;
	}

	public static ResultSet getResultSet() {
		return rs;
	}

}