/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.dao;

import com.mycompany.quanlinhasach.database.JDBCUtil;
import com.mycompany.quanlinhasach.model.PhieuThuTien;
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
 * @author TienDat
 */
public class PhieuThuTienDAO {
    public PhieuThuTienDAO getInstace() {
        return new PhieuThuTienDAO();
    }
    
    public boolean insertPhieuThuTien(PhieuThuTien phieuThuTien) {
        String sql = "INSERT INTO PHIEUTHUTIEN (MaKhachHang, SoTienThu, NgayThuTien, MaNhanVien) "
                + " VALUES (?, ?, ?, ?)";
        try (Connection con = JDBCUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, phieuThuTien.getMaKhachhang());
            pst.setInt(2, phieuThuTien.getSoTienThu());
            pst.setString(3, phieuThuTien.getNgayThuTien());
            pst.setInt(4, phieuThuTien.getMaNhanVien());
            
            int result = pst.executeUpdate();
            
            JDBCUtil.closeConnection(con);
            return result > 0;  
        } catch (SQLException ex) {
            Logger.getLogger(PhieuThuTienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public PhieuThuTien selectPhieuThuTienById(int phieuThuTienID) {
        String sql = "SELECT * FROM PHIEUTHUTIEN WHERE MaPhieuThuTien = ?";
        
        try ( Connection con = JDBCUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(sql) ) {
            
            // Thiết lập giá trị tham số
        pst.setInt(1, phieuThuTienID);

        // Thực thi truy vấn
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                int maPhieuThuTien = rs.getInt("MaPhieuThuTien");
                int maKhachHang = rs.getInt("MaKhachHang");
                int soTienThu = rs.getInt("SoTienThu");
                String ngayThuTien = rs.getString("NgayThuTien");
                int maNhanVien = rs.getInt("MaNhanVien");

                // Khởi tạo đối tượng NhanVien và trả về
                return new PhieuThuTien(maPhieuThuTien, maKhachHang, soTienThu, ngayThuTien, maNhanVien);
            }
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(PhieuThuTienDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
    
    public ArrayList<PhieuThuTien> selectAllPhieuThuTien() {
        ArrayList<PhieuThuTien> ketQua = new ArrayList<>();
        
        try ( Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();     ) {
            String sql = "SELECT * FROM PHIEUTHUTIEN";
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int maPhieuThuTien = rs.getInt("MaPhieuThuTien");
                int maKhachHang = rs.getInt("MaKhachHang");
                int soTienThu = rs.getInt("SoTienThu");
                String ngayThuTien = rs.getString("NgayThuTien");
                int maNhanVien = rs.getInt("MaNhanVien");
                
                PhieuThuTien phieuThuTien = new PhieuThuTien(maPhieuThuTien, maKhachHang, soTienThu, ngayThuTien, maNhanVien);
                ketQua.add(phieuThuTien);
            }
            
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.getStackTrace();
        }
        
        return ketQua;
    }
}
