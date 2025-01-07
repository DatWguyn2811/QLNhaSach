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
import com.mycompany.quanlinhasach.model.DauSachModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DauSachDAO implements DAOInterface<DauSachModel> {
    public static DauSachDAO getInstance() {
        return new DauSachDAO();
    }
    public static int layMaDauSach(String tenDauSach) throws SQLException {
        int maDauSach = -1;

        try (Connection conn = JDBCUtil.getConnection()) {
            String query = "SELECT MaDauSach FROM DauSach WHERE TenDauSach = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, tenDauSach);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                maDauSach = rs.getInt("MaDauSach");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maDauSach;
    }
    private int getMaFromDatabase(Connection con, String sql, String name) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // Trả về MaTheLoai hoặc MaTacGia từ cột đầu tiên
            }
            return -1; // Trả về -1 nếu không tìm thấy
        }
    }

    public boolean insert(ArrayList<DauSachModel> t) throws SQLException {
        // Kết nối cơ sở dữ liệu
        Connection con = null;
        PreparedStatement stmt = null;
        String sqlInsert = "INSERT INTO DAUSACH (TenDauSach, MaTheLoai, MaTacGia) VALUES (?, ?, ?)";
        String sqlGetMatheLoai = "SELECT MaTheLoai FROM THELOAI WHERE TenTheLoai = ?";
        String sqlGetMaTacGia = "SELECT MaTacGia FROM TACGIA WHERE TenTacGia = ?";

        try {
            con = JDBCUtil.getConnection();
            con.setAutoCommit(false); // Bắt đầu transaction

            stmt = con.prepareStatement(sqlInsert);

            for (DauSachModel sach : t) {
                // Lấy MaDauSach dựa trên TenDauSach
                

                // Lấy MaNhaXuatBan dựa trên TenNXB
                // Lấy MaTheLoai dựa trên TenTheLoai
                int maTheLoai = getMaFromDatabase(con, sqlGetMatheLoai, sach.getTenTheLoai());
                if (maTheLoai == -1) {
                    throw new SQLException("Không tìm thấy MaTheLoai cho thể loại: " + sach.getTenTheLoai());
                }

                // Lấy MaTacGia dựa trên TenTacGia
                int maTacGia = getMaFromDatabase(con, sqlGetMaTacGia, sach.getTenTacGia());
                if (maTacGia == -1) {
                    throw new SQLException("Không tìm thấy MaTacGia cho tác giả: " + sach.getTenTacGia());
                }





                // Gán giá trị vào câu lệnh SQL
                stmt.setString(1, sach.getTenDauSach());                // MaDauSach
                stmt.setInt(2, maTheLoai);         // DonGia
                stmt.setInt(3, maTacGia);        // SoLuong


                stmt.addBatch(); // Thêm vào batch
            }

            // Thực thi batch
            stmt.executeBatch();
            con.commit(); // Commit transaction nếu không có lỗi

            return true; // Trả về true nếu thành công
        } catch (SQLException ex) {
            if (con != null) {
                try {
                    con.rollback(); // Rollback nếu có lỗi
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            ex.printStackTrace();
            return false; // Trả về false nếu có lỗi
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                JDBCUtil.closeConnection(con);
            }
        }
    }

    @Override
    public boolean insert(DauSachModel t) {
        String sql = "INSERT INTO DAUSACH (TenDauSach, MaTheLoai, MaTacGia) VALUES (?, " +
                     "(SELECT MaTheLoai FROM THELOAI WHERE TenTheLoai = ?), " +
                     "(SELECT MaTacGia FROM TACGIA WHERE TenTacGia = ?))";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getTenDauSach());
            stmt.setString(2, t.getTenTheLoai());
            stmt.setString(3, t.getTenTacGia());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public int update(DauSachModel dauSach) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sqlUpdate = "UPDATE DAUSACH SET TenDauSach = ?, MaTheLoai = ?, MaTacGia = ? WHERE MaDauSach = ?";
        String sqlGetMaDauSach = "SELECT MaDauSach FROM DauSach WHERE TenDauSach = ?";
        String sqlGetMaTheLoai = "SELECT MaTheLoai FROM THELOAI WHERE TenTheLoai = ?";
        String sqlGetMaTacGia = "SELECT MaTacGia FROM TACGIA WHERE TenTacGia = ?";

        try {
            con = JDBCUtil.getConnection();
            
            

            
            // Lấy MaTheLoai từ TenTheLoai
            PreparedStatement stmtMaTheLoai = con.prepareStatement(sqlGetMaTheLoai);
            stmtMaTheLoai.setString(1, dauSach.getTenTheLoai());
            rs = stmtMaTheLoai.executeQuery();
            int maTheLoai = -1;
            if (rs.next()) {
                maTheLoai = rs.getInt("MaTheLoai");
            }
            if (maTheLoai == -1) {
                throw new SQLException("Không tìm thấy MaTheLoai cho TenTheLoai: " + dauSach.getTenTheLoai());
            }

            // Lấy MaTacGia từ TenTacGia
            PreparedStatement stmtMaTacGia = con.prepareStatement(sqlGetMaTacGia);
            stmtMaTacGia.setString(1, dauSach.getTenTacGia());
            rs = stmtMaTacGia.executeQuery();
            int maTacGia = -1;
            if (rs.next()) {
                maTacGia = rs.getInt("MaTacGia");
            }
            if (maTacGia == -1) {
                throw new SQLException("Không tìm thấy MaTacGia cho TenTacGia: " + dauSach.getTenTacGia());
            }

            // Tạo câu lệnh SQL để cập nhật thông tin đầu sách
            stmt = con.prepareStatement(sqlUpdate);

            // Gán giá trị cho các tham số trong câu lệnh SQL
            stmt.setString(1, dauSach.getTenDauSach());
            stmt.setInt(2, maTheLoai); // Gán MaTheLoai lấy được
            stmt.setInt(3, maTacGia);  // Gán MaTacGia lấy được
            stmt.setInt(4, dauSach.getMaDauSach()); // Xác định đầu sách cần cập nhật bằng MaDauSach
            
            

            System.out.println("TenDauSach: " + dauSach.getTenDauSach());
            System.out.println("MaTheLoai: " + maTheLoai);
            System.out.println("MaTacGia: " + maTacGia);
            System.out.println("MaDauSach: " + dauSach.getMaDauSach());

            int rowsAffected = stmt.executeUpdate(); // Thực thi câu lệnh SQL và trả về số dòng bị ảnh hưởng

            if (rowsAffected > 0) {
                return rowsAffected; // Trả về số dòng bị ảnh hưởng
            } else {
                throw new SQLException("Không có bản ghi nào bị ảnh hưởng. Kiểm tra lại dữ liệu cần cập nhật.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0; // Trả về 0 nếu có lỗi xảy ra
        } finally {
            // Đảm bảo đóng kết nối và câu lệnh sau khi sử dụng
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
                if (con != null) JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }



    @Override
    public boolean delete(DauSachModel t) {
        String sql = "DELETE FROM DAUSACH WHERE MaDauSach = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, t.getMaDauSach());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean delete(int maDauSach) {
        Connection con = null;
        PreparedStatement stmtDelete = null;

        String sqlDelete = "DELETE FROM DAUSACH WHERE MaDauSach = ?";
        try {
            // Kết nối đến cơ sở dữ liệu
            con = JDBCUtil.getConnection();

            // Xóa bản ghi từ bảng DAUSACH
            stmtDelete = con.prepareStatement(sqlDelete);
            stmtDelete.setInt(1, maDauSach); // Sử dụng setInt vì maDauSach là kiểu int

            int rowsAffected = stmtDelete.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có bản ghi bị xóa
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        } finally {
            // Đóng các tài nguyên
            try {
                if (stmtDelete != null) stmtDelete.close();
                if (con != null) JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }



    @Override
    public ArrayList<DauSachModel> selectAll() {
        ArrayList<DauSachModel> list = new ArrayList<>();
        String sql = """
            SELECT 
                ds.MaDauSach, 
                ds.TenDauSach, 
                tl.TenTheLoai, 
                tg.TenTacGia
            FROM 
                DAUSACH ds
            JOIN 
                THELOAI tl ON ds.MaTheLoai = tl.MaTheLoai
            JOIN 
                TACGIA tg ON ds.MaTacGia = tg.MaTacGia
            """;

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DauSachModel ds = new DauSachModel(
                    rs.getInt("MaDauSach"),          // Lấy mã đầu sách
                    rs.getString("TenDauSach"),      // Lấy tên đầu sách
                    rs.getString("TenTheLoai"),      // Lấy tên thể loại
                    rs.getString("TenTacGia")        // Lấy tên tác giả
                );
                list.add(ds); // Thêm vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public DauSachModel selectById(DauSachModel t) {
        String sql = """
            SELECT ds.MaDauSach, ds.TenDauSach, tl.TenTheLoai, tg.TenTacGia
            FROM DAUSACH ds
            JOIN THELOAI tl ON ds.MaTheLoai = tl.MaTheLoai
            JOIN TACGIA tg ON ds.MaTacGia = tg.MaTacGia
            WHERE ds.MaDauSach = ?
        """;

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, t.getMaDauSach());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new DauSachModel(
                        rs.getInt("MaDauSach"),
                        rs.getString("TenDauSach"),
                        rs.getString("TenTheLoai"),
                        rs.getString("TenTacGia")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }


    @Override
    public ArrayList<DauSachModel> selectByCondition(String condition) {
        ArrayList<DauSachModel> list = new ArrayList<>();
        String sql = """
            SELECT ds.MaDauSach, ds.TenDauSach, tl.TenTheLoai, tg.TenTacGia
            FROM DAUSACH ds
            JOIN THELOAI tl ON ds.MaTheLoai = tl.MaTheLoai
            JOIN TACGIA tg ON ds.MaTacGia = tg.MaTacGia
            WHERE """ + condition;

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DauSachModel ds = new DauSachModel(
                    rs.getInt("MaDauSach"),
                    rs.getString("TenDauSach"),
                    rs.getString("TenTheLoai"),
                    rs.getString("TenTacGia")
                );
                list.add(ds); // Thêm đầu sách vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}


