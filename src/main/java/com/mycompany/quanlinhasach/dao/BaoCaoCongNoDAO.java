/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.dao;

import com.mycompany.quanlinhasach.database.JDBCUtil;
import com.mycompany.quanlinhasach.model.BaoCaoCongNo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HOANG
 */

    
    public class BaoCaoCongNoDAO {
        public List<BaoCaoCongNo> getAllBaoCaoCongNo() {
        List<BaoCaoCongNo> baoCaoCongNoList = new ArrayList<>();
        String query = "SELECT b.MaBaoCaoCongNo, k.TenKhachHang, b.NoDau, b.NoCuoi, b.PhatSinh, b.Thang, b.Nam " +
                       "FROM BAOCAOCONGNO b " +
                       "JOIN KHACHHANG k ON b.MaKhachHang = k.MaKhachHang";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int maBaoCaoCongNo = rs.getInt("MaBaoCaoCongNo");
                    String tenKhachHang = rs.getString("TenKhachHang");
                    int noDau = rs.getInt("NoDau");
                    int noCuoi = rs.getInt("NoCuoi");
                    int phatSinh = rs.getInt("PhatSinh");
                    int thang = rs.getInt("Thang");
                    int nam = rs.getInt("Nam");
    
                    BaoCaoCongNo baoCao = new BaoCaoCongNo(maBaoCaoCongNo, tenKhachHang, noDau, noCuoi, phatSinh, thang, nam);
                    baoCaoCongNoList.add(baoCao);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    
            return baoCaoCongNoList;
        }

    
        public List<BaoCaoCongNo> getBaoCaoCongNoByMonthYearAndName(Integer month, Integer year, String name) {
            List<BaoCaoCongNo> baoCaoCongNoList = new ArrayList<>();
            StringBuilder query = new StringBuilder("SELECT b.MaBaoCaoCongNo, k.TenKhachHang, b.NoDau, b.NoCuoi, b.PhatSinh, b.Thang, b.Nam " +
                           "FROM BAOCAOCONGNO b " +
                           "JOIN KHACHHANG k ON b.MaKhachHang = k.MaKhachHang WHERE 1=1");
    
            if (month != null) {
                query.append(" AND b.Thang = ?");
            }
            if (year != null) {
                query.append(" AND b.Nam = ?");
            }
            if (name != null && !name.isEmpty()) {
                query.append(" AND k.TenKhachHang LIKE ?");
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
                int maBaoCaoCongNo = rs.getInt("MaBaoCaoCongNo");
                String tenKhachHang = rs.getString("TenKhachHang");
                int noDau = rs.getInt("NoDau");
                int noCuoi = rs.getInt("NoCuoi");
                int phatSinh = rs.getInt("PhatSinh");
                int thang = rs.getInt("Thang");
                int nam = rs.getInt("Nam");

                BaoCaoCongNo baoCao = new BaoCaoCongNo(maBaoCaoCongNo, tenKhachHang, noDau, noCuoi, phatSinh, thang, nam);
                baoCaoCongNoList.add(baoCao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return baoCaoCongNoList;
    }

    public List<Integer> getDistinctMonths() {
        List<Integer> months = new ArrayList<>();
        String query = "SELECT DISTINCT Thang FROM BAOCAOCONGNO ORDER BY Thang";

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
        String query = "SELECT DISTINCT Nam FROM BAOCAOCONGNO ORDER BY Nam";

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


