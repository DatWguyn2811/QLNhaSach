/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.dao;

import com.mycompany.quanlinhasach.database.JDBCUtil;
import com.mycompany.quanlinhasach.model.CTHD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class CTHDDAO implements DAOInterface<CTHD> {

    @Override
    public boolean insert(CTHD t) {
        String sql = "INSERT INTO cthd (MaHoaDon, MaSach, ThanhTien, SoLuong) VALUES (?, ?, ?, ?)";
        
        try (Connection con = JDBCUtil.getConnection(); 
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, t.getMaHoaDon());
            pst.setInt(2, t.getMaSach());
            pst.setInt(3, t.getThanhTien());
            pst.setInt(4, t.getSoLuong());
            
            int result = pst.executeUpdate();
            return result > 0;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public int update(CTHD t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(CTHD t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CTHD> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CTHD selectById(CTHD t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CTHD> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
