/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.dao;

/**
 *
 * @author NGUYEN MY NGAN
 */
import com.mycompany.quanlinhasach.database.JDBCUtil;
import com.mycompany.quanlinhasach.model.Sach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class SachDAO {
    private Connection connection;

    public SachDAO(Connection connection) {
        this.connection = connection;
    }
    public String layTenTacGia(String tenSach) {
        String tacGia = null;
        try (Connection conn = JDBCUtil.getConnection()) {
            // Truy vấn lấy tên tác giả dựa vào tên đầu sách
            String sql = "SELECT t.TenTacGia " +
                         "FROM DAUSACH d " +
                         "JOIN TACGIA t ON d.MaTacGia = t.MaTacGia " +
                         "WHERE d.TenDauSach = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tenSach);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tacGia = rs.getString("TenTacGia"); // Lấy tên tác giả
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tacGia;
    }
    public String layTenTheLoai(String tenSach) {
        String tenTheLoai = null;
        try (Connection conn = JDBCUtil.getConnection()) {
            // SQL query để lấy tên thể loại
            String sql = "SELECT t.TenTheLoai " +
                         "FROM THELOAI t " +
                         "JOIN DAUSACH d ON t.MaTheLoai = d.MaTheLoai " +
                         "WHERE d.TenDauSach = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tenSach);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tenTheLoai = rs.getString("TenTheLoai");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tenTheLoai;
    }
    public int layMaNhaXuatBan(String nhaXuatBan) {
        int maNhaXuatBan = -1; // Giả sử giá trị -1 là không tìm thấy

        try (Connection conn = JDBCUtil.getConnection()) {
            String query = "SELECT MaNhaXuatBan FROM NXB WHERE TenNhaXuatBan = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nhaXuatBan);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                maNhaXuatBan = rs.getInt("MaNhaXuatBan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maNhaXuatBan;
    }




    // Kiểm tra xem sách có tồn tại trong cơ sở dữ liệu không
    public boolean kiemTraSachTonTai(String tenSach, String nhaXuatBan, int namXuatBan) {
        try (Connection conn = JDBCUtil.getConnection()) {
            String sql = "SELECT COUNT(*) FROM SACH s " +
                         "JOIN DAUSACH d ON s.MaDauSach = d.MaDauSach " +
                         "JOIN NXB n ON s.MaNhaXuatBan = n.MaNhaXuatBan " +
                         "WHERE d.TenDauSach = ? AND n.TenNhaXuatBan = ? AND s.NamXuatBan = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tenSach);
            stmt.setString(2, nhaXuatBan);
            stmt.setInt(3, namXuatBan);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void luuSachVaoCSDL(List<Sach> danhSachSach) throws SQLException {
    // Bỏ qua MaSach vì nó sẽ tự động tăng trong CSDL
        String sql = "INSERT INTO SACH (MaDauSach, DonGia, SoLuong, NamXuatBan, MaNhaXuatBan) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Sach sach : danhSachSach) {
                stmt.setInt(1, sach.getMaDauSach()); // MaDauSach
                stmt.setInt(2, sach.getDonGia()); // DonGia
                stmt.setInt(3, sach.getSoLuong()); // SoLuong
                stmt.setInt(4, sach.getNamXuatBan()); // NamXuatBan
                stmt.setInt(5, sach.getMaNhaXuatBan()); // MaNhaXuatBan
                stmt.addBatch(); // Thêm vào batch để thực hiện nhiều câu lệnh cùng lúc
            }

            stmt.executeBatch(); // Thực hiện tất cả các câu lệnh trong batch
        }
    }


}

