/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.controller;

import com.mycompany.quanlinhasach.dao.KhachHangDAO;
import com.mycompany.quanlinhasach.dao.PhieuThuTienDAO;
import com.mycompany.quanlinhasach.model.KhachHang;
import com.mycompany.quanlinhasach.model.NhanVien;
import com.mycompany.quanlinhasach.model.PhieuThuTien;
import com.mycompany.quanlinhasach.view.QuanLiKhachHang;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TienDat
 */
public class KhachHangController {
    private KhachHangDAO khachHangDAO;
    private PhieuThuTienDAO phieuThuTienDAO;
    private QuanLiKhachHang view;
    private NhanVien currentUser;
    private int selectedID;
    
    public QuanLiKhachHang getView(){
        return view;
    }
    public KhachHangController(KhachHangDAO khDAO,PhieuThuTienDAO phieuThuTienDAO, QuanLiKhachHang v, NhanVien nv) {
        this.view = v;
        this.khachHangDAO = khDAO;
        this.currentUser = nv;
        
        // Button mở pop up thêm khách hàng
        view.getOpenPopUpThemKhachHangBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog themKhachHangDialog = view.getThemKhachHangDialog();
                themKhachHangDialog.setVisible(true);
                themKhachHangDialog.setLocationRelativeTo(view);
            }
        });
        
        // Button mở pop up sửa khách hàng
        view.getOpenPopUpSuaKhachHangBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = view.getKhachHangTable();
                int selectedRow = table.getSelectedRow();
                
                if (selectedRow == -1) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Vui lòng chọn một khách hàng cần sửa!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                }
                
                int maKhachhang = (int) table.getValueAt(selectedRow, 0);
                selectedID = (int) table.getValueAt(selectedRow, 0);
                
                KhachHang khachHang = khachHangDAO.selectKhachHangById(maKhachhang);
                
                JDialog suaKhachHangDialog = view.getSuaKhachHangDialog();
                
                view.getKhachHangNameTxtField1().setText(khachHang.getTenKhachHang());
                view.getDiaChiTxtField1().setText(khachHang.getDiaChi());
                view.getSoDienThoaiTxtField1().setText(khachHang.getSoDienThoai());
                view.getEmailTxtField1().setText(khachHang.getEmail());
                
                suaKhachHangDialog.setVisible(true);
                suaKhachHangDialog.setLocationRelativeTo(view);
            }
        
        });
        
        // Button mở pop up phiếu thu tiền khách hàng
        view.getOpenPopUpPhieuThuTienBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = view.getKhachHangTable();
                int selectedRow = table.getSelectedRow();
                
                if (selectedRow == -1) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Vui lòng chọn một khách hàng cần thêm phiếu thu tiền!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                }
                
                int maKhachhang = (int) table.getValueAt(selectedRow, 0);
                selectedID = (int) table.getValueAt(selectedRow, 0);
                
                KhachHang khachHang = khachHangDAO.selectKhachHangById(maKhachhang);
                int selectedTongNo = khachHang.getTongNo();
                if (selectedTongNo == 0) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Khách hàng đang không nợ!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                
                JDialog phieuThuTienDialog = view.getThemPhieuThuTien();
                
                view.getTenKhachThuTienTxtField().setText(khachHang.getTenKhachHang());
                view.getDiaChiKhachThuTienTxtField().setText(khachHang.getDiaChi());
                view.getSoDienThoaiThuTienTxtField().setText(khachHang.getSoDienThoai());
                view.getEmailKhachThuTienTxtField().setText(khachHang.getEmail());
                
                phieuThuTienDialog.setVisible(true);
                phieuThuTienDialog.setLocationRelativeTo(view);
            }       
        });
        
        // Button chọn xác nhận thêm khách hàng
        view.getXacNhanThemBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenKhachHang = view.getKhachHangNameTxtField().getText();
                String diaChi = view.getDiaChiTxtField().getText();
                String soDienThoai = view.getSoDienThoaiTxtField().getText();
                String email = view.getEmailTxtField().getText();
               
                if (tenKhachHang.isEmpty() || diaChi.isEmpty() || soDienThoai.isEmpty() || email.isEmpty() ) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Vui lòng nhập đầy đủ thông tin!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                        return;
                }
                
                if (!isValidPhoneNumber(soDienThoai)) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Số điện thoại không hợp lệ!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                        return;
                }
                   
                
                if (!isValidEmail(email)) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Email không hợp lệ!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                        return;
                }
                
                KhachHang khachHang = new KhachHang(tenKhachHang, diaChi, soDienThoai, email);
                
                boolean isAdded = khachHangDAO.insertKhachHang(khachHang);
                
                if (isAdded) {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Thêm khách hàng thành công!",
                            "Thông báo",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );
                    view.getThemKhachHangDialog().setVisible(false);
                    loadTableKhachHangData();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Thêm khách hàng thất bại!",
                            "Lỗi",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
                
            }
        });
        
        // Button chọn huỷ thêm khách hàng
        view.getXacNhanHuyThemBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getKhachHangNameTxtField().setText("");
                view.getDiaChiTxtField().setText("");
                view.getSoDienThoaiTxtField().setText("");
                view.getEmailTxtField().setText("");
                
                view.getThemKhachHangDialog().setVisible(false);
            }
        });
        
        // Button chọn xác nhận sửa khách hàng
        view.getXacNhanSuaBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int maKhachHang = selectedID;
                String tenKhachHang = view.getKhachHangNameTxtField1().getText();
                String diaChi = view.getDiaChiTxtField1().getText();
                String soDienThoai = view.getSoDienThoaiTxtField1().getText();
                String email = view.getEmailTxtField1().getText();
                
                if (diaChi.isEmpty() || soDienThoai.isEmpty() || email.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Vui lòng nhập đầy đủ thông tin!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                        return;
                }
                
                if (!isValidPhoneNumber(soDienThoai)) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Số điện thoại không hợp lệ!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                        return;
                }
                   
                
                if (!isValidEmail(email)) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Email không hợp lệ!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                        return;
                }
                
                KhachHang khachHang = new KhachHang(maKhachHang, tenKhachHang, diaChi, soDienThoai, email);
                
                boolean isUpdated = khachHangDAO.updateKhachHang(khachHang);
                
                if (isUpdated) {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Sửa khách hàng thành công!",
                            "Thông báo",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );
                    view.getSuaKhachHangDialog().setVisible(false);
                    loadTableKhachHangData();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Sửa khách hàng thất bại!",
                            "Lỗi",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
                
            }
        }); 
        
        // Button chọn huỷ sửa khách hàng
        view.getXacNhanHuySuaBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                view.getKhachHangNameTxtField1().setText("");
                view.getDiaChiTxtField1().setText("");
                view.getSoDienThoaiTxtField1().setText("");
                view.getEmailTxtField1().setText("");
                
                selectedID = -1;
                view.getSuaKhachHangDialog().setVisible(false);
            }
        });
        
        // Button chọn xác nhận thêm phiếu thu tiền
        view.getXacNhanThemPhieuThuTien().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int maKhachHang = selectedID;
                String soTienThuText = view.getSoTienThuTxtField().getText();
                
                if (!soTienThuText.matches("\\d+")) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Số tiền thu không đúng định dạng!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                        return;
                }
                
                int soTienThu = Integer.parseInt(soTienThuText);
                
                
                String ngayThuTien = convertDateToString(view.getNgayThuTienDateChooser());
                int maNhanVien = currentUser.getMaNhanVien();
                
                KhachHang kh = khachHangDAO.selectKhachHangById(maKhachHang);
                
                if (kh.getTongNo() - soTienThu < 0) {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Số tiền thu không vượt quá số nợ!",
                            "Thông báo",
                            javax.swing.JOptionPane.WARNING_MESSAGE
                        );
                    return;
                } 
                
                PhieuThuTien phieuThuTien = new PhieuThuTien(maKhachHang, soTienThu, ngayThuTien, maNhanVien);
                               
                boolean isAdded = phieuThuTienDAO.insertPhieuThuTien(phieuThuTien);
                
                if (isAdded) {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Thêm phiếu thu tiền thành công!",
                            "Thông báo",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );
                    view.getThemKhachHangDialog().setVisible(false);
                    loadTableKhachHangData();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Thêm phiếu thu tiền thất bại!",
                            "Lỗi",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        
        // Button chọn xác nhận huỷ phiếu thu tiền
        view.getXacNhanHuyThemPhieuThu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getSoTienThuTxtField().setText("");
                view.getNgayThuTienDateChooser().setDate(null);
                
                selectedID = -1;
                view.getThemPhieuThuTien().setVisible(false);
            }
        });
        
        loadTableKhachHangData();
    }

    private  void loadTableKhachHangData() {
        DefaultTableModel defaultTableModel = new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên khách hàng", "Địa chỉ", "Số điện thoại", "Email", "Tổng nợ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        defaultTableModel.setRowCount(0);
        
        ArrayList<KhachHang> khachHangList = khachHangDAO.selectAllKhachHang();
        
        for (KhachHang kh : khachHangList) {
            defaultTableModel.addRow(new Object[] {
                kh.getMaKhachHang(),
                kh.getTenKhachHang(),
                kh.getDiaChi(),
                kh.getSoDienThoai(),
                kh.getEmail(),
                kh.getTongNo()
            });
        }
        
        view.getKhachHangTable().setModel(defaultTableModel);
        
        view.getSearchKhachHangTxtField().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                performSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
            
            private void performSearch() {
                String searchText = view.getSearchKhachHangTxtField().getText().trim().toLowerCase();
                defaultTableModel.setRowCount(0);
                
                for (KhachHang kh : khachHangList) {
                    if (kh.getTenKhachHang().toLowerCase().contains(searchText)) {
                        defaultTableModel.addRow(new Object[] {
                            kh.getMaKhachHang(),
                            kh.getTenKhachHang(),
                            kh.getSoDienThoai(),
                            kh.getEmail(),
                            kh.getTongNo()
                        });
                    }
                }
            }
        });
        
    }
    
    public static boolean isValidPhoneNumber(String phone) {
        // Biểu thức chính quy kiểm tra số điện thoại 10 chữ số
        String regex = "^\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    
    public static boolean isValidEmail(String email) {
        // Biểu thức chính quy kiểm tra định dạng email
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public static String convertDateToString(JDateChooser jDateChooser) {
        Date selectedDate = jDateChooser.getDate();
        
        if (selectedDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(selectedDate);
        } else {
            return "";
        }
    }
}
