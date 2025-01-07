/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.controller;

import com.mycompany.quanlinhasach.dao.CTHDDAO;
import com.mycompany.quanlinhasach.dao.CT_PhieuNhapSachDAO;
import com.mycompany.quanlinhasach.dao.HoaDonDAO;
import com.mycompany.quanlinhasach.dao.PhieuNhapSachDAO;
import com.mycompany.quanlinhasach.dao.SachDAO;
import com.mycompany.quanlinhasach.dao.KhachHangDAO;
import com.mycompany.quanlinhasach.dao.QuyDinhDAO;
import com.mycompany.quanlinhasach.model.CTHD;
import com.mycompany.quanlinhasach.model.HoaDon;
import com.mycompany.quanlinhasach.model.KhachHang;
import com.mycompany.quanlinhasach.model.QuyDinh;
import com.mycompany.quanlinhasach.model.Sach;
import com.mycompany.quanlinhasach.view.BanHang;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.text.SimpleDateFormat;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class BanHangController implements ControllerInterface{
    
    private BanHang view;
    private SachDAO sach1DAO;
    private KhachHangDAO khachHangDAO;
    private HoaDonDAO hoaDonDAO;
    private CTHDDAO cthdDAO;
    
   
    public BanHang getView(){
        return view;
    }
    public BanHangController(BanHang view, SachDAO dao,KhachHangDAO khdao, HoaDonDAO hoaDonDAO,CTHDDAO cthdDAO){
       
        this.view=view;
        this.sach1DAO=dao;
        this.khachHangDAO=khdao;
        this.hoaDonDAO=hoaDonDAO;
        this.cthdDAO=cthdDAO;
        loadTableData();
        // Thêm sự kiện MouseListener vào JTable để khi click vào dòng sẽ hiển thị JDialog NhapSoLuong
        this.view.getSachTable().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sachTableMouseClicked(evt);
            }
        });
        // Trong Constructor BanHangController
    view.getSoLuongLuuBtn().addActionListener(new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            handleLuuButton();
        }
    });



