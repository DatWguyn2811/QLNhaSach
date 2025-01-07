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
public class NhanVienDAO {
    public static NhanVienDAO getInstance() {
        return new NhanVienDAO();
    }

    public boolean insert(NhanVien t) {
    String sql = "INSERT INTO `NHANVIEN`(`TenNhanVien`, `GioiTinh`, `NgaySinh`, `NgayVaoLam`, `SoDienThoai`, `Email`, `Luong`, `TrangThai`, `TenDangNhap`, `MatKhau`, `VaiTro`, `MaQuyen`)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection con = JDBCUtil.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        // Set giá trị cho các tham số của PreparedStatement
        pst.setString(1, t.getTenNhanVien());
        pst.setString(2, t.getGioiTinh());
        pst.setString(3, t.getNgaySinh()); // Ngày sinh là String
        pst.setString(4, t.getNgayVaoLam()); // Ngày vào làm là String
        pst.setString(5, t.getSoDienThoai());
        pst.setString(6, t.getEmail());
        pst.setDouble(7, t.getLuong());
        pst.setInt(8, t.getTrangThai());
        pst.setString(9, t.getTenDangNhap());
        pst.setString(10, t.getMatKhau());
        pst.setString(11, t.getVaiTro());
        pst.setInt(12, t.getMaQuyen());

        // Thực thi truy vấn
        int result = pst.executeUpdate();

        // Kiểm tra kết quả
        return result > 0;

    } catch (SQLException ex) {
        Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}

