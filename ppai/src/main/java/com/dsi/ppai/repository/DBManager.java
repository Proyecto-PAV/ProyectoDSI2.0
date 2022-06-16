package com.dsi.ppai.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ppai?user=root&password=root");
        } catch (SQLException ex) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
