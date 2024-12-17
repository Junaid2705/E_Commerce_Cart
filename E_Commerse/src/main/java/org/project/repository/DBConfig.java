package org.project.repository;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import static java.lang.System.*;

public class DBConfig {

    // PathHelper content

    // DBConfig content
    private static Connection conn =null;
    private static PreparedStatement pstmt=null;
    private static Statement stmt=null;
    private static ResultSet rs=null;
    private static DBConfig db=null;

    // Constructor initializes PathHelper and DBConfig
    private DBConfig() throws IOException {
        // PathHelper logic
        File file = new File("");
        FileReader fr=new FileReader(file.getAbsolutePath()+"\\src\\main\\resources\\application.properties");
//.substring(0,file.getAbsolutePath().length()
        // DBConfig logic
        Properties p = new Properties();
        
        try {
            p.load(fr);
            // Handle IOException for loading the file
            String driver = p.getProperty("Driver");
            String url = p.getProperty("url");
            String password = p.getProperty("password");
            String username = p.getProperty("username");

            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            if (conn != null) {
                System.out.println("Connection is successful");
            } else {
                System.out.println("Connection failed");
                System.out.println("Helooooo");
            }

        } catch (Exception ex) {
        	System.out.println("Error is " + ex);
        	ex.getStackTrace();
        	System.out.println("Good Morning");
        }
    } // Constructor ends

    // Singleton instance
    public static DBConfig getInstance() {
        if (db == null) {
            try {
                db = new DBConfig();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Hello");
            }
        }
        return db;
    }

    public Connection getConnection() {
        return conn;
    }

    public PreparedStatement getPreparedStatement() {
        return pstmt;
    }

    public Statement getStatement() {
        return stmt;
    }

    public ResultSet getResultSet() {
        return rs;
    }
}
