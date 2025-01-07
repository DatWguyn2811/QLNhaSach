/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quanlinhasach;

import com.mycompany.quanlinhasach.controller.SachController;
import com.mycompany.quanlinhasach.dao.SachDAO;
import com.mycompany.quanlinhasach.view.BanHang;
import com.mycompany.quanlinhasach.view.DangNhap;
import com.mycompany.quanlinhasach.view.DauSach;
import com.mycompany.quanlinhasach.view.DauSach;
import com.mycompany.quanlinhasach.view.SanPham;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author TienDat
 */
//public class Quanlinhasach {
//
//    public static void main(String[] args) throws SQLException {
//        setUIFont(new javax.swing.plaf.FontUIResource("Roboto", Font.PLAIN, 14));
//        
//        JPanel panel= new JPanel();
//        panel.setContentPane(new SanPham());
//        panel.setDefaultCloseOperation(JPanel.);
//        panel.setSize(1080, 723);
//        panel.setVisible(true);
//        
//        
//    }
//    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
//        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
//        while (keys.hasMoreElements()) {
//            Object key = keys.nextElement();
//            Object value = UIManager.get(key);
//            if (value instanceof javax.swing.plaf.FontUIResource) {
//                UIManager.put(key, f);
//            }
//        }
//    }
//    }

public class Quanlinhasach {

    public static void main(String[] args) throws SQLException {
        // Thiết lập font mặc định cho toàn bộ giao diện
        setUIFont(new javax.swing.plaf.FontUIResource("Roboto", Font.PLAIN, 14));

        // Tạo JFrame chính
        JFrame frame = new JFrame("Quản Lý Nhà Sách");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 730);

        // Tạo và hiển thị giao diện đăng nhập
        DangNhap dangNhapFrame = new DangNhap();
        frame.setContentPane(dangNhapFrame);

        // Hiển thị giao diện đăng nhập
        frame.setVisible(true);
    }

    // Phương thức thiết lập font mặc định cho toàn bộ UI
    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}