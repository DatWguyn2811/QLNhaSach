/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.NhaXuatBanDAO;
import model.NhaXuatBan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import view.SanPham;



public class NhaXuatBanController {
    private SanPham view;
    private NhaXuatBanDAO dao;

    public NhaXuatBanController(SanPham view, NhaXuatBanDAO dao) {
        this.view = view;
        this.dao = dao;
    }

//    public void loadNhaXuatBanToComboBox() {
//        // Lấy danh sách nhà xuất bản từ DAO
//        List<NhaXuatBan> nhaXuatBans = dao.getAllNhaXuatBan();
//
//        // Đổ dữ liệu vào ComboBox của View
//        view.loadNhaXuatBanToComboBox(nhaXuatBans);
//    }

    public static void main(String[] args) {
        // Kết nối cơ sở dữ liệu
        String jdbcURL = "jdbc:mysql://localhost:3306/quanlynhasach";
        String jdbcUsername = "root";
        String jdbcPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
            SanPham view = new SanPham();
            NhaXuatBanDAO dao = new NhaXuatBanDAO(connection);

            // Tạo controller và tải dữ liệu vào ComboBox
            NhaXuatBanController controller = new NhaXuatBanController(view, dao);
//            controller.loadNhaXuatBanToComboBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//public class NhaXuatBanController {
//
//    public static void main(String[] args) {
//        String jdbcURL = "jdbc:mysql://localhost:3306/quanlynhasach";
//        String jdbcUsername = "root";
//        String jdbcPassword = "";
//
//        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
//            NhaXuatBanDAO dao = new NhaXuatBanDAO(connection);
//            SanPham view = new SanPham();
//
//            // Lấy danh sách tên nhà xuất bản từ DAO
//            List<String> nhaXuatBans = dao.getAllNhaXuatBan();
//            
//            // Kiểm tra xem có dữ liệu hay không
//            
//            // Đổ dữ liệu vào JComboBox
//            view.loadNhaXuatBanToComboBox(nhaXuatBans);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}


