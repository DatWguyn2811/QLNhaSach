/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.dao;

import com.mycompany.quanlinhasach.database.JDBCUtil;
import com.mycompany.quanlinhasach.model.CT_PhieuNhapSach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class CT_PhieuNhapSachDAO implements DAOInterface<CT_PhieuNhapSach> {

    @Override
        public boolean insert(CT_PhieuNhapSach t) {
        String sql = "INSERT INTO CT_PHIEUNHAPSACH (MaPhieuNhapSach, MaSach, SoLuongNhap, DonGiaNhap, ThanhTien) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            // Thiết lập các giá trị cho câu lệnh SQL
            stmt.setInt(1, t.getMaPhieuNhapSach());
            stmt.setInt(2, t.getMaSach());
            stmt.setInt(3, t.getSoLuongNhap());
            stmt.setDouble(4, t.getDonGiaNhap());
            stmt.setDouble(5, t.getThanhTien());

            // Thực thi câu lệnh SQL
            int rowsInserted = stmt.executeUpdate();

            // Nếu số dòng bị ảnh hưởng > 0, tức là thêm thành công
            return rowsInserted > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CT_PhieuNhapSachDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Trả về false nếu xảy ra lỗi
        }
    }

    @Override
    public int update(CT_PhieuNhapSach t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(CT_PhieuNhapSach t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CT_PhieuNhapSach> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CT_PhieuNhapSach selectById(CT_PhieuNhapSach t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CT_PhieuNhapSach> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
