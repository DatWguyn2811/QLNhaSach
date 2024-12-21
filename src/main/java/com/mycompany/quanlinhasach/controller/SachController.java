/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author NGUYEN MY NGAN
 */
import DAO.SachDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Sach;

public class SachController {
    private SachDAO sachDAO;
    private JTable table;

    public SachController(SachDAO sachDAO, JTable table) {
        this.sachDAO = sachDAO;
        this.table = table;
    }

    public void kiemTraVaThemSach(String tenSach, String nhaXuatBan, int namXuatBan, int soLuong, int donGia, String ngayNhap) {
        try {
            // Kiểm tra sách có tồn tại trong cơ sở dữ liệu
            if (!sachDAO.kiemTraSachTonTai(tenSach, nhaXuatBan,namXuatBan)) {
                JOptionPane.showMessageDialog(null, "Sách không tồn tại trong cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Nếu sách tồn tại, thêm một hàng mới vào bảng hiển thị
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.addRow(new Object[] {tenSach, "Tác giả", nhaXuatBan, namXuatBan, soLuong, donGia, ngayNhap});

            JOptionPane.showMessageDialog(null, "Thêm sách thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void kiemTraVaThemSach1(String tenSach, String nhaXuatBan, int namXuatBan, int soLuong, int donGia) {
        try {
            // Lấy tên tác giả và thể loại từ DAO
            String tacGia = sachDAO.layTenTacGia(tenSach);
            String theLoai = sachDAO.layTenTheLoai(tenSach);

            // Kiểm tra nếu sách đã tồn tại trong cơ sở dữ liệu
            if (sachDAO.kiemTraSachTonTai(tenSach, nhaXuatBan, namXuatBan)) {
                JOptionPane.showMessageDialog(null, "Sách đã tồn tại trong cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra nếu sách đã tồn tại trong bảng hiển thị
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                String existingTenSach = (String) model.getValueAt(i, 0);
                String existingNhaXuatBan = (String) model.getValueAt(i, 3);
                int existingNamXuatBan = (int) model.getValueAt(i, 4);

                // Kiểm tra trùng lặp
                if (existingTenSach.equals(tenSach) &&
                    existingNhaXuatBan.equals(nhaXuatBan) &&
                    existingNamXuatBan == namXuatBan) {
                    JOptionPane.showMessageDialog(null, "Sách đã tồn tại trong bảng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Nếu sách không tồn tại, thêm một hàng mới vào bảng
            model.addRow(new Object[]{tenSach, tacGia, theLoai, nhaXuatBan, namXuatBan, soLuong, donGia});

            JOptionPane.showMessageDialog(null, "Thêm sách thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public List<Sach> layDuLieuTuBang() {
        List<Sach> danhSachSach = new ArrayList<>();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            String tenDauSach = (String) model.getValueAt(i, 0); // Lấy mã đầu sách
            int donGia = Integer.parseInt(model.getValueAt(i, 5).toString()); // Chuyển đổi dữ liệu sang int
            int soLuong = Integer.parseInt(model.getValueAt(i, 4).toString());
            int namXuatBan = Integer.parseInt(model.getValueAt(i, 3).toString());
            String nhaXuatBan = (String) model.getValueAt(i, 2);
            int maNhaXuatBan = sachDAO.layMaNhaXuatBan(nhaXuatBan); // Lấy mã nhà xuất bản

            Sach sach = new Sach();
            danhSachSach.add(sach);
        }

        return danhSachSach;
    }


}

