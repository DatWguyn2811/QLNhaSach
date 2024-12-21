/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author NGUYEN MY NGAN
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DauSach;

public class DauSachDAO {
    private Connection connection;

    public DauSachDAO(Connection connection) {
        this.connection = connection;
    }

    // Lấy tất cả đầu sách
    public List<DauSach> getAllDauSach() throws SQLException {
        List<DauSach> list = new ArrayList<>();
        String sql = "SELECT * FROM DAUSACH";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            DauSach ds = new DauSach(
                rs.getInt("MaDauSach"),
                rs.getString("TenDauSach"),
                rs.getInt("MaTheLoai"),
                rs.getInt("MaTacGia")
            );
            list.add(ds);
        }
        return list;
    }

    // Thêm đầu sách mới
    public void insertDauSach(DauSach ds) throws SQLException {
        String sql = "INSERT INTO DAUSACH (MaDauSach, TenDauSach, MaTheLoai, MaTacGia) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, ds.getMaDauSach());
        stmt.setString(2, ds.getTenDauSach());
        stmt.setInt(3, ds.getMaTheLoai());
        stmt.setInt(4, ds.getMaTacGia());
        stmt.executeUpdate();
    }

    // Sửa đầu sách
    public void updateDauSach(DauSach ds) throws SQLException {
        String sql = "UPDATE DAUSACH SET TenDauSach = ?, MaTheLoai = ?, MaTacGia = ? WHERE MaDauSach = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, ds.getTenDauSach());
        stmt.setInt(2, ds.getMaTheLoai());
        stmt.setInt(3, ds.getMaTacGia());
        stmt.setInt(4, ds.getMaDauSach());
        stmt.executeUpdate();
    }

    // Xóa đầu sách
    public void deleteDauSach(int maDauSach) throws SQLException {
        String sql = "DELETE FROM DAUSACH WHERE MaDauSach = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, maDauSach);
        stmt.executeUpdate();
    }
}

