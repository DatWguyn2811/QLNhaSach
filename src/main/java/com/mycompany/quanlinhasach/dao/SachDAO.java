/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.dao;

import com.mycompany.quanlinhasach.database.JDBCUtil;
import com.mycompany.quanlinhasach.model.Sach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class SachDAO  implements DAOInterface<Sach> {
    
    public static SachDAO getInstance() {
        return new SachDAO();
    }
    public boolean canSellBook(int maSach, int soLuongBan) {
        String sql = "SELECT SoLuong FROM SACH WHERE MaSach = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, maSach);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int soLuongTon = rs.getInt("SoLuong");
                    //return (soLuongTon - soLuongBan) >= 20;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SachDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public int getTongNoKH(int MaKH) {
        int TongNo = 0;
        try (Connection con = JDBCUtil.getConnection()) {
            // Câu lệnh SQL để lấy tên khách hàng từ số điện thoại
            String sql = "SELECT TongNo FROM khachhang WHERE MaKhachHang = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, MaKH); // Truyền tham số số điện thoại
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    TongNo = rs.getInt("TongNo"); // Lấy tên khách hàng
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SachDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TongNo;
    }
    // Phương thức tìm kiếm tên khách hàng theo số điện thoại
    public String getTenKhachHangBySDT(String soDienThoai) {
        String tenKhachHang = null;
        try (Connection con = JDBCUtil.getConnection()) {
            // Câu lệnh SQL để lấy tên khách hàng từ số điện thoại
            String sql = "SELECT TenKhachHang FROM khachhang WHERE SoDienThoai = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, soDienThoai); // Truyền tham số số điện thoại
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    tenKhachHang = rs.getString("TenKhachHang"); // Lấy tên khách hàng
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SachDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tenKhachHang;
    }
    
    public int getMaKhachHangBySDT(String soDienThoai) {
        String sql = "SELECT MaKhachHang FROM KHACHHANG WHERE SoDienThoai = ?";
        int maKhachHang = -1; // Nếu không tìm thấy, trả về -1

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            // Thiết lập tham số cho truy vấn
            pst.setString(1, soDienThoai);

            // Thực thi truy vấn
            try (ResultSet rs = pst.executeQuery()) {
                // Kiểm tra nếu có kết quả
                if (rs.next()) {
                    // Lấy giá trị MaKhachHang
                    maKhachHang = rs.getInt("MaKhachHang");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return maKhachHang; // Trả về MaKhachHang hoặc -1 nếu không tìm thấy
    }


    public int layMaSach(String tenDauSach, String nhaXuatBan, int namXuatBan) {
    int maSach = -1;
    try (Connection con = JDBCUtil.getConnection()) {
        // 1. Lấy MaDauSach từ bảng DAUSACH
        String sqlLayMaDauSach = "SELECT MaDauSach FROM DAUSACH WHERE TenDauSach = ?";
        try (PreparedStatement stmt1 = con.prepareStatement(sqlLayMaDauSach)) {
            stmt1.setString(1, tenDauSach);
            ResultSet rs1 = stmt1.executeQuery();
            if (rs1.next()) {
                int maDauSach = rs1.getInt("MaDauSach");

                // 2. Lấy MaSach từ bảng SACH
                String sqlLayMaSach = "SELECT MaSach FROM SACH WHERE MaDauSach = ? AND NamXuatBan = ? AND MaNhaXuatBan = ?";
                try (PreparedStatement stmt2 = con.prepareStatement(sqlLayMaSach)) {
                    stmt2.setInt(1, maDauSach);
                    stmt2.setInt(2, namXuatBan);

                    // Chuyển đổi nhà xuất bản thành mã (nếu cần)
                    int maNhaXuatBan = layMaNhaXuatBan(nhaXuatBan, con);
                    if (maNhaXuatBan == -1) {
                        throw new SQLException("Không tìm thấy nhà xuất bản: " + nhaXuatBan);
                    }
                    stmt2.setInt(3, maNhaXuatBan);

                    ResultSet rs2 = stmt2.executeQuery();
                    if (rs2.next()) {
                        maSach = rs2.getInt("MaSach");
                    }
                }
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(SachDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return maSach;
}

    private int layMaNhaXuatBan(String tenNhaXuatBan, Connection con) throws SQLException {
        String sql = "SELECT MaNhaXuatBan FROM NXB WHERE TenNhaXuatBan = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, tenNhaXuatBan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("MaNhaXuatBan");
            }
        }
        return -1;
    }

    public String layTenTacGia(String tenSach) throws SQLException {
    String tenTacGia = null;
    String sql = "SELECT TACGIA.TenTacGia FROM DAUSACH " +
                 "JOIN TACGIA ON DAUSACH.MaTacGia = TACGIA.MaTacGia " +
                 "WHERE DAUSACH.TenDauSach = ?";

    try (Connection con = JDBCUtil.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
        
        stmt.setString(1, tenSach); // Gán tên sách vào câu truy vấn
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            tenTacGia = rs.getString("TenTacGia"); // Lấy tên tác giả
        }
    }

    return tenTacGia;
}
    public int laySoLuongTon(int maSach) throws SQLException {
    int soLuongTon = 0;
    String sql = "SELECT SoLuong FROM SACH WHERE MaSach = ?";
    

    try (Connection con = JDBCUtil.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
        
        stmt.setInt(1, maSach); // Gán tên sách vào câu truy vấn
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            soLuongTon = rs.getInt("SoLuong"); // Lấy tên tác giả
        }
    }

    return soLuongTon;
}
public String layTenTheLoai(String tenSach) throws SQLException {
    String theLoai = null;
    String sql = "SELECT THELOAI.TenTheLoai FROM DAUSACH " +
                 "JOIN THELOAI ON DAUSACH.MaTheLoai = THELOAI.MaTheLoai " +
                 "WHERE DAUSACH.TenDauSach = ?";

    try (Connection con = JDBCUtil.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
        
        stmt.setString(1, tenSach); // Gán tên sách vào câu truy vấn
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            theLoai = rs.getString("TenTheLoai"); // Lấy tên thể loại
        }
    }

    return theLoai;
}
    public boolean kiemTraNhanVienTonTai(int manv) throws SQLException {
        String sql = "SELECT * FROM NHANVIEN WHERE MaNhanVien = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, manv); // Sử dụng setInt vì manv là kiểu int
            ResultSet rs = stmt.executeQuery();

            // Kiểm tra xem có kết quả nào không
            return rs.next();
        }
    }
public boolean kiemTraSachTonTai(String tenSach, String nhaXuatBan, int namXuatBan) throws SQLException {
    String sql = "SELECT COUNT(*) FROM SACH " +
                 "JOIN DAUSACH ON SACH.MaDauSach = DAUSACH.MaDauSach " +
                 "JOIN NXB ON SACH.MaNhaXuatBan = NXB.MaNhaXuatBan " +
                 "WHERE DAUSACH.TenDauSach = ? AND NXB.TenNhaXuatBan = ? AND SACH.NamXuatBan = ?";

    try (Connection con = JDBCUtil.getConnection();
         PreparedStatement stmt = con.prepareStatement(sql)) {
        
        // Set các tham số vào câu truy vấn
        stmt.setString(1, tenSach);
        stmt.setString(2, nhaXuatBan);
        stmt.setInt(3, namXuatBan);
        
        // Thực thi truy vấn và lấy kết quả
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1); // Lấy số lượng sách thỏa mãn điều kiện
            return count > 0; // Nếu có ít nhất 1 sách thỏa mãn điều kiện, trả về true (sách tồn tại)
        }
    }

    return false; // Nếu không có sách nào thỏa mãn điều kiện, trả về false
}


    public boolean insert(ArrayList<Sach> t) throws SQLException {
    // Kết nối cơ sở dữ liệu
    Connection con = null;
    PreparedStatement stmt = null;
    String sqlInsert = "INSERT INTO SACH (MaDauSach, SoLuong, DonGia, NamXuatBan, MaNhaXuatBan) VALUES (?, ?, ?, ?, ?)";
    String sqlGetMaDauSach = "SELECT MaDauSach FROM DauSach WHERE TenDauSach = ?";
    String sqlGetMaNhaXuatBan = "SELECT MaNhaXuatBan FROM NXB WHERE TenNhaXuatBan = ?";

    try {
        con = JDBCUtil.getConnection();
        con.setAutoCommit(false); // Bắt đầu transaction

        stmt = con.prepareStatement(sqlInsert);

        for (Sach sach : t) {
            // Lấy MaDauSach dựa trên TenDauSach
            int maDauSach = getMaFromDatabase(con, sqlGetMaDauSach, sach.getTenSach());
            if (maDauSach == -1) {
                throw new SQLException("Không tìm thấy MaDauSach cho tên: " + sach.getTenSach());
            }

            // Lấy MaNhaXuatBan dựa trên TenNXB
            int maNhaXuatBan = getMaFromDatabase(con, sqlGetMaNhaXuatBan, sach.getTenNXB());
            if (maNhaXuatBan == -1) {
                throw new SQLException("Không tìm thấy MaNhaXuatBan cho nhà xuất bản: " + sach.getTenNXB());
            }

            // Log thông tin câu lệnh SQL và dữ liệu chuẩn bị
            System.out.println("Đang thực thi câu lệnh SQL: " + sqlInsert);
            System.out.println("Dữ liệu chuẩn bị: MaDauSach = " + maDauSach + ", DonGia = " + sach.getDonGia() + ", SoLuong = " + sach.getSoLuong() +
                    ", NamXuatBan = " + sach.getNamXuatBan() + ", MaNhaXuatBan = " + maNhaXuatBan);

            // Gán giá trị vào câu lệnh SQL
            stmt.setInt(1, maDauSach);                // MaDauSach
            stmt.setInt(2, sach.getDonGia());         // DonGia
            stmt.setInt(3, sach.getSoLuong());        // SoLuong
            stmt.setInt(4, sach.getNamXuatBan());     // NamXuatBan
            stmt.setInt(5, maNhaXuatBan);             // MaNhaXuatBan

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

    /**
 * Phương thức hỗ trợ để lấy mã từ cơ sở dữ liệu dựa trên tên.
 */
private int getMaFromDatabase(Connection con, String sql, String ten) throws SQLException {
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        stmt = con.prepareStatement(sql);
        stmt.setString(1, ten);
        rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1); // Lấy giá trị cột đầu tiên
        }
        return -1; // Không tìm thấy
    } finally {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
    }
}

    @Override
public int update(Sach sach) {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sqlUpdate = "UPDATE SACH SET MaDauSach = ?, DonGia = ?, SoLuong = ?, NamXuatBan = ?, MaNhaXuatBan = ? WHERE MaSach = ?";
    String sqlGetMaDauSach = "SELECT MaDauSach FROM DauSach WHERE TenDauSach = ?";
    String sqlGetMaNhaXuatBan = "SELECT MaNhaXuatBan FROM NXB WHERE TenNhaXuatBan = ?";

    try {
        con = JDBCUtil.getConnection();
        
        // Lấy MaDauSach từ TenDauSach
        PreparedStatement stmtMaDauSach = con.prepareStatement(sqlGetMaDauSach);
        stmtMaDauSach.setString(1, sach.getTenSach());  // Gán TenDauSach vào câu truy vấn
        rs = stmtMaDauSach.executeQuery();
        int maDauSach = -1;
        if (rs.next()) {
            maDauSach = rs.getInt("MaDauSach");
        }
        if (maDauSach == -1) {
            throw new SQLException("Không tìm thấy MaDauSach cho TenDauSach: " + sach.getTenSach());
        }

        // Lấy MaNhaXuatBan từ TenNhaXuatBan
        PreparedStatement stmtMaNhaXuatBan = con.prepareStatement(sqlGetMaNhaXuatBan);
        stmtMaNhaXuatBan.setString(1, sach.getTenNXB());  // Gán TenNhaXuatBan vào câu truy vấn
        rs = stmtMaNhaXuatBan.executeQuery();
        int maNhaXuatBan = -1;
        if (rs.next()) {
            maNhaXuatBan = rs.getInt("MaNhaXuatBan");
        }
        if (maNhaXuatBan == -1) {
            throw new SQLException("Không tìm thấy MaNhaXuatBan cho TenNhaXuatBan: " + sach.getTenNXB());
        }

        // Tạo câu lệnh SQL để cập nhật thông tin sách
        stmt = con.prepareStatement(sqlUpdate);

        // Gán giá trị cho các tham số trong câu lệnh SQL
        stmt.setInt(1, maDauSach);  // Gán MaDauSach lấy được
        stmt.setInt(2, sach.getDonGia());
        stmt.setInt(3, sach.getSoLuong());
        stmt.setInt(4, sach.getNamXuatBan());
        stmt.setInt(5, maNhaXuatBan);  // Gán MaNhaXuatBan lấy được
        stmt.setInt(6, sach.getMaSach());  // Xác định sách cần cập nhật bằng MaSach
        
        System.out.println("MaDauSach: " + maDauSach);
        System.out.println("DonGia: " + sach.getDonGia());
        System.out.println("SoLuong: " + sach.getSoLuong());
        System.out.println("NamXuatBan: " + sach.getNamXuatBan());
        System.out.println("MaSach: " + sach.getMaSach());

        
        int rowsAffected = stmt.executeUpdate();  // Thực thi câu lệnh SQL và trả về số dòng bị ảnh hưởng

        if (rowsAffected > 0) {
            return rowsAffected;  // Trả về số dòng bị ảnh hưởng
        } else {
            throw new SQLException("Không có bản ghi nào bị ảnh hưởng. Kiểm tra lại dữ liệu cần cập nhật.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        return 0;  // Trả về 0 nếu có lỗi xảy ra, không có dòng nào bị ảnh hưởng
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




    
    public boolean delete(int t) {
        Connection con = null;
    PreparedStatement stmt = null;
    String sqlDelete = "DELETE FROM SACH WHERE MaSach = ?";

    try {
        con = JDBCUtil.getConnection();
        
        // Tạo câu lệnh SQL để xóa sách dựa trên MaSach
        stmt = con.prepareStatement(sqlDelete);
        stmt.setInt(1, t);  // Giả sử t.getMaSach() trả về mã sách để xóa

        int rowsAffected = stmt.executeUpdate();  // Thực thi câu lệnh SQL

        // Kiểm tra nếu có ít nhất một dòng bị ảnh hưởng (sách bị xóa thành công)
        return rowsAffected > 0;

    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;  // Nếu có lỗi xảy ra, trả về false
    } finally {
        // Đảm bảo đóng kết nối và câu lệnh sau khi sử dụng
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (con != null) {
            JDBCUtil.closeConnection(con);
        }
    } // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Sach> selectAll() {
    ArrayList<Sach> ketQua = new ArrayList<>();
    try {
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT SACH.MaSach, DAUSACH.TenDauSach, TACGIA.TenTacGia, NXB.TenNhaXuatBan, " +
                     "SACH.NamXuatBan, SACH.DonGia, SACH.SoLuong " +
                     "FROM SACH " +
                     "JOIN DAUSACH ON SACH.MaDauSach = DAUSACH.MaDauSach " +
                     "JOIN TACGIA ON DAUSACH.MaTacGia = TACGIA.MaTacGia " +
                     "JOIN NXB ON SACH.MaNhaXuatBan = NXB.MaNhaXuatBan";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            int maSach = rs.getInt("MaSach");
            String tenSach = rs.getString("TenDauSach");
            String tenTacGia = rs.getString("TenTacGia");
            String tenNXB = rs.getString("TenNhaXuatBan");
            int namXuatBan = rs.getInt("NamXuatBan");
            int donGia = rs.getInt("DonGia");
            int soLuong = rs.getInt("SoLuong");

            Sach sach = new Sach(maSach, tenSach, tenTacGia, tenNXB, namXuatBan, donGia, soLuong);
            ketQua.add(sach);
        }
        JDBCUtil.closeConnection(con);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ketQua;
}
    public boolean kiemTraDauSachTonTai(String tenDauSach, String tenTacGia, String tenTheLoai) throws SQLException {
        String sql = "SELECT COUNT(*) FROM DAUSACH " +
                     "JOIN THELOAI ON DAUSACH.MaTheLoai = THELOAI.MaTheLoai " +
                     "JOIN TACGIA ON DAUSACH.MaTacGia = TACGIA.MaTacGia " +
                     "WHERE DAUSACH.TenDauSach = ? AND THELOAI.TenTheLoai = ? AND TACGIA.TenTacGia = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            // Set các tham số vào câu truy vấn
            stmt.setString(1, tenDauSach);
            stmt.setString(2, tenTheLoai);
            stmt.setString(3, tenTacGia);

            // Thực thi truy vấn và lấy kết quả
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1); // Lấy số lượng sách thỏa mãn điều kiện
                    return count > 0; // Nếu có ít nhất 1 sách thỏa mãn điều kiện, trả về true
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Ném ngoại lệ để xử lý nếu cần
        }

        return false; // Nếu không có sách nào thỏa mãn điều kiện, trả về false
    }

    @Override
    public Sach selectById(Sach t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Sach> selectByCondition(String condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insert(Sach t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Sach t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
