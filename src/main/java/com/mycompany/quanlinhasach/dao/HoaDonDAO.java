/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.dao;

import com.mycompany.quanlinhasach.database.JDBCUtil;
import com.mycompany.quanlinhasach.model.HoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class HoaDonDAO implements DAOInterface<HoaDon> {
    // Lưu mã hóa đơn vừa được thêm
    private int lastInsertedId = -1;
    @Override
    public boolean insert(HoaDon t) {
        String sql = "INSERT INTO hoadon (MaKhachHang, NgayLap, TongTien, SoTienThu, ConLai, MaNhanVien) VALUES (?, ?, ?, ?, ?, ?)";

        Connection con = null;  // Đảm bảo Connection con không bị đóng quá sớm

        try {
            con = JDBCUtil.getConnection();  // Lấy kết nối
            // Tắt auto commit để bắt đầu giao dịch
            con.setAutoCommit(false);

            try (PreparedStatement pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                // Thiết lập các tham số cho câu lệnh INSERT
                pst.setInt(1, t.getMaKhachHang());
                pst.setString(2, t.getNgayLap());
                pst.setInt(3, t.getTongTien());
                pst.setInt(4, t.getSoTienThu());
                pst.setInt(5, t.getConLai());
                pst.setInt(6, t.getMaNhanVien());

                // Thực thi câu lệnh INSERT
                int result = pst.executeUpdate();

                // Nếu câu lệnh INSERT thành công, commit giao dịch
                if (result > 0) {
                    con.commit();  // Commit giao dịch

                    // Lấy mã hóa đơn vừa được thêm vào (auto-generated key)
                    try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            lastInsertedId = generatedKeys.getInt(1);  // Lưu lại maHoaDon
                            System.out.println("Mã hóa đơn vừa thêm: " + lastInsertedId); // In ra mã hóa đơn
                        }
                    }
                    return true;
                } else {
                    con.rollback();  // Rollback nếu có lỗi
                    return false;
                }
            } catch (SQLException ex) {
                con.rollback(); // Rollback nếu xảy ra lỗi trong câu lệnh SQL
                ex.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                // Đảm bảo bật lại auto commit sau khi giao dịch kết thúc
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close(); // Đảm bảo đóng kết nối sau khi sử dụng
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Phương thức lấy mã hóa đơn vừa thêm
    public int getLastInsertedId() {
        return lastInsertedId;
    }

    @Override
    public int update(HoaDon t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(HoaDon t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<HoaDon> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDon selectById(HoaDon t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<HoaDon> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
