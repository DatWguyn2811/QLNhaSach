/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mycompany.quanlinhasach.dao.NhanVienDAO;
import com.mycompany.quanlinhasach.model.NhanVien;
import com.mycompany.quanlinhasach.view.DangNhap;
import com.mycompany.quanlinhasach.view.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
/**
 *
 * @author ASUS
 */
public class DangNhapController {
    private DangNhap dangNhap;
    
    public DangNhapController(DangNhap dangNhap) {
        this.dangNhap = dangNhap;
        this.dangNhap.getjButton_Login().addActionListener(new LoginActionListener());
    }
    
    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tenDangNhap = dangNhap.getjTextField_Username().getText();
            String matKhau = new String(dangNhap.getjPasswordField_password().getPassword());

            NhanVien nhanVien = kiemTraDangNhap(tenDangNhap);
            
            if (nhanVien != null && BCrypt.verifyer().verify(matKhau.toCharArray(), nhanVien.getMatKhau().toCharArray()).verified) {
                JOptionPane.showMessageDialog(dangNhap, "Đăng nhập thành công!");
                Main mainFrame = new Main(nhanVien);
                mainFrame.setVisible(true);
                dangNhap.dispose(); 
            } else {
                // If login fails
                JOptionPane.showMessageDialog(dangNhap, "Sai tên đăng nhập hoặc mật khẩu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
  
    public NhanVien kiemTraDangNhap(String tenDangNhap) {
        if (tenDangNhap == null || tenDangNhap.trim().isEmpty()) {
            return null; 
        }
        return NhanVienDAO.getInstance().kiemTraDangNhap(tenDangNhap); // Retrieve user based on username
    }
}
