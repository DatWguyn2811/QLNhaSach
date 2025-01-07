/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.controller;

import com.mycompany.quanlinhasach.dao.NhanVienDAO;
import com.mycompany.quanlinhasach.model.NhanVien;
import com.mycompany.quanlinhasach.view.QuanLiNhanVien;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author TienDat
 */
public class NhanVienController  {
    private QuanLiNhanVien view;
    private NhanVienDAO nhanVienDAO;
    private int selectedID;
    private String emailTemp;
    
    public QuanLiNhanVien getView() {
        return view;
    }

    
    public NhanVienController(QuanLiNhanVien view, NhanVienDAO dao) {
        this.view = view;
        this.nhanVienDAO = dao;
        
        view.getjDateChooser1().getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Date selectedDate = view.getjDateChooser1().getDate();
                if (selectedDate != null) {
                    view.getjDateChooser2().setMinSelectableDate(selectedDate);
                }
            } 
        });
        
        // Button mở PopUp Thêm nhân viên
        view.getAddBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog themNhanVienDialog = view.getThemNhanVienDialog();
                themNhanVienDialog.setVisible(true);
                themNhanVienDialog.setLocationRelativeTo(view);
            }  
        });
        
        // Button mở PopUp Sửa nhân viên
        view.getEditBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = view.getNhanVienTable();
                int selectedRow = table.getSelectedRow();
                
                if (selectedRow == -1) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Vui lòng chọn một nhân viên để sửa!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                }
                
                int maNhanVien = (int) table.getValueAt(selectedRow, 0);
                selectedID = (int) table.getValueAt(selectedRow, 0);
                NhanVien nhanVien = NhanVienDAO.getInstance().selectById(maNhanVien);
                
                JDialog suaNhanVienDialog = view.getSuaNhanVienDialog();
                fillSelectedNhanVienDataToEditPopUp(nhanVien);
                suaNhanVienDialog.setVisible(true);
                suaNhanVienDialog.setLocationRelativeTo(view);
            }  
        });
        
        // Button Xoá nhân viên 
        view.getDeleteBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = view.getNhanVienTable();
                int selectedRow = table.getSelectedRow();

                // Kiểm tra xem có dòng nào được chọn không
                if (selectedRow == -1) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Vui lòng chọn một nhân viên để xoá",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                int maNhanVien = (int) table.getValueAt(selectedRow, 0);

                // Xác nhận 
                int confirm = javax.swing.JOptionPane.showConfirmDialog(
                    view,
                    "Chắc chắn muốn xoá nhân viên này không?",
                    "Xác nhận xoá",
                    javax.swing.JOptionPane.YES_NO_OPTION,
                    javax.swing.JOptionPane.QUESTION_MESSAGE
                );

                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                    boolean isDeleted = nhanVienDAO.delete(maNhanVien);

                    if (isDeleted) {
                        javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Xoá nhân viên thành công",
                            "Thông báo",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                        );

                        // Tải lại bảng
                        loadTableData();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Xoá nhân viên thất bại",
                            "Lỗi",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        });
        
        // Button thêm nhân viên trong pop up
        view.getAddBtn1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenNhanVien = view.getUserNameTxtField1().getText();
                String luongText = view.getSalaryTxtField1().getText();
                String email = view.getEmailTxtField1().getText();
                String gioiTinh = view.getGenderComboBox1().getSelectedItem().toString();
                String ngaySinh = convertDateToString(view.getjDateChooser1());
                String ngayVaoLam = convertDateToString(view.getjDateChooser2());
                String vaiTro = view.getPositionComboBox1().getSelectedItem().toString();
                String trangThaiText = view.getStatusComboBox1().getSelectedItem().toString();
                String soDienThoai = view.getPhoneNumberTxtField1().getText();
                String tenDangNhap = view.getUserAccountTxtField1().getText();
                char[] password = view.getUserPasswordField1().getPassword();
                String matKhau = new String(password);
                
                // Kiểm tra xem có bị trống ô không
                if (tenNhanVien.isEmpty() || luongText.isEmpty() || email.isEmpty() || soDienThoai.isEmpty()
                    || tenDangNhap.isEmpty() || matKhau.isEmpty() || ngaySinh.isEmpty() || ngayVaoLam.isEmpty()) {
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
                
                if (nhanVienDAO.kiemTraEmail(email)) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Email tồn tại!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                        return;
                }
                
                if (nhanVienDAO.kiemTraTenDangNhap(tenDangNhap)) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Tên đăng nhập tồn tại!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                        return;
                }
                
              if (ngayVaoLam.compareTo(ngaySinh) < 0) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Ngày vào làm phải bé hơn ngày sinh!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                        return;
                }
                
                
                int luong;
                try {
                    luong = Integer.parseInt(luongText);
                } catch (NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Lương không hợp lệ!",
                            "Lỗi",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                
                int trangThai = trangThaiText.equals("Đang làm") ? 1 : 0;
                
                int maQuyen = vaiTro.equals("Quản lí") ? 1 : 2;           
                
                NhanVien nhanVienMoi = new NhanVien(tenNhanVien, gioiTinh, ngaySinh, soDienThoai, email, luong, trangThai, ngayVaoLam, tenDangNhap, matKhau, vaiTro, maQuyen);
                
                boolean isAdded = nhanVienDAO.insert(nhanVienMoi);
                
                if (isAdded) {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Thêm nhân viên thành công!",
                            "Thông báo",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );

                    // Đóng popup và tải lại bảng
                    view.getThemNhanVienDialog().setVisible(false);
                    loadTableData();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Thêm nhân viên thất bại!",
                            "Lỗi",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
                
            }
        });
        
        // Button huỷ thêm nhân viên trong pop up
        view.getCancelBtn1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getUserNameTxtField1().setText("");
                view.getSalaryTxtField1().setText("");
                view.getEmailTxtField1().setText("");
                view.getGenderComboBox1().setSelectedIndex(0);
                view.getjDateChooser1().setDate(null);
                view.getjDateChooser2().setDate(null);
                view.getPositionComboBox1().setSelectedIndex(0);
                view.getStatusComboBox1().setSelectedIndex(0);
                view.getPhoneNumberTxtField1().setText("");
                view.getUserAccountTxtField1().setText("");
                view.getUserPasswordField1().setText("");
                
                view.getThemNhanVienDialog().setVisible(false);
            }
        });
        
        // Button huỷ sửa nhân viên trong pop up
        view.getCancelBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getUserNameTxtField().setText("");
                view.getSalaryTxtField().setText("");
                view.getEmailTxtField().setText("");
                view.getGenderComboBox().setSelectedIndex(0);
                view.getjDateChooser3().setDate(null);
                view.getjDateChooser4().setDate(null); 
                view.getPositionComboBox().setSelectedIndex(0);
                view.getStatusComboBox().setSelectedIndex(0);
                view.getPhoneNumberTxtField().setText("");
                view.getUserAccountTxtField().setText("");
                view.getUserPasswordField().setText("");       
                selectedID = -1;    
                view.getSuaNhanVienDialog().setVisible(false);
            }
        });
        
        // Button cập nhật nhân viên
        view.getUpdateBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int maNhanVien = selectedID;
                String tenNhanVien = view.getUserNameTxtField().getText();
                String luongText = view.getSalaryTxtField().getText();
                String email = view.getEmailTxtField().getText();
                String gioiTinh = view.getGenderComboBox().getSelectedItem().toString();
                String ngaySinh = convertDateToString(view.getjDateChooser3());
                String ngayVaoLam = convertDateToString(view.getjDateChooser4());
                String vaiTro = view.getPositionComboBox().getSelectedItem().toString();
                String trangThaiText = view.getStatusComboBox().getSelectedItem().toString();
                String soDienThoai = view.getPhoneNumberTxtField().getText();
                String tenDangNhap = view.getUserAccountTxtField().getText();
                char[] password = view.getUserPasswordField().getPassword();
                String matKhau = new String(password);
                
                // Kiểm tra xem có bị trống ô không
                if (luongText.isEmpty() || email.isEmpty() || soDienThoai.isEmpty()) {
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
                
                if (nhanVienDAO.kiemTraEmail(email) && !emailTemp.equals(email)) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Email tồn tại!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                        return;
                }
                
                int luong;
                try {
                    luong = Integer.parseInt(luongText);
                } catch (NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Lương phải là số nguyên hợp lệ!",
                            "Lỗi",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                
                int trangThai = trangThaiText.equals("Đang làm") ? 1 : 0;
                
                int maQuyen = vaiTro.equals("Quản lí") ? 1 : 2;
                
                NhanVien nhanVien = new NhanVien(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, soDienThoai, email, luong, trangThai, ngayVaoLam, tenDangNhap, matKhau, vaiTro, maQuyen);
                
                boolean isUpdated = nhanVienDAO.update(nhanVien);
                
                if (isUpdated) {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Sửa nhân viên thành công!",
                            "Thông báo",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );

                    // Đóng popup và tải lại bảng
                    view.getSuaNhanVienDialog().setVisible(false);
                    loadTableData();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Sửa nhân viên thất bại!",
                            "Lỗi",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
            }
            
        });
        
        // Tải dữ liệu ban đầu
        
        loadTableData();
    }
    
    
    public void loadCountNhanVien() {
        int totalNhanVien = NhanVienDAO.getInstance().selectTotalNhanVien();
        view.getCountUserLabel().setText("Tổng nhân viên: " + totalNhanVien);
    }
    
    public void loadTableData() {
        loadCountNhanVien();
        DefaultTableModel defaultTableModel = new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "Họ và tên", "Số điện thoại", "Email", "Giới tính", "Ngày sinh", "Ngày bắt đầu", "Chức vụ", "Lương", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        defaultTableModel.setRowCount(0);
        
        
    
        ArrayList<NhanVien> nhanVienList = nhanVienDAO.selectAll();
        
        for (NhanVien nv : nhanVienList) { 
            
            String trangThai;
            if (nv.getTrangThai() == 1) {
                trangThai = "Đang làm";
            } else {
                trangThai = "Nghỉ làm";
            }
            
            String formattedNgaySinh = "2000-01-01";
            String formattedNgayVaoLam = "2000-01-01";
            
            try {
                // Định dạng ban đầu (yyyy/MM/dd) và định dạng đích (dd/MM/yyyy)
                SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");

                // Chuyển đổi ngày sinh
                Date ngaySinhDate = originalFormat.parse(nv.getNgaySinh());
                formattedNgaySinh = targetFormat.format(ngaySinhDate);

                // Chuyển đổi ngày vào làm
                Date ngayVaoLamDate = originalFormat.parse(nv.getNgayVaoLam());
                formattedNgayVaoLam = targetFormat.format(ngayVaoLamDate);

            } catch (Exception e) {
                e.printStackTrace();;
            }

            
            defaultTableModel.addRow(new Object[] {
                    nv.getMaNhanVien(),
                    nv.getTenNhanVien(),
                    nv.getSoDienThoai(),
                    nv.getEmail(),
                    nv.getGioiTinh(),
                    formattedNgaySinh,
                    formattedNgayVaoLam,
                    nv.getVaiTro(),
                    nv.getLuong(),
                    trangThai
            });
        }
        
        
        
        
        view.getNhanVienTable().setModel(defaultTableModel);
        
        // Chỉnh độ rộng các cột
        setTableColumnWidths(view.getNhanVienTable());
        
        view.getSearchTextField().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                performSearch();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                performSearch();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                // Không cần cho PlainTextField
            }

            private void performSearch() {
                String searchText = view.getSearchTextField().getText().trim().toLowerCase();
                defaultTableModel.setRowCount(0); // Xóa tất cả các hàng
                for (NhanVien nv : nhanVienList) {
                    String trangThai;
                    if (nv.getTrangThai() == 1) {
                        trangThai = "Đang làm";
                    } else {
                        trangThai = "Nghỉ làm";
                    }

                    if (nv.getTenNhanVien().toLowerCase().contains(searchText)) {
                        defaultTableModel.addRow(new Object[] {
                            nv.getMaNhanVien(),
                            nv.getTenNhanVien(),
                            nv.getSoDienThoai(),
                            nv.getEmail(),
                            nv.getGioiTinh(),
                            nv.getNgaySinh(),
                            nv.getNgayVaoLam(),
                            nv.getVaiTro(),
                            nv.getLuong(),
                            trangThai
                        });
                    }
                }

            }
        });
    }
    
    public void setTableColumnWidths(JTable table) {
    // Lấy TableColumnModel từ JTable
        TableColumnModel columnModel = table.getColumnModel();

        // Đặt độ rộng cho từng cột
        columnModel.getColumn(0).setPreferredWidth(30);  // Cột "ID"
        columnModel.getColumn(1).setPreferredWidth(120); // Cột "Họ và tên"
        columnModel.getColumn(2).setPreferredWidth(50); // Cột "Số điện thoại"
        columnModel.getColumn(3).setPreferredWidth(120); // Cột "Email"
        columnModel.getColumn(4).setPreferredWidth(40); // Cột "Giới tính"
        columnModel.getColumn(5).setPreferredWidth(60); // Cột "Ngày sinh"
        columnModel.getColumn(6).setPreferredWidth(70); // Cột "Ngày bắt đầu"
        columnModel.getColumn(7).setPreferredWidth(40); // Cột "Chức vụ"
        columnModel.getColumn(8).setPreferredWidth(40); // Cột "Lương"
        columnModel.getColumn(9).setPreferredWidth(60); // Cột "Trạng thái"
    }
    
    public void fillSelectedNhanVienDataToEditPopUp(NhanVien nhanVien) {
        view.getUserNameTxtField().setText(nhanVien.getTenNhanVien());
        view.getSalaryTxtField().setText(String.valueOf(nhanVien.getLuong()));
        view.getEmailTxtField().setText(nhanVien.getEmail());
        emailTemp = view.getEmailTxtField().getText();
        view.getGenderComboBox().setSelectedItem(nhanVien.getGioiTinh());
        view.getPositionComboBox().setSelectedItem(nhanVien.getVaiTro());
        view.getStatusComboBox().setSelectedItem(nhanVien.getTrangThai() == 1 ? "Đang làm" : "Nghỉ làm");
        view.getPhoneNumberTxtField().setText(nhanVien.getSoDienThoai());
        view.getUserAccountTxtField().setText(nhanVien.getTenDangNhap());
        setDateForJDateChooser(nhanVien.getNgaySinh(), view.getjDateChooser3());
        setDateForJDateChooser(nhanVien.getNgayVaoLam(), view.getjDateChooser4());
    }
    
    public String convertDateToString(JDateChooser jDateChooser) {
        Date selectedDate = jDateChooser.getDate();
        
        if (selectedDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(selectedDate);
        } else {
            return "";
        }
    }
    
    public void setDateForJDateChooser(String dateString, JDateChooser dateChooser) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // Parse chuỗi ngày thành đối tượng Date
            Date date = dateFormat.parse(dateString);
            // Gán giá trị cho JDateChooser
            dateChooser.setDate(date);
        } catch (ParseException e) {
            // Xử lý lỗi nếu chuỗi ngày không hợp lệ
            System.err.println("Ngày không hợp lệ: " + dateString);
            e.printStackTrace();
        }
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
    
}
