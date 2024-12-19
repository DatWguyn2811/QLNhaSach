/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author TienDat
 */
public class JDBCUtil {
    public static Connection getConnection() throws SQLException {
        Connection c = null;
        
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String url = "jdbc:mysql://localhost:3306/QuanLiNhaSach";
            String username = "root";
            String password = "";
            
            c = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return c;  
    }
    
    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            } 
        } catch (SQLException e) {
                    e.printStackTrace();
        }
    }
}
