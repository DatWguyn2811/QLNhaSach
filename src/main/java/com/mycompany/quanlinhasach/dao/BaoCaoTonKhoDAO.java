package com.mycompany.quanlinhasach.dao;

import com.mycompany.quanlinhasach.database.JDBCUtil;
import com.mycompany.quanlinhasach.model.BaoCaoTonKho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaoCaoTonKhoDAO {

    public List<BaoCaoTonKho> getAllBaoCaoTonKho() {
        List<BaoCaoTonKho> baoCaoTonKhoList = new ArrayList<>();
        String query = "SELECT b.MaBaoCaoTonKho, b.MaSach, ds.TenDauSach AS TenSach, b.TonDau, b.TonCuoi, b.PhatSinh, b.Thang, b.Nam " +
                       "FROM BAOCAOTON b " +
                       "JOIN SACH s ON b.MaSach = s.MaSach " +
                       "JOIN DAUSACH ds ON s.MaDauSach = ds.MaDauSach";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int maBaoCaoTonKho = rs.getInt("MaBaoCaoTonKho");
                int maSach = rs.getInt("MaSach");
                String tenSach = rs.getString("TenSach");
                int tonDau = rs.getInt("TonDau");
                int tonCuoi = rs.getInt("TonCuoi");
                int phatSinh = rs.getInt("PhatSinh");
                int thang = rs.getInt("Thang");
                int nam = rs.getInt("Nam");

                BaoCaoTonKho baoCao = new BaoCaoTonKho(maBaoCaoTonKho, maSach, tenSach, tonDau, tonCuoi, phatSinh, thang, nam);
                baoCaoTonKhoList.add(baoCao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return baoCaoTonKhoList;
    }

    public List<BaoCaoTonKho> getBaoCaoTonKhoByMonthYearAndName(Integer month, Integer year, String name) {
        List<BaoCaoTonKho> baoCaoTonKhoList = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT b.MaBaoCaoTonKho, b.MaSach, ds.TenDauSach AS TenSach, b.TonDau, b.TonCuoi, b.PhatSinh, b.Thang, b.Nam " +
                       "FROM BAOCAOTON b " +
                       "JOIN SACH s ON b.MaSach = s.MaSach " +
                       "JOIN DAUSACH ds ON s.MaDauSach = ds.MaDauSach WHERE 1=1");

        if (month != null) {
            query.append(" AND b.Thang = ?");
        }
        if (year != null) {
            query.append(" AND b.Nam = ?");
        }
        if (name != null && !name.isEmpty()) {
            query.append(" AND ds.TenDauSach LIKE ?");
        }

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            int paramIndex = 1;
            if (month != null) {
                stmt.setInt(paramIndex++, month);
            }
            if (year != null) {
                stmt.setInt(paramIndex++, year);
            }
            if (name != null && !name.isEmpty()) {
                stmt.setString(paramIndex++, "%" + name + "%");
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int maBaoCaoTonKho = rs.getInt("MaBaoCaoTonKho");
                int maSach = rs.getInt("MaSach");
                String tenSach = rs.getString("TenSach");
                int tonDau = rs.getInt("TonDau");
                int tonCuoi = rs.getInt("TonCuoi");
                int phatSinh = rs.getInt("PhatSinh");
                int thang = rs.getInt("Thang");
                int nam = rs.getInt("Nam");

                BaoCaoTonKho baoCao = new BaoCaoTonKho(maBaoCaoTonKho, maSach, tenSach, tonDau, tonCuoi, phatSinh, thang, nam);
                baoCaoTonKhoList.add(baoCao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return baoCaoTonKhoList;
    }

    public List<Integer> getDistinctMonths() {
        List<Integer> months = new ArrayList<>();
        String query = "SELECT DISTINCT Thang FROM BAOCAOTON ORDER BY Thang";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                months.add(rs.getInt("Thang"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return months;
    }

    public List<Integer> getDistinctYears() {
        List<Integer> years = new ArrayList<>();
        String query = "SELECT DISTINCT Nam FROM BAOCAOTON ORDER BY Nam";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                years.add(rs.getInt("Nam"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return years;
    }
}