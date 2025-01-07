/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.controller;

import com.mycompany.quanlinhasach.dao.QuyDinhDAO;
import com.mycompany.quanlinhasach.dao.TheLoaiDAO;
import com.mycompany.quanlinhasach.model.QuyDinh;
import com.mycompany.quanlinhasach.model.TheLoai;
import com.mycompany.quanlinhasach.view.CaiDatQuyDinh;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TienDat
 */
public class CaiDatQuyDinhController {
    private CaiDatQuyDinh view;
    private QuyDinhDAO quyDinhDAO;
    private TheLoaiDAO theLoaiDAO;
    private int selectedTheLoaiID;
    
    public CaiDatQuyDinh getView(){
        return view;
    }
    
    
    public CaiDatQuyDinhController(CaiDatQuyDinh v, QuyDinhDAO quyDinhDAO, TheLoaiDAO theLoaiDAO) {
        this.view = v;
        this.quyDinhDAO = quyDinhDAO;
        this.theLoaiDAO = theLoaiDAO;
        
        // Button mở pop up thêm danh mục
        view.getThemDanhMucBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog themDanhMucDialog = view.getThemDanhMucDialog();
                
                themDanhMucDialog.setVisible(true);
            }
        });
        
        // Button mở pop up sửa danh mục
        view.getSuaDanhMucBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = view.getjTable1();
                int selectedRow = table.getSelectedRow();
               
                if (selectedRow == -1) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Vui lòng chọn một danh mục để sửa!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                }
                
                int maTheLoai = (int) table.getValueAt(selectedRow, 0);
                selectedTheLoaiID = (int) table.getValueAt(selectedRow, 0);
                
                TheLoai theLoai = TheLoaiDAO.getInstance().selectTheLoaiById(maTheLoai);
                
                JDialog suaDanhMucDialog = view.getSuaDanhMucDialog();
                view.getTenDanhMucMoiTxtField1().setText(theLoai.getTenTheLoai());
                suaDanhMucDialog.setVisible(true);
            }
        });
        
        // Xoá danh mục
        view.getXoaDanhMucBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = view.getjTable1();
                
                int selectedRow = table.getSelectedRow();
                
                if (selectedRow == -1) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Vui lòng chọn một danh mục để xoá",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                
                int maTheLoai = (int) table.getValueAt(selectedRow, 0);
                
                int confirm = javax.swing.JOptionPane.showConfirmDialog(
                    view,
                    "Chắc chắn muốn xoá danh mục này không?",
                    "Xác nhận xoá",
                    javax.swing.JOptionPane.YES_NO_OPTION,
                    javax.swing.JOptionPane.QUESTION_MESSAGE
                );
                
                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                    boolean isDeleted = theLoaiDAO.deleteTheLoai(maTheLoai);

                    if (isDeleted) {
                        javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Xoá danh mục thành công",
                            "Thông báo",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                        );

                        // Tải lại bảng
                        loadDanhMucTableData();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Danh mục này vẫn còn sách, không thể xoá",
                            "Lỗi",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        });
        
        // Button cập nhật hệ số quy định
        view.getCapNhatQuyDinhBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hiển thị cửa sổ xác nhận
	        int confirm = javax.swing.JOptionPane.showConfirmDialog(
	            view,
	            "Bạn có chắc chắn muốn cập nhật các quy định?",
	            "Xác nhận",
	            javax.swing.JOptionPane.YES_NO_OPTION,
	            javax.swing.JOptionPane.QUESTION_MESSAGE
	        );

	        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
	            try {
	                int soLuongNhapToiThieu = Integer.parseInt(view.getSoLuongNhapKhoToiThieuTxtField().getText());
	                int soLuongTonToiDaTruocNhap = Integer.parseInt(view.getSoLuongTonKhoToiDaTruocNhapTxtField().getText());
	                int soLuongTonToiThieuSauBan = Integer.parseInt(view.getSoLuongTonKhoToiThieuSauBanTxtField().getText());
	                double heSoGiaBan = Double.parseDouble(view.getHeSoGiaBanTxtField().getText());
	                int soTienNoToiDa = Integer.parseInt(view.getSoTienNoToiDaChoPhepTxtField().getText());
	                int apDungChinhSach = Integer.parseInt(String.valueOf(view.getApDungChinhSachComboBox().getSelectedItem().equals("Cho phép") ? 1 : 0 ));

	                QuyDinh qd = new QuyDinh(soLuongNhapToiThieu, soLuongTonToiDaTruocNhap, soTienNoToiDa, soLuongTonToiThieuSauBan, apDungChinhSach, heSoGiaBan);

	                boolean isUpdated = quyDinhDAO.updateQuyDinh(qd);

	                if (isUpdated) {
	                    javax.swing.JOptionPane.showMessageDialog(
	                        view,
	                        "Cập nhật quy định thành công!",
	                        "Thông báo",
	                        javax.swing.JOptionPane.INFORMATION_MESSAGE
	                    );
	                } else {
	                    javax.swing.JOptionPane.showMessageDialog(
	                        view,
	                        "Cập nhật quy định thất bại. Vui lòng thử lại!",
	                        "Lỗi",
	                        javax.swing.JOptionPane.ERROR_MESSAGE
	                    );
	                }
	            } catch (NumberFormatException ex) {
	                javax.swing.JOptionPane.showMessageDialog(
	                    view,
	                    "Dữ liệu nhập vào không hợp lệ. Vui lòng kiểm tra lại!",
	                    "Lỗi nhập liệu",
	                    javax.swing.JOptionPane.WARNING_MESSAGE
	                );
	            }
	        }
            }
        });
        
        // Button thêm danh mục
        view.getAddDanhMucBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenTheLoai = view.getTenDanhMucMoiTxtField().getText();
                
                if (tenTheLoai.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Vui lòng nhập danh mục!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                        return;
                }
                
                TheLoai theLoaiMoi = new TheLoai(tenTheLoai);
                
                boolean isAdded = theLoaiDAO.insertTheLoai(theLoaiMoi);
                
                if (isAdded) {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Thêm danh mục thành công!",
                            "Thông báo",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );

                    // Đóng popup và tải lại bảng
                    view.getThemDanhMucDialog().setVisible(false);
                    loadDanhMucTableData();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Thêm danh mục thất bại!",
                            "Lỗi",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        
        // Button huỷ thêm danh mục
        view.getCancelAddDanhMucBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTenDanhMucMoiTxtField().setText("");
                view.getThemDanhMucDialog().setVisible(false);
            }
        });
        
        // Button sửa danh mục
        view.getAddDanhMucBtn1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String tenTheLoai = view.getTenDanhMucMoiTxtField1().getText();
                
                if (tenTheLoai.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(
                        view,
                        "Danh mục không được trống!",
                        "Thông báo",
                        javax.swing.JOptionPane.WARNING_MESSAGE
                    );
                        return;
                }
                
                TheLoai theLoaiSua = new TheLoai(selectedTheLoaiID, tenTheLoai);
                
                boolean isUpdated = theLoaiDAO.updateTheLoai(theLoaiSua);
                
                if (isUpdated) {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Sửa danh mục thành công!",
                            "Thông báo",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );

                    // Đóng popup và tải lại bảng
                    view.getSuaDanhMucDialog().setVisible(false);
                    loadDanhMucTableData();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(
                            view,
                            "Sửa danh mục thất bại!",
                            "Lỗi",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        
        // Button huỷ sửa danh mục
        view.getCancelAddDanhMucBtn1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTenDanhMucMoiTxtField1().setText("");
                view.getSuaDanhMucDialog().setVisible(false);
            }
        });
        
        loadHeSoQuyDinhData();
        loadDanhMucTableData();
    }
   

    private void loadDanhMucTableData() {
        DefaultTableModel defaultTableModel = new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "ID", "Danh mục", "Số lượng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        
        
        
        defaultTableModel.setRowCount(0);
        
        ArrayList<TheLoai> theLoaiList = theLoaiDAO.selectAllTheLoai();
        
        for (TheLoai tl : theLoaiList) {
            int maTheLoai = tl.getMaTheLoai();
            String tenTheLoai = tl.getTenTheLoai();
            int soLuong = tl.getSoLuong();
            
            defaultTableModel.addRow(new Object[] {
                tl.getMaTheLoai(),
                tl.getTenTheLoai(),
                tl.getSoLuong()
            });  
        }
        
        view.getjTable1().setModel(defaultTableModel);
        
        
        JTable table = view.getjTable1();
        table.setModel(defaultTableModel);

        
        table.getTableHeader().setReorderingAllowed(false);
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setResizable(false); 
        }
    }

    private void loadHeSoQuyDinhData() {
        QuyDinh qd = QuyDinhDAO.getInstance().selectQuyDinh();
        view.getSoLuongNhapKhoToiThieuTxtField().setText(String.valueOf(qd.getSoLuongNhapToiThieu()));
        view.getSoLuongTonKhoToiDaTruocNhapTxtField().setText(String.valueOf(qd.getSoLuongTonToiThieuDeNhap()));
        view.getSoLuongTonKhoToiThieuSauBanTxtField().setText(String.valueOf(qd.getSoLuongTonSauKhiBanToiThieu()));
        view.getHeSoGiaBanTxtField().setText(String.valueOf(qd.getHeSoGiaBan()));
        view.getSoTienNoToiDaChoPhepTxtField().setText(String.valueOf(qd.getMonNoKhachHangToiDaDeBan()));
        view.getApDungChinhSachComboBox().setSelectedItem(String.valueOf(qd.getApDungChinhSachThuNo()));               
    }
}