view.getThuTienThanhToan().addActionListener(new java.awt.event.ActionListener() {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        // Lấy số điện thoại của khách hàng
        String soDienThoai = view.getTxtSDT().getText().trim();

        // Kiểm tra nếu số điện thoại không rỗng
        if (!soDienThoai.isEmpty()) {
            // Lấy tên khách hàng và mã khách hàng từ cơ sở dữ liệu
            String tenKhachHang = sach1DAO.getTenKhachHangBySDT(soDienThoai);
            //int tongNoHienTai = sach1DAO.getTongNoBySDT(soDienThoai); // Lấy tổng nợ hiện tại từ CSDL
            int thuTien = Integer.parseInt(view.getTxtThuTien().getText().trim()); // Lấy số tiền thanh toán từ giao diện
            int tongTien = Integer.parseInt(view.getLblTongTien().getText().trim()); // Tổng tiền hóa đơn

            // Lấy giá trị ngày lập từ giao diện và chuyển về chuỗi
            java.util.Date ngayLapUtil = view.getDateNgayNhap().getDate();
            if (ngayLapUtil == null) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn ngày lập hóa đơn.", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Chuyển ngày từ java.util.Date thành chuỗi theo định dạng "yyyy-MM-dd"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngayLap = dateFormat.format(ngayLapUtil); // Chuyển ngày lập thành chuỗi

            if (tenKhachHang == null) {
                // Nếu khách hàng không tồn tại trong CSDL, thêm mới khách hàng
                KhachHang khachHangMoi = new KhachHang();
                khachHangMoi.setTenKhachHang(view.getTxtKhachHang().getText());
                khachHangMoi.setSoDienThoai(soDienThoai);
                khachHangMoi.setDiaChi(""); // Địa chỉ trống
                khachHangMoi.setEmail("");  // Email trống

                // Tạo đối tượng KhachHangDAO và gọi insertKhachHang
                if (khachHangDAO.insertKhachHang(khachHangMoi)) {
                    JOptionPane.showMessageDialog(view, "Khách hàng đã được thêm vào cơ sở dữ liệu.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(view, "Lỗi khi thêm khách hàng mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Lấy mã khách hàng từ cơ sở dữ liệu
            int maKhachHang = sach1DAO.getMaKhachHangBySDT(soDienThoai);

            // Tạo hóa đơn
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaKhachHang(maKhachHang);
            hoaDon.setNgayLap(ngayLap); // Gán ngày lập (dạng chuỗi)
            hoaDon.setTongTien(tongTien);
            hoaDon.setSoTienThu(thuTien);
            hoaDon.setConLai(tongTien - thuTien);
            int maNhanVien = Integer.parseInt(view.getTxtMaNhanVien().getText());
            hoaDon.setMaNhanVien(maNhanVien);
            
            HoaDonDAO hdDAO = new HoaDonDAO();
            // Gọi hàm insertHoaDon
            if (hdDAO.insert(hoaDon)) {
                JOptionPane.showMessageDialog(view, "Thêm hóa đơn thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Lỗi khi thêm hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lấy mã hóa đơn vừa thêm (bạn cần implement phương thức này trong HoaDonDAO)
            int maHoaDon = hdDAO.getLastInsertedId();
            System.out.println("Mã hóa đơn vừa thêm: " + maHoaDon); // In ra mã hóa đơn


            // Thêm chi tiết hóa đơn
            for (int i = 0; i < view.getSachHoadonTable().getRowCount(); i++) {
                // Lấy thông tin từ các cột trong bảng
                String tenSach = view.getSachTable().getValueAt(i, 0).toString();
                String NhaXB = view.getSachTable().getValueAt(i, 1).toString();
                String DonGia = view.getSachTable().getValueAt(i, 3).toString();
                String NamXBString = view.getSachTable().getValueAt(i, 2).toString();
                  
                // Chuyển NamXB từ String sang int
                int NamXB = Integer.parseInt(NamXBString);
                int maSach = sach1DAO.layMaSach(tenSach, NhaXB, NamXB);
                
                int soLuong = Integer.parseInt(view.getSachHoadonTable().getValueAt(i, 2).toString());
                int donGia = Integer.parseInt(view.getSachHoadonTable().getValueAt(i, 1).toString());
                int thanhTien = Integer.parseInt(view.getSachHoadonTable().getValueAt(i, 3).toString());

                // Tạo đối tượng CTHD (Chi tiết hóa đơn)
                CTHD cthd = new CTHD();
                cthd.setMaHoaDon(maHoaDon);
                cthd.setMaSach(maSach);
                cthd.setThanhTien(thanhTien);
                cthd.setSoLuong(soLuong);

                // Thêm chi tiết hóa đơn vào CSDL
                if (!cthdDAO.insert(cthd)) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi thêm chi tiết hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Ẩn nút Thu Tiền sau khi hoàn tất
            view.getThuTien().setVisible(false);
            view.getLblDaTra().setText(String.valueOf(thuTien)); // Hiển thị số tiền đã thu
            view.getLblConLai().setText(String.valueOf(tongTien - thuTien)); // Số tiền còn lại
            loadTableData();
        } else {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập số điện thoại khách hàng.", "Lỗi", JOptionPane.WARNING_MESSAGE);
        }
    }
});


    view.getSoLuongHuyBtn().addActionListener(new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            // Cập nhật thông tin thanh toán
            view.getNhapSoLuong().setVisible(false);
        }
    });




    view.getThuTienHuyBtn().addActionListener(new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            // Cập nhật thông tin thanh toán
            view.getThuTien().setVisible(false);
        }
    });
    loadHoaDonTableData();
    addEventHandlers();
    
    view.getTimKiem().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
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
                ArrayList<Sach> sachList = sach1DAO.selectAll();
                DefaultTableModel defaultTableModel = (DefaultTableModel) view.getSachTable().getModel();
                String searchText = view.getTimKiem().getText().trim().toLowerCase();
                defaultTableModel.setRowCount(0); // Xóa tất cả các hàng
                for (Sach s : sachList) {
                    

                    if (s.getTenSach().toLowerCase().contains(searchText)) {
                        defaultTableModel.addRow(new Object[] {
                            s.getTenSach(),
                            s.getTenNXB(),
                            s.getNamXuatBan(),
                            s.getDonGia(),
                            s.getSoLuong(),
                            
                        });
                    }
                }

            }
        });
    }
    

    @Override
    public void add(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private void updateThanhToanInfo() {
        try {
            // Kiểm tra nếu txtThuTien không trống và là số hợp lệ
            String thuTienText = view.getTxtThuTien().getText().trim();
            if (thuTienText.isEmpty()) {
                throw new NumberFormatException("Số tiền thu không thể trống.");
            }

            int thuTien = Integer.parseInt(thuTienText);

            // Kiểm tra nếu số tiền thu là số âm
            if (thuTien < 0) {
                throw new NumberFormatException("Số tiền thu không thể là số âm.");
            }

            // Lấy tổng tiền từ lblTongTien
            int tongTien = Integer.parseInt(view.getLblTongTien().getText().trim());

            // Kiểm tra nếu số tiền thu lớn hơn tổng tiền
            if (thuTien > tongTien) {
                throw new NumberFormatException("Số tiền thu không thể lớn hơn tổng tiền.");
            }

            // Cập nhật lblDaTra
            view.getLblDaTra().setText(String.format("%d", thuTien));

            // Cập nhật lblTongTien
            view.getLblTongTien().setText(String.format("%d", tongTien));

            // Tính số tiền còn lại
            int conLai = tongTien - thuTien;

            // Cập nhật lblConLai
            view.getLblConLai().setText(String.format("%d", conLai));

        } catch (NumberFormatException ex) {
            // Nếu nhập không hợp lệ, thông báo lỗi
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void loadTableData() {
    DefaultTableModel defaultTableModel = new DefaultTableModel(
        new Object[][]{},
        new Object[]{
            "Tên sách", "NXB", "Năm XB", "Đơn giá", "Tồn kho"
        }
    ) {
        boolean[] canEdit = new boolean[]{
            false, false, false, false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    };

    defaultTableModel.setRowCount(0);
     // Gọi phương thức selectAll để lấy danh sách sách từ cơ sở dữ liệu
    ArrayList<Sach> sachList = sach1DAO.selectAll();
       

    
    // Kiểm tra dữ liệu trả về
    System.out.println("Dữ liệu sách: " + sachList); // Dòng kiểm tra

    for (Sach sach : sachList) {
        defaultTableModel.addRow(new Object[]{
            sach.getTenSach(),
            sach.getTenNXB(),
            sach.getNamXuatBan(),
            sach.getDonGia(),
            sach.getSoLuong()
        });
    }
    
    
  
    view.getThanhToanBtn().addActionListener(new java.awt.event.ActionListener() {
    @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
        try {
            if (isThanhToanValid()) {
                // Xử lý logic thanh toán ở đây
                
                view.getThuTien().setVisible(true);
                view.getTxtSoLuong().setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    });
    view.getHuyBtn().addActionListener(new java.awt.event.ActionListener() {
    @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            
                // Xử lý logic thanh toán ở đây
                resetForm();
            
        }
    });

    
    view.getSachTable().setModel(defaultTableModel);}
    public void loadHoaDonTableData() {
        DefaultTableModel hoaDonTableModel = new DefaultTableModel(
        new Object[][]{},
        new Object[]{"Tên sách", "Đơn giá", "Số lượng", "Thành tiền"}
    ) {
        boolean[] canEdit = new boolean[]{false, false, false, false};

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    };
    view.getXoaBtn().addActionListener(new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            handleXoaButton();
        }
    });
    view.getSachHoadonTable().setModel(hoaDonTableModel);
    }
    private void updateTongTien() {
        DefaultTableModel hoaDonTableModel = (DefaultTableModel) view.getSachHoadonTable().getModel();
        int rowCount = hoaDonTableModel.getRowCount();
        int tongTien = 0;

        // Duyệt qua tất cả các hàng trong bảng hóa đơn và tính tổng thành tiền
        for (int i = 0; i < rowCount; i++) {
            int thanhTien = (Integer) hoaDonTableModel.getValueAt(i, 3); // Cột "Thành tiền" là cột thứ 4
            tongTien += thanhTien;
        }

        // Cập nhật tổng tiền vào lblTongTien
        view.getLblTongTien().setText(String.valueOf(tongTien));

    }

    private void addEventHandlers() {
        view.getTxtSDT().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String soDienThoai = view.getTxtSDT().getText().trim();
                    if (!soDienThoai.isEmpty()) {
                        // Gọi phương thức từ SachDAO để lấy tên khách hàng
                        String tenKhachHang = sach1DAO.getTenKhachHangBySDT(soDienThoai);

                        if (tenKhachHang != null) {
                            // Hiển thị tên khách hàng lên giao diện
                            view.getTxtKhachHang().setText(tenKhachHang);
                        } else {
                            // Thông báo khách hàng không tồn tại
                            JOptionPane.showMessageDialog(
                                view,
                                "Khách hàng không tồn tại!",
                                "Thông báo",
                                JOptionPane.WARNING_MESSAGE
                            );
                            // Xóa nội dung trường nhập
                            view.getTxtSDT().setText("");
                            view.getTxtKhachHang().setText("");
                        }
                    }
                }
            }
        });
    }


    private boolean isThanhToanValid() throws SQLException {
        // Kiểm tra các trường nhập liệu
        String sdt = view.getTxtSDT().getText().trim();
        String khachHang = view.getTxtKhachHang().getText().trim();
        Date ngayNhap = view.getDateNgayNhap().getDate(); // Giả sử bạn dùng JDateChooser hoặc tương tự
        String manv = view.getTxtMaNhanVien().getText().trim();
        
         String tenSach = (String) view.getSachTable().getValueAt(0, 0); // Lấy tên sách từ cột đầu tiên
        String nhaXuatBan = (String) view.getSachTable().getValueAt(0, 1); // Lấy NXB từ cột thứ hai
        int namXuatBan = Integer.parseInt(view.getSachTable().getValueAt(0, 2).toString()); // Lấy năm xuất bản
        
        int maNhanVien = Integer.parseInt(view.getTxtMaNhanVien().getText());
        // Lấy model của bảng hóa đơn
        DefaultTableModel hoaDonTableModel = (DefaultTableModel) view.getSachHoadonTable().getModel();
        if (!sach1DAO.kiemTraNhanVienTonTai(maNhanVien)) {
            JOptionPane.showMessageDialog(null, "Nhân viên không tồn tại trong cơ sở dữ liệu!.", "Lỗi", JOptionPane.ERROR_MESSAGE);
               
        }
        String txtSoLuong = view.getTxtSoLuong().getText().trim(); // Lấy giá trị từ trường txtSoLuong
        if (txtSoLuong.isEmpty()) {
        JOptionPane.showMessageDialog(
            view,
            "Vui lòng nhập số lượng sách!",
            "Lỗi nhập liệu",
            JOptionPane.ERROR_MESSAGE
        );
        return false;  // Dừng lại nếu số lượng không được nhập
    }
        // Lấy dữ liệu QuyĐịnh từ cơ sở dữ liệu
        QuyDinh qd = QuyDinhDAO.getInstance().selectQuyDinh();
        int TongNo = sach1DAO.getTongNoKH(sach1DAO.getMaKhachHangBySDT(sdt));
        if (TongNo > qd.getMonNoKhachHangToiDaDeBan() ) {
                    JOptionPane.showMessageDialog(null,
                            "Tổng nợ của khách đã lớn hơn " + qd.getMonNoKhachHangToiDaDeBan() + "!",
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                     return false;
        }
        

    int soLuong = 0;
    try {
        soLuong = Integer.parseInt(txtSoLuong); // Chuyển đổi nếu trường nhập liệu không rỗng
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(
            view,
            "Số lượng phải là một số nguyên hợp lệ!",
            "Lỗi nhập liệu",
            JOptionPane.ERROR_MESSAGE
        );
        return false;  // Dừng lại nếu không thể chuyển đổi thành số nguyên
    }

        System.out.print(soLuong);
        int slt = sach1DAO.laySoLuongTon(sach1DAO.layMaSach(tenSach, nhaXuatBan, namXuatBan));
        if (slt-soLuong < qd.getSoLuongTonSauKhiBanToiThieu()) {
                    JOptionPane.showMessageDialog(null,
                            "Số lượng tồn sau khi bán không đủ " + qd.getSoLuongTonToiThieuDeNhap() + "!",
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return false; // Dừng thực thi nếu điều kiện không thỏa mãn
                }
        // Kiểm tra các điều kiện
        if (sdt.isEmpty() || khachHang.isEmpty() || ngayNhap == null || manv.isEmpty() ) {
            JOptionPane.showMessageDialog(
                view,
                "Vui lòng điền đầy đủ thông tin vào các trường: Số điện thoại, Tên khách hàng, Mã nhân viên và Ngày nhập!",
                "Lỗi nhập liệu",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        if (hoaDonTableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(
                view,
                "Hóa đơn phải có ít nhất một dòng dữ liệu!",
                "Lỗi nhập liệu",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        return true;
        
    }
    
    private void resetForm() {
        view.getTxtSDT().setText("");
        view.getTxtKhachHang().setText("");
        view.getDateNgayNhap().setDate(null); // Đặt lại ngày nhập
        DefaultTableModel hoaDonTableModel = (DefaultTableModel) view.getSachHoadonTable().getModel();
        hoaDonTableModel.setRowCount(0);
        view.getLblTongTien().setText("0");
        view.getLblConLai().setText("0");
        view.getLblDaTra().setText("0");// Xóa toàn bộ dữ liệu trong bảng hóa đơn
    }


    // Thêm sự kiện lắng nghe khi chọn dòng trong bảng
    private void sachTableMouseClicked(java.awt.event.MouseEvent evt) {
        int selectedRow = view.getSachTable().getSelectedRow();
        if (selectedRow >= 0) {
            // Lấy thông tin sách từ dòng được chọn
            String tenSach = (String) view.getSachTable().getValueAt(selectedRow, 0);
            int donGia = (Integer) view.getSachTable().getValueAt(selectedRow, 3);
            
             // Giả sử cột "Tồn kho" là cột số 4

            // Hiển thị JDialog NhapSoLuong và truyền thông tin
            
            view.getNhapSoLuong().setVisible(true);
           
        }
        
    }
    private void handleLuuButton() {
        try {
            // Lấy thông tin cần thiết từ view
            String tenSach = (String) view.getSachTable().getValueAt(view.getSachTable().getSelectedRow(), 0);
            int donGia = (Integer) view.getSachTable().getValueAt(view.getSachTable().getSelectedRow(), 3);
            int tonKho = (Integer) view.getSachTable().getValueAt(view.getSachTable().getSelectedRow(), 4);
            int soLuong = Integer.parseInt(view.getTxtSoLuong().getText());

            // Kiểm tra điều kiện: số lượng phải lớn hơn 0 và không vượt quá tồn kho
            if (soLuong <= 0) {
                javax.swing.JOptionPane.showMessageDialog(view, "Số lượng phải là số dương!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (soLuong > tonKho) {
                javax.swing.JOptionPane.showMessageDialog(view, "Số lượng không được lớn hơn tồn kho!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Tính thành tiền
            int thanhTien = soLuong * donGia;

            // Lấy model của bảng SachHoadonTable
            DefaultTableModel hoaDonTableModel = (DefaultTableModel) view.getSachHoadonTable().getModel();

            // Thêm dữ liệu vào bảng
            hoaDonTableModel.addRow(new Object[]{
                tenSach, donGia, soLuong, thanhTien
            });

            // Đóng JDialog
            view.getNhapSoLuong().dispose();

            // Cập nhật tổng tiền
            updateTongTien();

            // Reset lại ô nhập số lượng
            

        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng nhập số lượng hợp lệ!", "Lỗi", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleXoaButton() {
    // Lấy bảng hóa đơn
    DefaultTableModel hoaDonTableModel = (DefaultTableModel) view.getSachHoadonTable().getModel();

    // Lấy chỉ số hàng được chọn
    int selectedRow = view.getSachHoadonTable().getSelectedRow();

    if (selectedRow >= 0) {
        // Hiển thị hộp thoại xác nhận
        int confirmation = javax.swing.JOptionPane.showConfirmDialog(
            view,
            "Bạn có chắc chắn muốn xóa dòng này?",
            "Xác nhận xóa",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.WARNING_MESSAGE
        );

        // Nếu người dùng chọn "Yes", tiến hành xóa
        if (confirmation == javax.swing.JOptionPane.YES_OPTION) {
            // Xóa hàng khỏi model
            hoaDonTableModel.removeRow(selectedRow);

            // Cập nhật lại bảng
            view.getSachHoadonTable().revalidate();
            view.getSachHoadonTable().repaint();
            // Cập nhật tổng tiền
        updateTongTien();
        }
    } else {
        // Thông báo nếu không có hàng nào được chọn
        javax.swing.JOptionPane.showMessageDialog(view, "Vui lòng chọn hàng để xóa!", "Thông báo", javax.swing.JOptionPane.WARNING_MESSAGE);
    }
}


    

    @Override
    public void importFromExcel(File excelFile) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void exportFromExcel(File destinationFile) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
