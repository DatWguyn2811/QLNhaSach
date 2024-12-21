/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.NhaXuatBan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhaXuatBanDAO {
    private Connection connection;

    public NhaXuatBanDAO(Connection connection) {
        this.connection = connection;
    }

    public List<NhaXuatBan> getAllNhaXuatBan() {
        List<NhaXuatBan> nhaXuatBans = new ArrayList<>();
        String query = "SELECT * FROM NXB";  // Sửa query cho đúng với cấu trúc DB của bạn
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int maNhaXuatBan = resultSet.getInt("MaNhaXuatBan");
                String tenNhaXuatBan = resultSet.getString("TenNhaXuatBan");
                nhaXuatBans.add(new NhaXuatBan(maNhaXuatBan, tenNhaXuatBan));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhaXuatBans;
    }
}


//public class NhaXuatBanDAO {
//
//    private Connection connection;
//
//    public NhaXuatBanDAO(Connection connection) {
//        this.connection = connection;
//    }
//
//    // Thêm một NXB mới
//    public boolean addNhaXuatBan(NhaXuatBan nhaXuatBan) {
//        String query = "INSERT INTO NXB (MaNhaXuatBan, TenNhaXuatBan) VALUES (?, ?)";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, nhaXuatBan.getMaNhaXuatBan());
//            preparedStatement.setString(2, nhaXuatBan.getTenNhaXuatBan());
//            return preparedStatement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // Lấy tất cả các NXB
//    public List<String> getAllNhaXuatBan() {
//        List<String> nhaXuatBans = new ArrayList<>();
//        String query = "SELECT * FROM NXB";
//        try (Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(query)) {
//            while (resultSet.next()) {
//                String tenNhaXuatBan = resultSet.getString("TenNhaXuatBan");
//                nhaXuatBans.add(tenNhaXuatBan);  // Chỉ thêm tên nhà xuất bản vào danh sách
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return nhaXuatBans;  // Trả về danh sách tên nhà xuất bản
//    }
//
//
//    // Lấy thông tin NXB theo ID
//    public NhaXuatBan getNhaXuatBanById(int id) {
//        String query = "SELECT * FROM NXB WHERE MaNhaXuatBan = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, id);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    int maNhaXuatBan = resultSet.getInt("MaNhaXuatBan");
//                    String tenNhaXuatBan = resultSet.getString("TenNhaXuatBan");
//                    return new NhaXuatBan(maNhaXuatBan, tenNhaXuatBan);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // Cập nhật thông tin NXB
//    public boolean updateNhaXuatBan(NhaXuatBan nhaXuatBan) {
//        String query = "UPDATE NXB SET TenNhaXuatBan = ? WHERE MaNhaXuatBan = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setString(1, nhaXuatBan.getTenNhaXuatBan());
//            preparedStatement.setInt(2, nhaXuatBan.getMaNhaXuatBan());
//            return preparedStatement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // Xóa NXB theo ID
//    public boolean deleteNhaXuatBan(int id) {
//        String query = "DELETE FROM NXB WHERE MaNhaXuatBan = ?";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, id);
//            return preparedStatement.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
