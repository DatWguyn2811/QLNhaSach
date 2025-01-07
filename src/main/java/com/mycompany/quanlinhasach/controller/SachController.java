/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.controller;

import com.mycompany.quanlinhasach.dao.CT_PhieuNhapSachDAO;
import com.mycompany.quanlinhasach.dao.PhieuNhapSachDAO;
import com.mycompany.quanlinhasach.dao.QuyDinhDAO;
import com.mycompany.quanlinhasach.dao.SachDAO;
import com.mycompany.quanlinhasach.model.CT_PhieuNhapSach;
import com.mycompany.quanlinhasach.model.PhieuNhapSach;
import com.mycompany.quanlinhasach.model.QuyDinh;
import com.mycompany.quanlinhasach.model.Sach;
import com.mycompany.quanlinhasach.view.SanPham;


import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class SachController implements ControllerInterface {

    private SanPham view;
    private SachDAO sach1DAO;
    private PhieuNhapSachDAO phieuNhapSachDAO;
    private CT_PhieuNhapSachDAO ctPhieuNhapSachDAO;

    public SanPham getView() {
        return view;
    }

    public SachController() {

    }

    public SachController(SanPham view, SachDAO dao, PhieuNhapSachDAO phieuNhapSachDAO, CT_PhieuNhapSachDAO ctPhieuNhapSachDAO) {
        this.view = view;
        this.sach1DAO = dao;
        this.phieuNhapSachDAO = phieuNhapSachDAO;
        this.ctPhieuNhapSachDAO = ctPhieuNhapSachDAO;

        loadTableData();
        updateLuuBtnState();
        updateTongSachLabel();

        //Nhập sách
        view.getNhapSachBtn().addActionListener(e -> {
            try {
                // Lấy dữ liệu từ bảng NhapTable
                DefaultTableModel model = (DefaultTableModel) view.getNhapTable().getModel();

                if (model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(view, "Không có sách nào để nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Tạo phiếu nhập sách mới
                PhieuNhapSach phieuNhapSach = new PhieuNhapSach();
                phieuNhapSach.setNgayNhap(java.time.LocalDate.now().toString()); // Ngày nhập là ngày hiện tại// Lấy mã nhân viên từ giao diện
                phieuNhapSach.setTongTien(0); // Tạm thời gán tổng tiền = 0, sẽ tính sau

                // Thêm phiếu nhập sách và lấy mã phiếu nhập
                if (!phieuNhapSachDAO.insert(phieuNhapSach)) {
                    JOptionPane.showMessageDialog(view, "Không thể thêm phiếu nhập sách!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int maPhieuNhap = phieuNhapSach.getMaPhieuNhapSach(); // Lấy mã phiếu nhập đã được tự động sinh
                int tongTien = 0; // Biến lưu tổng tiền

                // Lặp qua từng dòng trong bảng để thêm vào CT_PHIEUNHAPSACH
                for (int i = 0; i < model.getRowCount(); i++) {
                    String tenSach = (String) model.getValueAt(i, 0);
                    String nhaXuatBan = (String) model.getValueAt(i, 2);
                    int namXuatBan = (int) model.getValueAt(i, 3);
                    int soLuongNhap = (int) model.getValueAt(i, 4);
                    int donGiaNhap = (int) model.getValueAt(i, 5);

                    // Lấy MaSach từ cơ sở dữ liệu
                    int maSach = sach1DAO.layMaSach(tenSach, nhaXuatBan, namXuatBan);
                    if (maSach == -1) {
                        JOptionPane.showMessageDialog(view, "Không tìm thấy sách: " + tenSach, "Lỗi", JOptionPane.ERROR_MESSAGE);
                        continue;
                    }

                    // Tính thành tiền
                    int thanhTien = soLuongNhap * donGiaNhap;
                    tongTien += thanhTien;

                    // Thêm chi tiết phiếu nhập sách
                    CT_PhieuNhapSach ctPhieuNhapSach = new CT_PhieuNhapSach();
                    ctPhieuNhapSach.setMaPhieuNhapSach(maPhieuNhap);
                    ctPhieuNhapSach.setMaSach(maSach);
                    ctPhieuNhapSach.setSoLuongNhap(soLuongNhap);
                    ctPhieuNhapSach.setDonGiaNhap(donGiaNhap);
                    ctPhieuNhapSach.setThanhTien(thanhTien);

                    if (!ctPhieuNhapSachDAO.insert(ctPhieuNhapSach)) {
                        JOptionPane.showMessageDialog(view, "Không thể thêm chi tiết phiếu nhập cho sách: " + tenSach, "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                    QuyDinh qd = new QuyDinh();
                    //phieuNhapSachDAO.capNhatDonGiaBan(maPhieuNhap, donGiaNhap, qd.getHeSoGiaBan());
                }
                
                // Cập nhật tổng tiền trong phiếu nhập sách
               
                phieuNhapSach.setTongTien(tongTien);
                phieuNhapSachDAO.capNhatTongTienPhieuNhap(maPhieuNhap, tongTien);
                

                JOptionPane.showMessageDialog(view, "Nhập sách thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                // Xóa dữ liệu trong bảng sau khi nhập thành công
                model.setRowCount(0);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "Có lỗi xảy ra: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            loadTableData();

        });

        // Lắng nghe sự thay đổi trong bảng table1
        view.getTable1().getModel().addTableModelListener(e -> updateLuuBtnState());
        // Đăng ký ActionListener cho nút "Lưu"
        view.getThoatBtn().addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) view.getTable1().getModel();
            model.setRowCount(0);  // Xóa hết các dòng trong table1
            resetInputFields();
        });
        view.getThoatNhapKhoBtn().addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) view.getNhapTable().getModel();
            model.setRowCount(0);  // Xóa hết các dòng trong table1
            resetInputFields();
        });
        view.getLuuBtn().addActionListener(e -> saveData());
        view.getThemBtn1().addActionListener(e -> {
            try {
                String tenSach = (String) view.getDauSachCmb1().getSelectedItem();
                String nhaXuatBan = (String) view.getNhaXuatBanCmb1().getSelectedItem();
                int namXuatBan = view.getNamXBchooser1().getYear();
 

                // Gọi controller để kiểm tra và thêm sách
                kiemTraVaThemSach1(tenSach, nhaXuatBan, namXuatBan, 0, 0);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng và đủ định dạng dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Lắng nghe sự kiện khi nhấn nút "Edit"
        view.getEditBtn().addActionListener(e -> {
            int selectedRow = view.getSachTable().getSelectedRow();  // Lấy hàng được chọn trong bảng
            if (selectedRow != -1) {
                // Lấy dữ liệu từ dòng đã chọn

                String tenSach = (String) view.getSachTable().getValueAt(selectedRow, 1);
                String tacGia = (String) view.getSachTable().getValueAt(selectedRow, 2);
                String nhaXuatBan = (String) view.getSachTable().getValueAt(selectedRow, 3);
                int namXuatBan = (int) view.getSachTable().getValueAt(selectedRow, 4);
                int donGia = (int) view.getSachTable().getValueAt(selectedRow, 5);
                int soLuong = (int) view.getSachTable().getValueAt(selectedRow, 6);

                // Lấy đối tượng JDialog từ phương thức getCapNhatSach()
                JDialog capNhatSachDialog = view.getCapNhatSach();  // CapNhatSach là JDialog đã có trong SanPham

                // Cập nhật các trường trong CapNhatSach
                view.getSuaTenDauSachCmb().setSelectedItem(tenSach);  // Cập nhật JComboBox Tên Đầu Sách
                view.getSuaTacGiaCmb().setText(String.valueOf(tacGia));  // Cập nhật JComboBox Tác Giả
                view.getSuaNXBCmb().setSelectedItem(nhaXuatBan);  // Cập nhật JComboBox Nhà Xuất Bản
                view.getSuaNamXBChooser().setValue(namXuatBan);  // Cập nhật JSpinner Năm Xuất Bản
                view.getSuaDonGiatxt().setText(String.valueOf(donGia));  // Cập nhật JTextField Đơn Giá
                view.getSuaSoLuongtxt().setText(String.valueOf(soLuong));

                // Hiển thị dialog
                view.getCapNhatSach().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn sách cần chỉnh sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });
        // Xử lý sự kiện nhấn nút SuaLuuBtn
        view.getSuaLuuBtn().addActionListener(e -> {
            int selectedRow = view.getSachTable().getSelectedRow();
            // Lấy dữ liệu từ các trường nhập vào

            String tenSach = view.getSuaTenDauSachCmb().getSelectedItem().toString();
            String tacGia = view.getSuaTacGiaCmb().toString();
            String nhaXuatBan = view.getSuaNXBCmb().getSelectedItem().toString();
            int namXuatBan = (int) view.getSuaNamXBChooser().getValue();
            int donGia = Integer.parseInt(view.getSuaDonGiatxt().getText());
            int soLuong = Integer.parseInt(view.getSuaSoLuongtxt().getText());
            int maSach = (int) view.getSachTable().getValueAt(selectedRow, 0);

            // Tạo đối tượng Sach với thông tin đã nhập
            Sach sach = new Sach();
            sach.setMaSach(maSach);
            sach.setTenSach(tenSach);
            sach.setTenTacGia(tacGia);
            sach.setTenNXB(nhaXuatBan);
            sach.setNamXuatBan(namXuatBan);
            sach.setDonGia(donGia);
            sach.setSoLuong(soLuong);

            // Cập nhật thông tin sách vào cơ sở dữ liệu
            update(sach);// Gọi phương thức update trong DAO để thực hiện cập nhật
            loadTableData();

            view.getCapNhatSach().setVisible(false);
        });

        // Button Xoá sách
        // Nút Xóa
        // Thêm vào bảng để nhập kho
        view.getThemBtn().addActionListener(e -> {
            try {
                
                // Lấy dữ liệu QuyĐịnh từ cơ sở dữ liệu
                QuyDinh qd = QuyDinhDAO.getInstance().selectQuyDinh();
                String tenSach = (String) view.getDauSachCmb().getSelectedItem();
                String nhaXuatBan = (String) view.getNhaXuatBanCmb().getSelectedItem();
                int namXuatBan = view.getNamXBchooser().getYear();
                int soLuong = Integer.parseInt(view.getTxtSoLuong().getText());
                int donGia = Integer.parseInt(view.getTxtDonGia().getText());
                String ngayNhap = new SimpleDateFormat("yyyy-MM-dd").format(view.getNgayNhapChooser().getDate());
                int slTon = sach1DAO.laySoLuongTon(sach1DAO.layMaSach(tenSach, nhaXuatBan, namXuatBan));
                // Kiểm tra số lượng
                System.out.print(qd.getSoLuongNhapToiThieu());
                if (soLuong < qd.getSoLuongNhapToiThieu()) {
                    JOptionPane.showMessageDialog(null,
                            "Không được nhập sách với số lượng dưới " + qd.getSoLuongNhapToiThieu() + "!",
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return; // Dừng thực thi nếu điều kiện không thỏa mãn
                }
                if (slTon > qd.getSoLuongTonToiThieuDeNhap()) {
                    JOptionPane.showMessageDialog(null,
                            "Không nhập sách có lượng tồn lớn hơn " + qd.getSoLuongTonToiThieuDeNhap() + "!",
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return; // Dừng thực thi nếu điều kiện không thỏa mãn
                }
                
                
               
                // Gọi controller để kiểm tra và thêm sách
                kiemTraVaThemVaoBangNhapSach(tenSach, nhaXuatBan, namXuatBan, soLuong, donGia, ngayNhap);

                view.getDauSachCmb().setSelectedIndex(0);  // Đặt về giá trị đầu tiên
                view.getNhaXuatBanCmb().setSelectedIndex(0);
                view.getNamXBchooser().setYear(Calendar.getInstance().get(Calendar.YEAR)); // Đặt về năm hiện tại
                view.getTxtSoLuong().setText(""); // Xóa nội dung số lượng
                view.getTxtDonGia().setText("");  // Xóa nội dung đơn giá
                view.getNgayNhapChooser().setDate(null); // Để trống trường ngày nhập
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng và đủ định dạng dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(SachController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

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
                        defaultTableModel.addRow(new Object[]{
                            s.getMaSach(),
                            s.getTenSach(),
                            s.getTenTacGia(),
                            s.getTenNXB(),
                            s.getNamXuatBan(),
                            s.getDonGia(),
                            s.getSoLuong(),});
                    }
                }

            }
        });
        view.getXoaBtn().addActionListener(e -> {
            int selectedRow = view.getSachTable().getSelectedRow();  // Lấy hàng được chọn trong bảng
            if (selectedRow != -1) {
                int maSach = (int) view.getSachTable().getValueAt(selectedRow, 0);

                // Xác nhận
                int confirm = JOptionPane.showConfirmDialog(
                        view,
                        "Chắc chắn muốn xoá sách này không?",
                        "Xác nhận xoá",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean isDeleted = sach1DAO.delete(maSach);

                    if (isDeleted) {
                        JOptionPane.showMessageDialog(
                                view,
                                "Xoá sách thành công",
                                "Thông báo",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        // Tải lại bảng chỉ khi xóa thành công
                        loadTableData();

                    } else {
                        JOptionPane.showMessageDialog(
                                view,
                                "Xoá sách thất bại",
                                "Lỗi",
                                JOptionPane.ERROR_MESSAGE
                        );

                    }

                    // Đảm bảo rằng giao diện CapNhatSach không được hiển thị sau khi xóa
                } else {
                }
                view.getCapNhatSach().setVisible(false);

            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn sách để xoá", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

    }

    public void updateTongSachLabel() {
        try {
            JTable sachTable = view.getSachTable(); // Lấy JTable từ view
            JLabel lblTongSach = view.getlblTongSach(); // Lấy JLabel để hiển thị tổng số dòng

            // Đếm số dòng hiện tại trong bảng
            int totalRows = sachTable.getRowCount();

            // Hiển thị số dòng trên label
            lblTongSach.setText(String.valueOf(totalRows));
        } catch (Exception ex) {
            ex.printStackTrace();
            view.getlblTongSach().setText("Lỗi: " + ex.getMessage());
        }
    }

    // Hàm kiểm tra trạng thái nút "Lưu"
    private void updateLuuBtnState() {
        DefaultTableModel model = (DefaultTableModel) view.getTable1().getModel();
        if (model.getRowCount() == 0) {
            view.getLuuBtn().setEnabled(false); // Vô hiệu hóa nút "Lưu"
        } else {
            view.getLuuBtn().setEnabled(true);  // Kích hoạt nút "Lưu"
        }
    }

    public void kiemTraVaThemVaoBangNhapSach(String tenSach, String nhaXuatBan, int namXuatBan, int soLuong, int donGia, String ngayNhap) throws SQLException {
        try {
            // Kiểm tra nếu sách đã tồn tại trong cơ sở dữ liệu
            if (!sach1DAO.kiemTraSachTonTai(tenSach, nhaXuatBan, namXuatBan)) {
                JOptionPane.showMessageDialog(null, "Sách không tồn tại trong cơ sở dữ liệu! Không thể nhập.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Nếu sách tồn tại, thực hiện nhập sách
            DefaultTableModel model = (DefaultTableModel) view.getNhapTable().getModel();

            // Kiểm tra xem sách đã tồn tại trong bảng hiển thị chưa
            boolean isExistingInTable = false;
            for (int i = 0; i < model.getRowCount(); i++) {
                String existingTenSach = (String) model.getValueAt(i, 0);
                String existingNhaXuatBan = (String) model.getValueAt(i, 3);
                int existingNamXuatBan = (int) model.getValueAt(i, 4);

                if (existingTenSach.equals(tenSach)
                        && existingNhaXuatBan.equals(nhaXuatBan)
                        && existingNamXuatBan == namXuatBan) {
                    isExistingInTable = true;

                    // Cập nhật số lượng nếu sách đã tồn tại trong bảng
                    int currentSoLuong = (int) model.getValueAt(i, 5);
                    model.setValueAt(currentSoLuong + soLuong, i, 5);
                    break;
                }
            }

            // Nếu sách không tồn tại trong bảng, thêm mới
            if (!isExistingInTable) {
                String tacGia = sach1DAO.layTenTacGia(tenSach);

                model.addRow(new Object[]{tenSach, tacGia, nhaXuatBan, namXuatBan, soLuong, donGia, ngayNhap});
            }

            JOptionPane.showMessageDialog(null, "Thêm sách thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void kiemTraVaThemSach1(String tenSach, String nhaXuatBan, int namXuatBan, int soLuong, int donGia) throws SQLException {
        try {
            // Lấy tên tác giả và thể loại từ DAO
            String tacGia = sach1DAO.layTenTacGia(tenSach);
            String theLoai = sach1DAO.layTenTheLoai(tenSach);

            // Log thông tin về sách trước khi kiểm tra và thêm
            System.out.println("Kiểm tra sách: Tên Sách = " + tenSach + ", Tên Tác Giả = " + tacGia + ", Tên Thể Loại = " + theLoai
                    + ", Nhà Xuất Bản = " + nhaXuatBan + ", Năm Xuất Bản = " + namXuatBan + ", Số Lượng = " + soLuong + ", Đơn Giá = " + donGia);

            // Kiểm tra nếu sách đã tồn tại trong cơ sở dữ liệu
            if (sach1DAO.kiemTraSachTonTai(tenSach, nhaXuatBan, namXuatBan)) {
                JOptionPane.showMessageDialog(null, "Sách đã tồn tại trong cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra nếu sách đã tồn tại trong bảng hiển thị
            DefaultTableModel model = (DefaultTableModel) view.getTable1().getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                String existingTenSach = (String) model.getValueAt(i, 0);
                String existingNhaXuatBan = (String) model.getValueAt(i, 3);
                int existingNamXuatBan = (int) model.getValueAt(i, 4);

                // Kiểm tra trùng lặp
                if (existingTenSach.equals(tenSach)
                        && existingNhaXuatBan.equals(nhaXuatBan)
                        && existingNamXuatBan == namXuatBan) {
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

    // Phương thức lưu dữ liệu khi nhấn nút "Lưu"
    private void saveData() {
        // Lấy danh sách sách từ giao diện (ví dụ từ một form hoặc bảng)
        ArrayList<Sach> sachList = getSachListFromView();

        // Gọi phương thức add với đối tượng sachList
        add(sachList);
        // Sau khi lưu, tải lại dữ liệu từ cơ sở dữ liệu
        loadTableData();

        // Xóa các dòng trong table1
        DefaultTableModel model = (DefaultTableModel) view.getTable1().getModel();
        model.setRowCount(0);  // Xóa hết các dòng trong table1
        // Reset các trường nhập liệu
        resetInputFields();
    }

    // Phương thức reset các trường nhập liệu
    private void resetInputFields() {
        // Reset combo box và chọn giá trị mặc định
        view.getDauSachCmb1().setSelectedIndex(0);  // Đặt lại chỉ số combo box (chọn giá trị mặc định đầu tiên)
        view.getNhaXuatBanCmb1().setSelectedIndex(0); // Tương tự với combo box nhà xuất bản

        // Reset năm xuất bản (nếu sử dụng JSpinner)
        view.getNamXBchooser1().setYear(2024); // Đặt lại năm xuất bản (hoặc giá trị mặc định của bạn)

        // Reset giá trị nhập vào cho text field
        view.getTxtDonGia1().setText(""); // Xóa nội dung trong trường nhập đơn giá
    }

    @Override
    public void add(Object t) {
        // Ép kiểu đối tượng t về kiểu ArrayList<Sach>
        if (t instanceof ArrayList<?>) {
            ArrayList<Sach> sachList = (ArrayList<Sach>) t;

            try {
                // Gọi phương thức insert trong SachDAO
                SachDAO dao = SachDAO.getInstance();
                boolean result = dao.insert(sachList);

                // Kiểm tra kết quả và in thông báo
                if (result) {
                    System.out.println("Thêm sách thành công!");
                    JOptionPane.showMessageDialog(view, "Thêm sách thành công!");
                } else {
                    System.out.println("Thêm sách thất bại!");
                    JOptionPane.showMessageDialog(view, "Thêm sách thất bại!");
                }
            } catch (SQLException e) {
                // Xử lý ngoại lệ SQL
                System.err.println("Lỗi khi thêm sách: " + e.getMessage());
                JOptionPane.showMessageDialog(view, "Lỗi khi thêm sách: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Xử lý nếu đối tượng truyền vào không phải ArrayList<Sach>
            System.err.println("Đối tượng truyền vào không hợp lệ. Vui lòng truyền vào ArrayList<Sach>.");
            JOptionPane.showMessageDialog(view, "Đối tượng truyền vào không hợp lệ. Vui lòng truyền vào ArrayList<Sach>.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ArrayList<Sach> getSachListFromView() {
        ArrayList<Sach> sachList = new ArrayList<>();

        // Lấy dữ liệu từ JTable
        DefaultTableModel model = (DefaultTableModel) view.getTable1().getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            // Cột 0 là maSach
            String tenSach = (String) model.getValueAt(i, 0); // Cột 1 là tenSach
            String tenTacGia = (String) model.getValueAt(i, 1);
            String tenNXB = (String) model.getValueAt(i, 3); // Cột 3 là tenNXB
            int namXuatBan = (int) model.getValueAt(i, 4); // Cột 4 là namXuatBan
            int soLuong = (int) model.getValueAt(i, 5); // Cột 6 là soLuong
            int donGia = (int) model.getValueAt(i, 6); // Cột 5 là donGia

            // Tạo đối tượng Sach và thêm vào danh sách
            Sach sach = new Sach(tenSach, tenTacGia, tenNXB, namXuatBan, 0, donGia);
            sachList.add(sach);
        }
        return sachList;
    }

    @Override
    public void update(Object t) {
        // Kiểm tra nếu đối tượng là kiểu Sach
        if (t instanceof Sach) {
            Sach sach = (Sach) t;
            try {
                // Gọi phương thức update của DAO để cập nhật thông tin
                int rowsAffected = sach1DAO.update(sach);
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(view, "Cập nhật sách thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(view, "Không thể cập nhật sách. Vui lòng kiểm tra lại thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                // Bắt các lỗi khác không phải SQLException
                JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();  // In chi tiết lỗi ra console để debug
            }
            // Bắt lỗi SQLException và hiển thị chi tiết
            // In chi tiết lỗi SQL ra console

        }
    }

    @Override
    public void delete(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void loadTableData() {
        DefaultTableModel defaultTableModel = new DefaultTableModel(
                new Object[][]{},
                new Object[]{
                    "ID", "Tên sách", "Tên tác giả", "NXB", "Năm XB", "Đơn giá", "Số lượng"
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
                sach.getMaSach(),
                sach.getTenSach(),
                sach.getTenTacGia(),
                sach.getTenNXB(),
                sach.getNamXuatBan(),
                sach.getDonGia(),
                sach.getSoLuong()
            });
        }

        view.getSachTable().setModel(defaultTableModel);
        updateTongSachLabel();
    }


    @Override
    public void importFromExcel(File excelFile) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void exportFromExcel(File destinationFile) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setTableColumnWidths(JTable table) {
        // Lấy TableColumnModel từ JTable
        TableColumnModel columnModel = table.getColumnModel();

        // Đặt độ rộng cho từng cột
        columnModel.getColumn(0).setPreferredWidth(30);  // Cột "ID"
        columnModel.getColumn(1).setPreferredWidth(150); // Cột "Họ và tên"
        columnModel.getColumn(1).setPreferredWidth(110);
        columnModel.getColumn(2).setPreferredWidth(150); // Cột "Số điện thoại"
        columnModel.getColumn(3).setPreferredWidth(40); // Cột "Email"
        columnModel.getColumn(4).setPreferredWidth(120); // Cột "Giới tính"
        columnModel.getColumn(5).setPreferredWidth(30); // Cột "Ngày sinh"
    }

}
