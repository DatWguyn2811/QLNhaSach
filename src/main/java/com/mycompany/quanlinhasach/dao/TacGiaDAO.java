/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.dao;

import com.mycompany.quanlinhasach.database.JDBCUtil;
import com.mycompany.quanlinhasach.model.TacGia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class TacGiaDAO {
    public ArrayList<TacGia> selectAll() {
        ArrayList<TacGia> list = new ArrayList<>();
        String sql = "SELECT MaTacGia, TenTacGia FROM TacGia";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TacGia tacGia = new TacGia(
                    rs.getInt("MaTacGia"),       // Lấy mã tác giả
                    rs.getString("TenTacGia")   // Lấy tên tác giả
                );
                list.add(tacGia); // Thêm vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

