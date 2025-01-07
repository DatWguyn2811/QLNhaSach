/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.dao;

import com.mycompany.quanlinhasach.database.JDBCUtil;
import com.mycompany.quanlinhasach.model.PhieuNhapSach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class PhieuNhapSachDAO implements DAOInterface<PhieuNhapSach>{

    public static PhieuNhapSachDAO getInstance() {
        return new PhieuNhapSachDAO();
    }
//    public void capNhatDonGiaBan(int maPhieuNhapSach, double donGiaNhap, double hsGiaBan ) throws SQLException{
//        String sql = "UPDATE SACH SET DonGia = ? WHERE MaPhieuNhapSach = ?";
//        try (Connection con = JDBCUtil.getConnection();
//             PreparedStatement stmt = con.prepareStatement(sql)) {
//            
//            double donGiaBan = hsGiaBan*donGiaNhap;
//            
//            stmt.setDouble(1, donGiaBan);
//            stmt.setInt(2, maPhieuNhapSach);
//            stmt.executeUpdate();
//        }
//    }
    public void capNhatTongTienPhieuNhap(int maPhieuNhapSach, int tongTien) throws SQLException {
        String sql = "UPDATE PHIEUNHAPSACH SET TongTien = ? WHERE MaPhieuNhapSach = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, tongTien);
            stmt.setInt(2, maPhieuNhapSach);
            stmt.executeUpdate();
        }
    }
    @Override
    public boolean insert(PhieuNhapSach t) {
        String sql = "INSERT INTO PHIEUNHAPSACH (NgayNhap) VALUES (?)";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Chuyển đổi ngayNhap từ String sang java.sql.Date
            java.sql.Date ngayNhapSql = java.sql.Date.valueOf(t.getNgayNhap());

            // Thiết lập các giá trị cho câu lệnh SQL
            stmt.setDate(1, ngayNhapSql);

            // Thực thi câu lệnh SQL
            stmt.executeUpdate();

            // Lấy khóa chính tự động sinh (MaPhieuNhapSach)
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                t.setMaPhieuNhapSach(rs.getInt(1)); // Gán mã phiếu nhập sách cho đối tượng
                return true; // Thêm thành công
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapSachDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; // Thêm thất bại
    }

    @Override
    public int update(PhieuNhapSach t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(PhieuNhapSach t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<PhieuNhapSach> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PhieuNhapSach selectById(PhieuNhapSach t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<PhieuNhapSach> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
