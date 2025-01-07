/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mycompany.quanlinhasach.dao.NhanVienDAO;
import com.mycompany.quanlinhasach.model.NhanVien;
import com.mycompany.quanlinhasach.view.ThongTinTaiKhoan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author ASUS
 */
public class ThongTinTaiKhoanController {

    private ThongTinTaiKhoan view;
    private NhanVienDAO nhanVienDAO; // Biến nhanVienDAO phải được khởi tạo ở đâu đó.
    public ThongTinTaiKhoan getView() {
        return view;
    }

    public ThongTinTaiKhoanController(ThongTinTaiKhoan view, NhanVienDAO nhanVienDAO, NhanVien nhanVien) {
        this.view = view;
        this.nhanVienDAO = nhanVienDAO;

        fillNhanVienData(nhanVien);
        
        // Button cập nhật thông tin tài khoản
        this.view.getUpdateBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpdateButtonClick(nhanVien.getMaNhanVien());
            }
        });
        
        // Button đổi mật khẩu
        view.getChangePasswordBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                view.getDoiMatKhau().setVisible(true);
            }
        });
        view.getCancelBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                view.getDoiMatKhau().setVisible(false);
            }
        });
        
        view.getSaveBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                handleChangePassword(nhanVien.getMaNhanVien());
            }
        });
        
    }

    private void handleUpdateButtonClick(int maNhanVien) {
        String tenNhanVien = view.getUserNameTxtField().getText();
        String email = view.getEmailTxtField().getText();
        String gioiTinh = (String) view.getGenderComboBox().getSelectedItem();
        String ngaySinh = new SimpleDateFormat("yyyy-MM-dd").format(view.getNgaySinhChooser().getDate());
        String trangThaiText = view.getStatusComboBox().getSelectedItem().toString();
        String soDienThoai = view.getPhoneNumberTxtField().getText();

        // Kiểm tra xem có bị trống ô không
        if (tenNhanVien.isEmpty() || email.isEmpty() || soDienThoai.isEmpty() || ngaySinh.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(
                    view,
                    "Vui lòng nhập đầy đủ thông tin!",
                    "Thông báo",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int trangThai = trangThaiText.equals("Đang làm") ? 1 : 0;

        NhanVien nhanVien = new NhanVien(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, soDienThoai, email, trangThai);

        boolean isUpdated = nhanVienDAO.updateTTTK(nhanVien);

        if (isUpdated) {
            javax.swing.JOptionPane.showMessageDialog(
                    view,
                    "Sửa nhân viên thành công!",
                    "Thông báo",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
            );

        } else {
            javax.swing.JOptionPane.showMessageDialog(
                    view,
                    "Sửa nhân viên thất bại!",
                    "Lỗi",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void fillNhanVienData(NhanVien nhanVien) {
        view.getUserNameTxtField().setText(nhanVien.getTenNhanVien());
        view.getSalaryTxtField().setText(String.valueOf(nhanVien.getLuong()));
        view.getEmailTxtField().setText(nhanVien.getEmail());
        view.getGenderComboBox().setSelectedItem(nhanVien.getGioiTinh());
        view.getStartingDateTxtField().setText(nhanVien.getNgayVaoLam());
        view.getPhoneNumberTxtField().setText(nhanVien.getSoDienThoai());

        try {
            // Chuyển đổi ngày sinh từ String sang Date nếu cần
            Date ngaySinhDate = new SimpleDateFormat("yyyy-MM-dd").parse(nhanVien.getNgaySinh());

            // Đặt giá trị Date vào JDateChooser
            view.getNgaySinhChooser().setDate(ngaySinhDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void handleChangePassword(int maNhanVien) {
        String oldPassword = view.getOldPasswordTxtField().getText();
        String newPassword = view.getNewPasswordTxtField().getText();
        String confirmPassword = view.getConfirmPasswordTxtField().getText(); // Thêm trường xác nhận mật khẩu

        try {
            // Kiểm tra mật khẩu cũ, mật khẩu mới và xác nhận mật khẩu không được rỗng
            if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra độ dài mật khẩu mới
            if (newPassword.length() < 6) {
                JOptionPane.showMessageDialog(view, "Mật khẩu mới phải có ít nhất 6 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra mật khẩu mới và xác nhận mật khẩu
            if (!newPassword.equals(confirmPassword)) {
                System.out.println("Mật khẩu xác nhận phải giống mật khẩu mới");
                JOptionPane.showMessageDialog(view, "Mật khẩu xác nhận phải giống mật khẩu mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lấy thông tin nhân viên từ database
            NhanVien nhanVien = nhanVienDAO.selectById(maNhanVien);

            // Xác minh mật khẩu cũ
            boolean isPasswordCorrect = BCrypt.verifyer().verify(oldPassword.toCharArray(), nhanVien.getMatKhau().toCharArray()).verified;

            if (!isPasswordCorrect) {
                JOptionPane.showMessageDialog(view, "Mật khẩu cũ không chính xác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Mã hóa mật khẩu mới
//            String hashedPassword = BCrypt.withDefaults().hashToString(12, newPassword.toCharArray());
            char[] hashedPasswordChar = BCrypt.withDefaults().hashToChar(12, newPassword.toCharArray());
            String hashedPassword = new String(hashedPasswordChar);

            System.out.println(hashedPassword);
            // Cập nhật mật khẩu mới vào database
            nhanVien.setMatkhau(hashedPassword);
            nhanVienDAO.update(nhanVien);

            JOptionPane.showMessageDialog(view, "Đổi mật khẩu thành công!");
            view.getDoiMatKhau().setVisible(false);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi không xác định: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
