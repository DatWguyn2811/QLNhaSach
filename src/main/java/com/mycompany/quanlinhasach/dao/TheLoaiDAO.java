/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.dao;

import com.mycompany.quanlinhasach.database.JDBCUtil;
import com.mycompany.quanlinhasach.model.TheLoai;
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
public class TheLoaiDAO {
    
    public static TheLoaiDAO getInstance() {
        return new TheLoaiDAO();
    }
    
    public boolean insertTheLoai(TheLoai tl) {
        String sql = "INSERT INTO `THELOAI` (`TenTheLoai`) VALUES (?)";
        
        try (
                Connection con = JDBCUtil.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, tl.getTenTheLoai());

            int result = pst.executeUpdate();

            return result > 0;

        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean updateTheLoai(TheLoai tl) {
        String sql = "UPDATE `THELOAI` SET "
                + "`TenTheLoai` = ?"
                + "WHERE `MaTheLoai` = ?";
        
        try (
                Connection con = JDBCUtil.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, tl.getTenTheLoai());
            pst.setInt(2, tl.getMaTheLoai());

            int result = pst.executeUpdate();

            return result > 0;

        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }
    
    public boolean deleteTheLoai(int theLoaiID) {
        // Kiểm tra xem số lượng sách của thể loại có bằng 0 hay không
        String checkSql = "SELECT COALESCE(SUM(SACH.SoLuong), 0) AS TotalBooks"
                            + " FROM `DAUSACH`" 
                            + " JOIN SACH ON SACH.MaDauSach = DAUSACH.MaDauSach" 
                            + " WHERE DAUSACH.MaTheLoai = ?";

        String deleteSql = "DELETE FROM THELOAI WHERE MaTheLoai = ?";

        try (
                Connection con = JDBCUtil.getConnection();
                PreparedStatement checkStmt = con.prepareStatement(checkSql);
                PreparedStatement deleteStmt = con.prepareStatement(deleteSql)) {

            checkStmt.setInt(1, theLoaiID);
            var rs = checkStmt.executeQuery();

            if (rs.next()) {
                int totalBooks = rs.getInt("TotalBooks");

                if (totalBooks == 0) {
                    deleteStmt.setInt(1, theLoaiID);
                    int result = deleteStmt.executeUpdate();
                    return result > 0; 
                } else {
                    return false; 
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; 
    }

    public ArrayList<TheLoai> selectAllTheLoai() {
        ArrayList<TheLoai> ketQua = new ArrayList<>();

        try {
            Connection con = JDBCUtil.getConnection();

            Statement st = con.createStatement();

            String sql = "SELECT THELOAI.MaTheLoai, THELOAI.TenTheLoai, " +
                         "COALESCE(SUM(SACH.SoLuong), 0) AS SoLuong " +
                         "FROM THELOAI " +
                         "LEFT JOIN DAUSACH ON DAUSACH.MaTheLoai = THELOAI.MaTheLoai " +
                         "LEFT JOIN SACH ON SACH.MaDauSach = DAUSACH.MaDauSach " +
                         "GROUP BY THELOAI.MaTheLoai, THELOAI.TenTheLoai";

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int maTheLoai = rs.getInt("MaTheLoai");
                String tenTheLoai = rs.getString("TenTheLoai");
                int soLuong = rs.getInt("SoLuong");

                TheLoai tl = new TheLoai(maTheLoai, tenTheLoai, soLuong);

                ketQua.add(tl);
            }
            
            JDBCUtil.closeConnection(con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ketQua;
    }
    
    public TheLoai selectTheLoaiById (int theLoaiID) {
        String sql = "SELECT * FROM THELOAI WHERE MaTheLoai = ?";
        
        try ( Connection con = JDBCUtil.getConnection();
               PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, theLoaiID);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                String tenTheLoai = rs.getString("TenTheLoai");
                
                return new TheLoai(tenTheLoai);
            }        
        } catch (SQLException ex) { 
            Logger.getLogger(TheLoaiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
