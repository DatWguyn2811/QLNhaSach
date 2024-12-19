/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.dao;

import com.mycompany.quanlinhasach.database.JDBCUtil;
import com.mycompany.quanlinhasach.model.NhanVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author TienDat
 */
public class NhanVienDAO implements DAOInterface<NhanVien> {
    public static NhanVienDAO getInstance() {
        return new NhanVienDAO();
    }

    @Override
    public boolean insert(NhanVien t) {
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "INSERT INTO `NHANVIEN`(`TenNhanVien`, `GioiTinh`, `NgaySinh`, `NgayVaoLam`, `SoDienThoai`, `Email`, `Luong`, `TrangThai`, `TenDangNhap`, `MatKhau`, `VaiTro`, `MaQuyen`)"
             + " VALUES ('"+t.getTenNhanVien()+"', '"+t.getGioiTinh()+"', '"+t.getNgaySinh()+"', '"+t.getNgayVaoLam()+"', '"+t.getSoDienThoai()+"', '"+t.getEmail()+"', "+t.getLuong()+", "+t.getTrangThai()+", '"+t.getTenDangNhap()+"', '"+t.getMatKhau()+"', '"+t.getVaiTro()+"', "+t.getMaQuyen()+")";

            int result = st.executeUpdate(sql);
            
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }

    @Override
    public int update(NhanVien t) {
       try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "UPDATE `NHANVIEN` SET " 
                        + "`TenNhanVien` = '" + t.getTenNhanVien() + "', "
                        + "`GioiTinh` = '" + t.getGioiTinh() + "', "
                        + "`NgaySinh` = '" + t.getNgaySinh() + "', "
                        + "`NgayVaoLam` = '" + t.getNgayVaoLam() + "', "
                        + "`SoDienThoai` = '" + t.getSoDienThoai() + "', "
                        + "`Email` = '" + t.getEmail() + "', "
                        + "`Luong` = " + t.getLuong() + ", "
                        + "`TrangThai` = " + t.getTrangThai() + ", "
                        + "`TenDangNhap` = '" + t.getTenDangNhap() + "', "
                        + "`MatKhau` = '" + t.getMatKhau() + "', "
                        + "`VaiTro` = '" + t.getVaiTro() + "', "
                        + "`MaQuyen` = " + t.getMaQuyen() + " "
                        + "WHERE `MaNhanVien` = " + t.getMaNhanVien(); 
            int result = st.executeUpdate(sql);
            
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 1;
    }

    @Override
    public boolean delete(NhanVien t) {
        try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "DELETE FROM `NHANVIEN` WHERE `MaNhanVien` = " + t.getMaNhanVien(); 
            int result = st.executeUpdate(sql);
            
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public ArrayList<NhanVien> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NhanVien selectById(NhanVien t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<NhanVien> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String String(int trangThai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
