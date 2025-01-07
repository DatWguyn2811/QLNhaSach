/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.controller;


import com.mycompany.quanlinhasach.dao.DauSachDAO;
import com.mycompany.quanlinhasach.dao.SachDAO;
import com.mycompany.quanlinhasach.dao.TacGiaDAO;
import com.mycompany.quanlinhasach.dao.TheLoaiDAO;
import com.mycompany.quanlinhasach.model.DauSachModel;
import com.mycompany.quanlinhasach.model.TacGia;
import com.mycompany.quanlinhasach.model.TheLoai;
import com.mycompany.quanlinhasach.view.DauSach;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class DauSachController implements ControllerInterface{
    private DauSach view;
    private DauSachDAO dsDAO;
    private SachDAO sachDAO;
    
    public DauSach getView(){
        return view;
    }
    public DauSachController(){
        
    }
    public DauSachController(DauSach view, DauSachDAO dao, SachDAO sachdao){
        this.view=view;
        this.dsDAO=dao;
        this.sachDAO=sachdao;
        
        loadTableData();
        loadTheLoaiToComboBox();
        loadTacGiaToComboBox();
        loadThemTableData();
        updateTongDauSachLabel();
        view.getThemDauSachBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Cập nhật thông tin thanh toán
                view.getThemDauSach().setVisible(true);
            }
        });
        view.getThoatThemBtn().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Cập nhật thông tin thanh toán
                view.getThemDauSach().setVisible(false);
            }
        });
        view.getThemHangBtn().addActionListener(e -> {
            try {
                // Lấy giá trị từ JTextField và JComboBox
                String tenDauSach = view.getTenDsTxy().getText().trim();
                String tenTacGia = (String) view.getTacGiaCmb().getSelectedItem();
                String tenTheLoai = (String) view.getTheLoaiCmb().getSelectedItem();

                // Kiểm tra giá trị rỗng
                if (tenDauSach.isEmpty() || tenTacGia == null || tenTheLoai == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Gọi controller để kiểm tra và thêm sách
                kiemTraVaThemDauSach(tenDauSach, tenTacGia, tenTheLoai);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng và đủ định dạng dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        view.getLuuThemBtn().addActionListener(e -> saveData());
           
        view.getCapnhatDauSachBtn().addActionListener(e -> {
            int selectedRow = view.getDauSachTable().getSelectedRow();  // Lấy hàng được chọn trong bảng
            if (selectedRow != -1) {
                // Lấy dữ liệu từ dòng đã chọn

                String tenDauSach = (String) view.getDauSachTable().getValueAt(selectedRow, 1);
                String tacGia = (String) view.getDauSachTable().getValueAt(selectedRow, 2);
                String theLoai = (String) view.getDauSachTable().getValueAt(selectedRow, 3);



                // Cập nhật các trường trong CapNhatSach
                view.getTacGiaCNCmb().setSelectedItem(tacGia);  // Cập nhật JComboBox Tên Đầu Sách
                view.getTheLoaiCNCmb().setSelectedItem(theLoai);  // Cập nhật JComboBox Tác Giả
                view.getTenDStxt().setText(String.valueOf(tenDauSach));  // Cập nhật JTextField Đơn Giá


                // Hiển thị dialog
                view.getCapNhatDauSach().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn sách cần chỉnh sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        view.getLuuCNBtn().addActionListener(e -> {
            int selectedRow = view.getDauSachTable().getSelectedRow();
            // Lấy dữ liệu từ các trường nhập vào

            int maDauSach = Integer.parseInt(view.getDauSachTable().getValueAt(selectedRow, 0).toString()); // Cột 0 chứa MaDauSach
            String tenDauSach = view.getTenDStxt().getText().trim();
            String tacGia = view.getTacGiaCNCmb().getSelectedItem().toString();
            String theLoai = view.getTheLoaiCNCmb().getSelectedItem().toString();

            // Tạo đối tượng với đầy đủ thông tin
            DauSachModel sach = new DauSachModel(maDauSach, tenDauSach, theLoai, tacGia);

            // Gọi phương thức cập nhật
            update(sach);

            loadTableData();
            view.getCapNhatDauSach().setVisible(false);
        });
        
        view.getXoaBtn().addActionListener(e -> {
            int selectedRow = view.getDauSachTable().getSelectedRow();  // Lấy hàng được chọn trong bảng
            if (selectedRow != -1) {
                int maDauSach = (int) view.getDauSachTable().getValueAt(selectedRow, 0);

                // Xác nhận
                int confirm = JOptionPane.showConfirmDialog(
                    view,
                    "Chắc chắn muốn xoá sách này không?",
                    "Xác nhận xoá",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean isDeleted = dsDAO.delete(maDauSach);

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
                } 
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn sách để xoá", "Thông báo", JOptionPane.WARNING_MESSAGE);
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
                ArrayList<DauSachModel> sachList = dsDAO.selectAll();
                DefaultTableModel defaultTableModel = (DefaultTableModel) view.getDauSachTable().getModel();
                String searchText = view.getTimKiem().getText().trim().toLowerCase();
                defaultTableModel.setRowCount(0); // Xóa tất cả các hàng
                for (DauSachModel ds : sachList) {

                    if (ds.getTenDauSach().toLowerCase().contains(searchText)) {
                        defaultTableModel.addRow(new Object[]{
                            ds.getMaDauSach(),
                            ds.getTenDauSach(),
                            ds.getTenTheLoai(),});
                    }
                }

            }
        });
    }
    private void saveData() {
        // Lấy danh sách sách từ giao diện (ví dụ từ một form hoặc bảng)
        ArrayList<DauSachModel> sachList = getDauSachListFromView();
        
        // Gọi phương thức add với đối tượng sachList
        add(sachList);
        // Sau khi lưu, tải lại dữ liệu từ cơ sở dữ liệu
        loadTableData();

        // Xóa các dòng trong table1
        DefaultTableModel model = (DefaultTableModel) view.getThemDsTable().getModel();
        model.setRowCount(0);  // Xóa hết các dòng trong table1
        // Reset các trường nhập liệu
        resetInputFields();
    }
    

    private ArrayList<DauSachModel> getDauSachListFromView() {
        ArrayList<DauSachModel> sachList = new ArrayList<>();

        // Lấy dữ liệu từ JTable
        DefaultTableModel model = (DefaultTableModel) view.getThemDsTable().getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            // Lấy dữ liệu từng cột
            String tenDauSach = (String) model.getValueAt(i, 0);
            String tenTacGia = (String) model.getValueAt(i, 1);
            String tenTheLoai = (String) model.getValueAt(i, 2);

            // Tạo đối tượng DauSachModel từ dữ liệu bảng
            DauSachModel dsach = new DauSachModel(tenDauSach,tenTheLoai,tenTacGia);


            // Thêm vào danh sách
            sachList.add(dsach);
        }
        return sachList;
    }

    private void resetInputFields() {
        // Reset giá trị nhập vào cho text field
        view.getTenDsTxy().setText(""); 
        // Reset combo box và chọn giá trị mặc định
        view.getTacGiaCmb().setSelectedIndex(0);  
        view.getTheLoaiCmb().setSelectedIndex(0); 



        
    }
    
    
    @Override
    public void add(Object t) {
        if (t instanceof ArrayList<?>) {
            ArrayList<DauSachModel> sachList = (ArrayList<DauSachModel>) t;

            try {
                // Gọi phương thức insert trong SachDAO
                DauSachDAO dao = DauSachDAO.getInstance();
                boolean result = dao.insert(sachList);

                // Kiểm tra kết quả và in thông báo
                if (result) {
                    System.out.println("Thêm đầu sách thành công!");
                    JOptionPane.showMessageDialog(view, "Thêm đầu sách thành công!");
                } else {
                    System.out.println("Thêm đầu sách thất bại!");
                    JOptionPane.showMessageDialog(view, "Thêm đầu sách thất bại!");
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
    

    @Override
    public void update(Object t) {
            // Kiểm tra nếu đối tượng là kiểu Sach
        if (t instanceof DauSachModel) {
            DauSachModel sach = (DauSachModel) t;
            try {
                // Gọi phương thức update của DAO để cập nhật thông tin
                int rowsAffected = dsDAO.update(sach);
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(view, "Cập nhật đầu sách thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(view, "Không thể cập nhật đầu sách. Vui lòng kiểm tra lại thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }catch (Exception ex) {
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
            "ID", "Tên đầu sách", "Tên tác giả", "Thể loại"
        }
    ) {
        boolean[] canEdit = new boolean[]{
            false, false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    };
    defaultTableModel.setRowCount(0);
     // Gọi phương thức selectAll để lấy danh sách sách từ cơ sở dữ liệu
    ArrayList<com.mycompany.quanlinhasach.model.DauSachModel> sachList = dsDAO.selectAll();
       

    
    // Kiểm tra dữ liệu trả về
    System.out.println("Dữ liệu sách: " + sachList); // Dòng kiểm tra

    for (com.mycompany.quanlinhasach.model.DauSachModel ds : sachList) {
        defaultTableModel.addRow(new Object[]{
            ds.getMaDauSach(),
            ds.getTenDauSach(),
            ds.getTenTacGia(),
            ds.getTenTheLoai()
        });
    }

  
    
    
        view.getDauSachTable().setModel(defaultTableModel);
        updateTongDauSachLabel();
    }
    public void loadThemTableData() {
        DefaultTableModel defaultTableModel = new DefaultTableModel(
        new Object[][]{},
        new Object[]{
            "Tên đầu sách", "Tên tác giả", "Thể loại"
        }
    ) {
        boolean[] canEdit = new boolean[]{
            false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    };
    defaultTableModel.setRowCount(0);
        view.getThemDsTable().setModel(defaultTableModel);
    }
    public void loadTacGiaToComboBox() {
        try {
            // Tạo đối tượng TacGiaDAO
            TacGiaDAO tacGiaDAO = new TacGiaDAO();

            // Lấy danh sách tác giả từ DAO
            List<TacGia> tacGias = tacGiaDAO.selectAll();

            // Tạo DefaultComboBoxModel để gán vào JComboBox
            DefaultComboBoxModel<String> cbbModel = new DefaultComboBoxModel<>();

            // Duyệt qua danh sách tác giả và thêm vào ComboBox
            for (TacGia tacGia : tacGias) {
                cbbModel.addElement(tacGia.getTenTacGia()); // Lấy tên tác giả
            }

            // Gán DefaultComboBoxModel vào JComboBox
            view.getTacGiaCmb().setModel(cbbModel);
            view.getTacGiaCNCmb().setModel(cbbModel);

        } catch (Exception e) {
            e.printStackTrace(); // In lỗi nếu có
        }
    }
    public void loadTheLoaiToComboBox() {
        try {
            // Tạo đối tượng TheLoaiDAO
            TheLoaiDAO theLoaiDAO = new TheLoaiDAO();

            // Lấy danh sách thể loại từ DAO
            List<TheLoai> theLoais = theLoaiDAO.selectAllTheLoai();

            // Tạo DefaultComboBoxModel để gán vào JComboBox
            DefaultComboBoxModel<String> cbbModel = new DefaultComboBoxModel<>();

            // Duyệt qua danh sách thể loại và thêm vào ComboBox
            for (TheLoai theLoai : theLoais) {
                cbbModel.addElement(theLoai.getTenTheLoai()); // Lấy tên thể loại
            }

            // Gán DefaultComboBoxModel vào JComboBox
            view.getTheLoaiCmb().setModel(cbbModel);
            view.getTheLoaiCNCmb().setModel(cbbModel);

        } catch (Exception e) {
            e.printStackTrace(); // In lỗi nếu có
        }
    }
    public void kiemTraVaThemDauSach(String tenDauSach, String tenTacGia, String tenTheLoai) throws SQLException {
        try {
            // Kiểm tra nếu sách đã tồn tại trong cơ sở dữ liệu
            if (sachDAO.kiemTraDauSachTonTai(tenDauSach, tenTacGia, tenTheLoai)) {
                JOptionPane.showMessageDialog(null, "Sách đã tồn tại trong cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra nếu sách đã tồn tại trong bảng hiển thị
            DefaultTableModel model = (DefaultTableModel) view.getThemDsTable().getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                String existingTenDauSach = (String) model.getValueAt(i, 0);
                String existingTenTacGia = (String) model.getValueAt(i, 1);
                String existingTenTheLoai = (String) model.getValueAt(i, 2);

                // Kiểm tra trùng lặp
                if (existingTenDauSach.equalsIgnoreCase(tenDauSach) &&
                    existingTenTacGia.equalsIgnoreCase(tenTacGia) &&
                    existingTenTheLoai.equalsIgnoreCase(tenTheLoai)) {
                    JOptionPane.showMessageDialog(null, "Sách đã tồn tại trong bảng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Nếu sách không tồn tại, thêm một hàng mới vào bảng
            model.addRow(new Object[]{tenDauSach, tenTacGia, tenTheLoai});

            JOptionPane.showMessageDialog(null, "Thêm sách thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void updateTongDauSachLabel() {
        try {
            JTable sachTable = view.getDauSachTable(); // Lấy JTable từ view
            JLabel lblTongDauSach = view.getTongSachLbl(); // Lấy JLabel để hiển thị tổng số dòng

            // Đếm số dòng hiện tại trong bảng
            int totalRows = sachTable.getRowCount();

            // Hiển thị số dòng trên label
            lblTongDauSach.setText(String.valueOf(totalRows));
        } catch (Exception ex) {
            ex.printStackTrace();
            view.getTongSachLbl().setText("Lỗi: " + ex.getMessage());
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