    public boolean update(NhanVien t) {
        String sql1 = "UPDATE `NHANVIEN` SET "
                + "`SoDienThoai` = ?, "
                + "`Email` = ?, "
                + "`Luong` = ?, "
                + "`TrangThai` = ?, "
                + "`MatKhau` = ?, "
                + "`VaiTro` = ?, "
                + "`MaQuyen` = ? "
                + "WHERE `MaNhanVien` = ?";

        String sql2 = "UPDATE `NHANVIEN` SET "
                + "`SoDienThoai` = ?, "
                + "`Email` = ?, "
                + "`Luong` = ?, "
                + "`TrangThai` = ?, "
                + "`VaiTro` = ?, "
                + "`MaQuyen` = ? "
                + "WHERE `MaNhanVien` = ?";

        try (
                Connection con = JDBCUtil.getConnection();
                PreparedStatement pst = con.prepareStatement((t.getMatKhau() == null || t.getMatKhau().isEmpty()) ? sql2 : sql1)) {

            // Thiết lập giá trị cho các tham số
            pst.setString(1, t.getSoDienThoai());
            pst.setString(2, t.getEmail());
            pst.setDouble(3, t.getLuong());
            pst.setInt(4, t.getTrangThai());

            if (t.getMatKhau() == null || t.getMatKhau().isEmpty()) {
                // Trường hợp `sql2` (không cập nhật mật khẩu)
                pst.setString(5, t.getVaiTro());
                pst.setInt(6, t.getMaQuyen());
                pst.setInt(7, t.getMaNhanVien());
            } else {
                // Trường hợp `sql1` (cập nhật mật khẩu)
                pst.setString(5, t.getMatKhau());
                pst.setString(6, t.getVaiTro());
                pst.setInt(7, t.getMaQuyen());
                pst.setInt(8, t.getMaNhanVien());
            }

            // Thực thi truy vấn
            int result = pst.executeUpdate();

            // Kiểm tra kết quả
            return result > 0;

        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateTTTK(NhanVien t) {
        String sql1 = "UPDATE `NHANVIEN` SET "
                    + "`TenNhanVien` = ?, "
                    + "`GioiTinh` = ?, "
                    + "`NgaySinh` = ?, "
                    + "`SoDienThoai` = ?, "
                    + "`Email` = ?, "
                    + "`TrangThai` = ? "
                    + "WHERE `MaNhanVien` = ?";

        try (
            Connection con = JDBCUtil.getConnection();
            PreparedStatement pst = con.prepareStatement(sql1)
        ) {
            // Thiết lập giá trị cho các tham số
            pst.setString(1, t.getTenNhanVien());
            pst.setString(2, t.getGioiTinh());
            pst.setString(3, t.getNgaySinh());
            pst.setString(4, t.getSoDienThoai());
            pst.setString(5, t.getEmail());
            pst.setInt(6, t.getTrangThai());
            pst.setInt(7, t.getMaNhanVien());

            // Thực thi truy vấn
            int result = pst.executeUpdate();

            // Kiểm tra kết quả
            return result > 0;

        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    
    public boolean delete(int t) {
    String sql = "DELETE FROM `NHANVIEN` WHERE `MaNhanVien` = ?";

    try (Connection con = JDBCUtil.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        // Gán giá trị cho tham số `MaNhanVien`
        pst.setInt(1, t);

        // Thực thi truy vấn
        int result = pst.executeUpdate();

        // Kiểm tra kết quả
        return result > 0;

    } catch (SQLException ex) {
        Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}

    public ArrayList<NhanVien> selectAll() {
        ArrayList<NhanVien> ketQua = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            
            Statement st = con.createStatement();
            
            String sql = "select * from NHANVIEN";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                int maNhanVien = rs.getInt("MaNhanVien");
                String tenNhanVien = rs.getString("TenNhanVien");
                String gioiTinh = rs.getString("GioiTinh");
                String ngaySinh = rs.getString("NgaySinh");
                String email = rs.getString("Email");
                String soDienThoai = rs.getString("SoDienThoai");
                String ngayVaoLam = rs.getString("NgayVaoLam");
                int luong = rs.getInt("Luong");
                int trangThai = rs.getInt("TrangThai");
                String vaiTro = rs.getString("VaiTro");
                String tenDangNhap = rs.getString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                int maQuyen = rs.getInt("MaQuyen");
                
                NhanVien nv = new NhanVien(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, soDienThoai, email, luong, trangThai, ngayVaoLam, tenDangNhap, matKhau, vaiTro, maQuyen);
                ketQua.add(nv);
            }
            
            JDBCUtil.closeConnection(con);
        } catch(SQLException e) {
            e.getStackTrace();
        }
        return ketQua;
    }

    public NhanVien selectById(int nhanVienID) {
        String sql = "SELECT * FROM NHANVIEN WHERE MaNhanVien = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            // Thiết lập giá trị tham số
            pst.setInt(1, nhanVienID);

            // Thực thi truy vấn
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int maNhanVien = rs.getInt("MaNhanVien");
                    String tenNhanVien = rs.getString("TenNhanVien");
                    String gioiTinh = rs.getString("GioiTinh");
                    String ngaySinh = rs.getString("NgaySinh");
                    String email = rs.getString("Email");
                    String soDienThoai = rs.getString("SoDienThoai");
                    String ngayVaoLam = rs.getString("NgayVaoLam");
                    int luong = rs.getInt("Luong");
                    int trangThai = rs.getInt("TrangThai");
                    String chucVu = rs.getString("VaiTro");
                    String tenDangNhap = rs.getString("TenDangNhap");
                    String matKhau = rs.getString("MatKhau");
                    int maQuyen = rs.getInt("MaQuyen");

                    // Khởi tạo đối tượng NhanVien và trả về
                    return new NhanVien(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, soDienThoai, email, luong, trangThai, ngayVaoLam, tenDangNhap, matKhau, chucVu, maQuyen);
                }
            }

        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        // Trả về null nếu không tìm thấy nhân viên
        return null;
    }

    public ArrayList<NhanVien> selectByCondition(String searchText) {
        ArrayList<NhanVien> ketQua = new ArrayList<>();
        String sql = "SELECT * FROM NHANVIEN WHERE TenNhanVien LIKE ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            // Thiết lập giá trị tham số
            pst.setString(1, searchText + "%");

            // Thực thi truy vấn
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int maNhanVien = rs.getInt("MaNhanVien");
                    String tenNhanVien = rs.getString("TenNhanVien");
                    String gioiTinh = rs.getString("GioiTinh");
                    String ngaySinh = rs.getString("NgaySinh");
                    String email = rs.getString("Email");
                    String soDienThoai = rs.getString("SoDienThoai");
                    String ngayVaoLam = rs.getString("NgayVaoLam");
                    int luong = rs.getInt("Luong");
                    int trangThai = rs.getInt("TrangThai");
                    String chucVu = rs.getString("VaiTro");
                    String tenDangNhap = rs.getString("TenDangNhap");
                    String matKhau = rs.getString("MatKhau");
                    int maQuyen = rs.getInt("MaQuyen");

                    // Tạo đối tượng NhanVien và thêm vào danh sách kết quả
                    NhanVien nv = new NhanVien(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, soDienThoai, email, luong, trangThai, ngayVaoLam, tenDangNhap, matKhau, chucVu, maQuyen);
                    ketQua.add(nv);
                }
            }

        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return ketQua;
    }
    public boolean kiemTraTenDangNhap(String tenDangNhap) {
        String sql = "SELECT COUNT(*) FROM NHANVIEN WHERE TenDangNhap = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, tenDangNhap); // Gán giá trị cho tham số

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1); // Lấy giá trị COUNT(*)
                    return count > 0; // Trả về true nếu tồn tại
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false; // Mặc định trả về false nếu xảy ra lỗi
        
    }
    public NhanVien kiemTraDangNhap(String TenDangNhap, String MatKhau) {
        NhanVien nhanVien = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE TenDangNhap=? AND MatKhau=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, TenDangNhap);
            pst.setString(2, MatKhau);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int maNhanVien = rs.getInt("MaNhanVien");
                String tenNhanVien = rs.getString("TenNhanVien");
                String gioiTinh = rs.getString("GioiTinh");
                String ngaySinh = rs.getString("NgaySinh");
                String ngayVaoLam = rs.getString("NgayVaoLam");
                String soDienThoai = rs.getString("SoDienThoai");
                String email = rs.getString("Email");
                int luong = rs.getInt("Luong");
                int trangThai = rs.getInt("TrangThai");
                String tenDangNhap = rs.getString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                String vaiTro = rs.getString("VaiTro");
                int maQuyen = rs.getInt("MaQuyen");

                nhanVien = new NhanVien(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, soDienThoai, email, luong, trangThai, ngayVaoLam, tenDangNhap, matKhau, vaiTro, maQuyen);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanVien;
    }
    
    public NhanVien kiemTraDangNhap(String TenDangNhap) {
        NhanVien nhanVien = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE TenDangNhap=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, TenDangNhap);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int maNhanVien = rs.getInt("MaNhanVien");
                String tenNhanVien = rs.getString("TenNhanVien");
                String gioiTinh = rs.getString("GioiTinh");
                String ngaySinh = rs.getString("NgaySinh");
                String ngayVaoLam = rs.getString("NgayVaoLam");
                String soDienThoai = rs.getString("SoDienThoai");
                String email = rs.getString("Email");
                int luong = rs.getInt("Luong");
                int trangThai = rs.getInt("TrangThai");
                String tenDangNhap = rs.getString("TenDangNhap");
                String matKhau = rs.getString("MatKhau");
                String vaiTro = rs.getString("VaiTro");
                int maQuyen = rs.getInt("MaQuyen");

                nhanVien = new NhanVien(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, soDienThoai, email, luong, trangThai, ngayVaoLam, tenDangNhap, matKhau, vaiTro, maQuyen);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanVien;
    }
    public boolean kiemTraEmail(String email) {
        String sql = "SELECT COUNT(*) FROM NHANVIEN WHERE Email = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, email); // Gán giá trị cho tham số

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1); // Lấy giá trị COUNT(*)
                    return count > 0; // Trả về true nếu tồn tại
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false; // Mặc định trả về false nếu xảy ra lỗi
    }
    public int selectTotalNhanVien() {
        String sql = "SELECT COUNT(*) FROM NHANVIEN";
        int totalStaff = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                totalStaff = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalStaff;
    }

}
