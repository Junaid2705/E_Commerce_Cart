package org.project.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.util.LinkedHashMap;

//import org.project.repository.DBConfig;

public class DBConnections {
    protected DBConfig db=DBConfig.getInstance();
    protected Connection conn=db.getConnection();
    protected Statement stmt=db.getStatement();
    protected PreparedStatement pstmt=db.getPreparedStatement();
    protected ResultSet rs=db.getResultSet();	

public DBConnections() {
    try {
        if (conn != null) {
            stmt = conn.createStatement();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}