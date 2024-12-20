/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.quanlinhasach.view;

/**
 *
 * @author TienDat
 */
public class QuanLiNhanVien extends javax.swing.JFrame {

    /**
     * Creates new form QuanLiNhanVien
     */
    public QuanLiNhanVien() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        menuIconLabel = new javax.swing.JLabel();
        userHeaderPanel = new javax.swing.JPanel();
        userIconLabel = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();
        bodyPanel = new javax.swing.JPanel();
        functionPanel = new javax.swing.JPanel();
        countUserLabel = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        downloadBtn = new javax.swing.JButton();
        uploadBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        nhanVienScrollPane = new javax.swing.JScrollPane();
        nhanVienTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        headerPanel.setBackground(new java.awt.Color(132, 94, 194));
        headerPanel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        headerPanel.setPreferredSize(new java.awt.Dimension(1080, 45));

        menuIconLabel.setForeground(new java.awt.Color(255, 255, 255));
        menuIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/img/list.png"))); // NOI18N
        menuIconLabel.setInheritsPopupMenu(false);

        userHeaderPanel.setBackground(new java.awt.Color(132, 94, 194));
        userHeaderPanel.setPreferredSize(new java.awt.Dimension(154, 45));

        userIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/img/contact.png"))); // NOI18N

        userNameLabel.setBackground(new java.awt.Color(132, 94, 194));
        userNameLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        userNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        userNameLabel.setText("Trần Nguyễn Bảo Hoàng");
        userNameLabel.setAlignmentY(0.0F);

        javax.swing.GroupLayout userHeaderPanelLayout = new javax.swing.GroupLayout(userHeaderPanel);
        userHeaderPanel.setLayout(userHeaderPanelLayout);
        userHeaderPanelLayout.setHorizontalGroup(
            userHeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userHeaderPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userNameLabel)
                .addGap(5, 5, 5)
                .addComponent(userIconLabel)
                .addGap(0, 0, 0))
        );
        userHeaderPanelLayout.setVerticalGroup(
            userHeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userHeaderPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(userHeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(userHeaderPanelLayout.createSequentialGroup()
                        .addComponent(userIconLabel)
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(menuIconLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userHeaderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(menuIconLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addComponent(userHeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bodyPanel.setBackground(new java.awt.Color(255, 255, 255));
        bodyPanel.setPreferredSize(new java.awt.Dimension(1080, 723));

        functionPanel.setBackground(new java.awt.Color(255, 255, 255));
        functionPanel.setOpaque(false);
        functionPanel.setPreferredSize(new java.awt.Dimension(1032, 44));
        functionPanel.setRequestFocusEnabled(false);

        countUserLabel.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        countUserLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        countUserLabel.setText("Tổng nhân viên: 0");
        countUserLabel.setAlignmentY(0.0F);
        countUserLabel.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        countUserLabel.setPreferredSize(new java.awt.Dimension(129, 44));

        searchTextField.setBackground(new java.awt.Color(251, 234, 255));
        searchTextField.setColumns(20);
        searchTextField.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        searchTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchTextField.setToolTipText("");
        searchTextField.setRequestFocusEnabled(false);
        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });

        searchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/img/search-interface-symbol.png"))); // NOI18N
        searchBtn.setBorder(null);
        searchBtn.setBorderPainted(false);
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        downloadBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/img/download-to-storage-drive.png"))); // NOI18N
        downloadBtn.setBorderPainted(false);

        uploadBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/img/upload.png"))); // NOI18N
        uploadBtn.setBorderPainted(false);

        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/img/addition.png"))); // NOI18N
        addBtn.setBorderPainted(false);

        javax.swing.GroupLayout functionPanelLayout = new javax.swing.GroupLayout(functionPanel);
        functionPanel.setLayout(functionPanelLayout);
        functionPanelLayout.setHorizontalGroup(
            functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(functionPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(countUserLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addGap(407, 407, 407)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(searchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(addBtn)
                .addGap(20, 20, 20)
                .addComponent(downloadBtn)
                .addGap(20, 20, 20)
                .addComponent(uploadBtn))
        );
        functionPanelLayout.setVerticalGroup(
            functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(functionPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(uploadBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(downloadBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(functionPanelLayout.createSequentialGroup()
                .addGroup(functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(searchTextField)
                    .addComponent(countUserLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        nhanVienScrollPane.setColumnHeaderView(null);
        nhanVienScrollPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        nhanVienTable.setAutoCreateRowSorter(true);
        nhanVienTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Họ và tên", "Giới tính", "Ngày sinh", "Email", "Số điện thoại", "Tổng chi tiêu", "Nợ", "Chú ý", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        nhanVienTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nhanVienTable.setEnabled(false);
        nhanVienTable.setRowHeight(44);
        nhanVienTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        nhanVienTable.setShowGrid(true);
        nhanVienScrollPane.setViewportView(nhanVienTable);
        nhanVienTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (nhanVienTable.getColumnModel().getColumnCount() > 0) {
            nhanVienTable.getColumnModel().getColumn(0).setResizable(false);
            nhanVienTable.getColumnModel().getColumn(0).setPreferredWidth(44);
            nhanVienTable.getColumnModel().getColumn(1).setResizable(false);
            nhanVienTable.getColumnModel().getColumn(1).setPreferredWidth(160);
            nhanVienTable.getColumnModel().getColumn(2).setResizable(false);
            nhanVienTable.getColumnModel().getColumn(2).setPreferredWidth(64);
            nhanVienTable.getColumnModel().getColumn(3).setResizable(false);
            nhanVienTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            nhanVienTable.getColumnModel().getColumn(4).setResizable(false);
            nhanVienTable.getColumnModel().getColumn(4).setPreferredWidth(168);
            nhanVienTable.getColumnModel().getColumn(5).setResizable(false);
            nhanVienTable.getColumnModel().getColumn(5).setPreferredWidth(96);
            nhanVienTable.getColumnModel().getColumn(6).setResizable(false);
            nhanVienTable.getColumnModel().getColumn(6).setPreferredWidth(90);
            nhanVienTable.getColumnModel().getColumn(7).setResizable(false);
            nhanVienTable.getColumnModel().getColumn(7).setPreferredWidth(90);
            nhanVienTable.getColumnModel().getColumn(8).setResizable(false);
            nhanVienTable.getColumnModel().getColumn(8).setPreferredWidth(80);
            nhanVienTable.getColumnModel().getColumn(9).setResizable(false);
            nhanVienTable.getColumnModel().getColumn(9).setPreferredWidth(60);
        }

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nhanVienScrollPane)
                    .addComponent(functionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(functionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(nhanVienScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bodyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bodyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 702, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLiNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLiNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLiNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLiNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLiNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JLabel countUserLabel;
    private javax.swing.JButton downloadBtn;
    private javax.swing.JPanel functionPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel menuIconLabel;
    private javax.swing.JScrollPane nhanVienScrollPane;
    private javax.swing.JTable nhanVienTable;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JButton uploadBtn;
    private javax.swing.JPanel userHeaderPanel;
    private javax.swing.JLabel userIconLabel;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}
