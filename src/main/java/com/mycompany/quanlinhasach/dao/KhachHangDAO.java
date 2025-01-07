/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.dao;

import com.mycompany.quanlinhasach.database.JDBCUtil;
import com.mycompany.quanlinhasach.model.KhachHang;
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
public class KhachHangDAO {
    public KhachHangDAO getInstance() {
        return new KhachHangDAO();
    }
    
    public boolean insertKhachHang(KhachHang kh) {
        String sql = "INSERT INTO `KHACHHANG` (`TenKhachHang`, `DiaChi`, `SoDienThoai`, `Email`, `TongNo`) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = JDBCUtil.getConnection();
                PreparedStatement pst = con.prepareStatement(sql);) {
            pst.setString(1, kh.getTenKhachHang());
            pst.setString(2, kh.getDiaChi());
            pst.setString(3, kh.getSoDienThoai());
            pst.setString(4, kh.getEmail());
            pst.setInt(5, kh.getTongNo());
            
            int result = pst.executeUpdate();
            
            return result > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }
    
    public boolean updateKhachHang(KhachHang kh) {
        String sql = "UPDATE `KHACHHANG` SET"
                + " TenKhachHang = ?, "
                + " DiaChi = ?, "
                + " SoDienThoai = ?, "
                + " Email = ? "
                + " WHERE MaKhachHang = ?";
        
        try ( Connection con = JDBCUtil.getConnection();
                PreparedStatement pst = con.prepareStatement(sql); ) {
            
            pst.setString(1, kh.getTenKhachHang());
            pst.setString(2, kh.getDiaChi());
            pst.setString(3, kh.getSoDienThoai());
            pst.setString(4, kh.getEmail());
            pst.setInt(5, kh.getMaKhachHang());
            
            int result = pst.executeUpdate();
            
            return result > 0;
            
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }      
    }
    
    public boolean deleteKhachHang(int khachHangID) {
        String checkSql = "SELECT TongNo FROM KHACHHANG WHERE MaKhachHang = ?";
        
        String deleteSql = "DELETE FROM KHACHHANG WHERE MaKhachHang = ?";
        
        try (
                Connection con = JDBCUtil.getConnection();
                PreparedStatement checkStmt = con.prepareStatement(checkSql);
                PreparedStatement deleteStmt = con.prepareStatement(deleteSql)) {

            checkStmt.setInt(1, khachHangID);
            var rs = checkStmt.executeQuery();

            if (rs.next()) {
                int tongNo = rs.getInt("TongNo");
                 
                if (tongNo == 0) {
                    deleteStmt.setInt(1, khachHangID);
                    int result = deleteStmt.executeUpdate();
                    return result > 0; 
                } else {
                    Logger.getLogger(KhachHangDAO.class.getName()).log(Level.WARNING,
                        "Không thể xóa khách hàng {0}: Tổng nợ hiện tại là {1}", new Object[]{khachHangID, tongNo});
                    return false; // Không xóa được vì còn nợ
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    } 
    
    public ArrayList<KhachHang> selectAllKhachHang() {
        String sql = "SELECT * FROM KHACHHANG";
        ArrayList<KhachHang> ketQua = new ArrayList<>();
        
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int maKhachHang = rs.getInt("MaKhachHang");
                String tenKhachHang = rs.getString("TenKhachHang");
                String diaChi = rs.getString("DiaChi");           
                String soDienThoai = rs.getString("SoDienThoai");
                String email = rs.getString("Email");
                int tongNo = rs.getInt("TongNo");
                
                KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, diaChi, soDienThoai, email, tongNo);
                ketQua.add(kh);
            }
        } catch (SQLException ex1) {
            ex1.getStackTrace();
        }
        
        return ketQua;
    }
    
    public KhachHang selectKhachHangById(int khachHang_id) {
        String sql = "SELECT * FROM KHACHHANG WHERE MaKhachHang = ?";
        
        try (Connection con = JDBCUtil.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {
            
        pst.setInt(1, khachHang_id);

        // Thực thi truy vấn
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                int maKhachHang = rs.getInt("MaKhachHang");
                String tenKhachHang = rs.getString("TenKhachHang");
                String diaChi = rs.getString("DiaChi");
                String soDienThoai = rs.getString("SoDienThoai");
                String email = rs.getString("Email");
                int tongNo = rs.getInt("TongNo");
                
                
                return new KhachHang(maKhachHang, tenKhachHang, diaChi, soDienThoai, email, tongNo);
            }
        }

        } catch (SQLException e) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }
   
    
    
}
