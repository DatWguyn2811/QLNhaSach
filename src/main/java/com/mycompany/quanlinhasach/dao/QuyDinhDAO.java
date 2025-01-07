/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.dao;

import com.mycompany.quanlinhasach.database.JDBCUtil;
import com.mycompany.quanlinhasach.model.QuyDinh;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author TienDat
 */
public class QuyDinhDAO {
    
    public static QuyDinhDAO getInstance() {
        return new QuyDinhDAO();
    }
    
    public boolean updateQuyDinh(QuyDinh qd) {
        String sql = "UPDATE `THAMSO` SET"
                + " SoLuongNhapToiThieu = ? ,"
                + " SoLuongTonToiThieuDeNhap = ? ,"
                + " MonNoKhachHangToiDaDeBan = ? ,"
                + " SoLuongTonSauKhiBanToiThieu = ? ,"
                + " ApDungChinhSachThuNo = ? ,"
                + " HeSoGiaBan = ?"
                + " WHERE 1";
        
        try (
                Connection con = JDBCUtil.getConnection();
                PreparedStatement pst = con.prepareStatement(sql)) {
            
            // Gán giá trị cho tham số 
            pst.setInt(1, qd.getSoLuongNhapToiThieu());
            pst.setInt(2, qd.getSoLuongTonToiThieuDeNhap());
            pst.setInt(3, qd.getMonNoKhachHangToiDaDeBan());
            pst.setInt(4, qd.getSoLuongTonSauKhiBanToiThieu());
            pst.setInt(5, qd.getApDungChinhSachThuNo());
            pst.setDouble(6, qd.getHeSoGiaBan());
            
            int result = pst.executeUpdate();

            // Kiểm tra kết quả
            return result > 0;

        } catch (SQLException ex) {
            Logger.getLogger(QuyDinhDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public QuyDinh selectQuyDinh() {
        String sql = "SELECT * FROM THAMSO";
        QuyDinh quyDinh = new QuyDinh(150, 300, 1000000, 20, 1, 0.05);
        try (
            Connection con = JDBCUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()
        ) {
            if (rs.next()) {
                int soLuongNhapToiThieu = rs.getInt("SoLuongNhapToiThieu");
                int soLuongTonToiThieuDeNhap = rs.getInt("SoLuongTonToiThieuDeNhap");
                int monNoKhachHangToiDaDeBan = rs.getInt("MonNoKhachHangToiDaDeBan");
                int soLuongTonSauKhiBanToiThieu = rs.getInt("SoLuongTonSauKhiBanToiThieu");
                int apDungChinhSachThuNo = rs.getInt("ApDungChinhSachThuNo");
                double heSoGiaBan = rs.getDouble("HeSoGiaBan");
                
                quyDinh = new QuyDinh(soLuongNhapToiThieu, soLuongTonToiThieuDeNhap, monNoKhachHangToiDaDeBan, soLuongTonSauKhiBanToiThieu, apDungChinhSachThuNo, heSoGiaBan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuyDinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return quyDinh;  
    }
    
}
