/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.quanlinhasach.view;

import com.toedter.calendar.JDateChooser;
import javax.accessibility.AccessibleContext;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;

/**
 *
 * @author ASUS
 */
public class ThongTinTaiKhoan extends javax.swing.JPanel {

    private JButton cancelBtn1;

    /**
     * Creates new form ThongTinTaiKhoan
     */
    public ThongTinTaiKhoan() {
        initComponents();
    }

    public JPanel getBirthdateInputPanel() {
        return birthdateInputPanel;
    }

    public JLabel getBirthdateLabel() {
        return birthdateLabel;
    }


    public JPanel getBtnPanel() {
        return btnPanel;
    }

    public JButton getCancelBtn() {
        return changePasswordBtn;
    }

    public JButton getCancelBtn1() {
        JButton cancelBtn1 = null;
        return cancelBtn1;
    }

    public JPanel getEmailInputPanel() {
        return emailInputPanel;
    }

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public JTextField getEmailTxtField() {
        return emailTxtField;
    }

    public JComboBox<String> getGenderComboBox() {
        return genderComboBox;
    }

    public JPanel getGenderInputPanel() {
        return genderInputPanel;
    }

    public JLabel getGenderLabel() {
        return genderLabel;
    }

    public JPanel getPhoneNumberInputPanel() {
        return phoneNumberInputPanel;
    }

    public JLabel getPhoneNumberLabel() {
        return phoneNumberLabel;
    }

    public JTextField getPhoneNumberTxtField() {
        return phoneNumberTxtField;
    }

    public JPanel getSalaryInputPanel() {
        return salaryInputPanel;
    }

    public JLabel getSalaryLabel() {
        return salaryLabel;
    }

    public JTextField getSalaryTxtField() {
        return salaryTxtField;
    }

    public JPanel getStartingDateInputPanel() {
        return startingDateInputPanel;
    }

    public JLabel getStartingDateLabel() {
        return startingDateLabel;
    }

    public JTextField getStartingDateTxtField() {
        return startingDateTxtField;
    }

    public JComboBox<String> getStatusComboBox() {
        return statusComboBox;
    }

    public JPanel getStatusInputPanel() {
        return statusInputPanel;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public JButton getUpdateBtn() {
        return updateBtn;
    }

    public JLabel getUpdateTitleLabel() {
        return updateTitleLabel;
    }

    public JPanel getUpdateUserPanel() {
        return updateUserPanel;
    }

    public JPanel getUserInfoPanel() {
        return userInfoPanel;
    }

    public void setNgaySinhChooser(JDateChooser ngaySinhChooser) {
        this.ngaySinhChooser = ngaySinhChooser;
    }

    public JDateChooser getNgaySinhChooser() {
        return ngaySinhChooser;
    }

    public JPanel getUserNameInputPanel() {
        return userNameInputPanel;
    }

    public JLabel getUserNameLabel() {
        return userNameLabel;
    }

    public JTextField getUserNameTxtField() {
        return userNameTxtField;
    }

    public ComponentUI getUi() {
        return ui;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public static int getWHEN_FOCUSED() {
        return WHEN_FOCUSED;
    }

    public static int getWHEN_ANCESTOR_OF_FOCUSED_COMPONENT() {
        return WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
    }

    public static int getWHEN_IN_FOCUSED_WINDOW() {
        return WHEN_IN_FOCUSED_WINDOW;
    }

    public static int getUNDEFINED_CONDITION() {
        return UNDEFINED_CONDITION;
    }

    public static String getTOOL_TIP_TEXT_KEY() {
        return TOOL_TIP_TEXT_KEY;
    }

    public static float getTOP_ALIGNMENT() {
        return TOP_ALIGNMENT;
    }

    public static float getCENTER_ALIGNMENT() {
        return CENTER_ALIGNMENT;
    }

    public static float getBOTTOM_ALIGNMENT() {
        return BOTTOM_ALIGNMENT;
    }

    public static float getLEFT_ALIGNMENT() {
        return LEFT_ALIGNMENT;
    }

    public static float getRIGHT_ALIGNMENT() {
        return RIGHT_ALIGNMENT;
    }

    public JDateChooser getjDateChooser1() {
        return ngaySinhChooser;
    }

    public void setjDateChooser1(JDateChooser jDateChooser1) {
        this.ngaySinhChooser = jDateChooser1;
    }

    public AccessibleContext getAccessibleContext() {
        return accessibleContext;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getPROPERTIES() {
        return PROPERTIES;
    }

    public static int getSOMEBITS() {
        return SOMEBITS;
    }

    public static int getFRAMEBITS() {
        return FRAMEBITS;
    }

    public static int getALLBITS() {
        return ALLBITS;
    }

    public static int getERROR() {
        return ERROR;
    }

    public static int getABORT() {
        return ABORT;
    }

    public void setBirthdateInputPanel(JPanel birthdateInputPanel) {
        this.birthdateInputPanel = birthdateInputPanel;
    }

    public void setBirthdateLabel(JLabel birthdateLabel) {
        this.birthdateLabel = birthdateLabel;
    }


    public void setBtnPanel(JPanel btnPanel) {
        this.btnPanel = btnPanel;
    }

    public void setCancelBtn(JButton cancelBtn) {
        this.changePasswordBtn = cancelBtn;
    }

    public void setCancelBtn1(JButton cancelBtn1) {
        this.cancelBtn1 = cancelBtn1;
    }

    public void setEmailInputPanel(JPanel emailInputPanel) {
        this.emailInputPanel = emailInputPanel;
    }

    public void setEmailLabel(JLabel emailLabel) {
        this.emailLabel = emailLabel;
    }

    public void setEmailTxtField(JTextField emailTxtField) {
        this.emailTxtField = emailTxtField;
    }

    public void setGenderComboBox(JComboBox<String> genderComboBox) {
        this.genderComboBox = genderComboBox;
    }

    public void setGenderInputPanel(JPanel genderInputPanel) {
        this.genderInputPanel = genderInputPanel;
    }

    public void setGenderLabel(JLabel genderLabel) {
        this.genderLabel = genderLabel;
    }

    public void setPhoneNumberInputPanel(JPanel phoneNumberInputPanel) {
        this.phoneNumberInputPanel = phoneNumberInputPanel;
    }

    public void setPhoneNumberLabel(JLabel phoneNumberLabel) {
        this.phoneNumberLabel = phoneNumberLabel;
    }

    public void setPhoneNumberTxtField(JTextField phoneNumberTxtField) {
        this.phoneNumberTxtField = phoneNumberTxtField;
    }

    public void setSalaryInputPanel(JPanel salaryInputPanel) {
        this.salaryInputPanel = salaryInputPanel;
    }

    public void setSalaryLabel(JLabel salaryLabel) {
        this.salaryLabel = salaryLabel;
    }

    public void setSalaryTxtField(JTextField salaryTxtField) {
        this.salaryTxtField = salaryTxtField;
    }

    public void setStartingDateInputPanel(JPanel startingDateInputPanel) {
        this.startingDateInputPanel = startingDateInputPanel;
    }

    public void setStartingDateLabel(JLabel startingDateLabel) {
        this.startingDateLabel = startingDateLabel;
    }

    public void setStartingDateTxtField(JTextField startingDateTxtField) {
        this.startingDateTxtField = startingDateTxtField;
    }

    public void setStatusComboBox(JComboBox<String> statusComboBox) {
        this.statusComboBox = statusComboBox;
    }

    public void setStatusInputPanel(JPanel statusInputPanel) {
        this.statusInputPanel = statusInputPanel;
    }

    public void setStatusLabel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
    }

    public void setUpdateBtn(JButton updateBtn) {
        this.updateBtn = updateBtn;
    }

    public void setUpdateTitleLabel(JLabel updateTitleLabel) {
        this.updateTitleLabel = updateTitleLabel;
    }

    public void setUpdateUserPanel(JPanel updateUserPanel) {
        this.updateUserPanel = updateUserPanel;
    }

    public void setUserInfoPanel(JPanel userInfoPanel) {
        this.userInfoPanel = userInfoPanel;
    }

    public void setUserNameInputPanel(JPanel userNameInputPanel) {
        this.userNameInputPanel = userNameInputPanel;
    }

    public void setUserNameLabel(JLabel userNameLabel) {
        this.userNameLabel = userNameLabel;
    }

    public void setUserNameTxtField(JTextField userNameTxtField) {
        this.userNameTxtField = userNameTxtField;
    }

    public void setUi(ComponentUI ui) {
        this.ui = ui;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    public void setAccessibleContext(AccessibleContext accessibleContext) {
        this.accessibleContext = accessibleContext;
    }

    public JButton getChangePasswordBtn() {
        return changePasswordBtn;
    }

    public JLabel getConfirmPasswordLabel() {
        return confirmPasswordLabel;
    }

    public JPanel getConfirmPasswordPanel() {
        return confirmPasswordPanel;
    }

    public JTextField getConfirmPasswordTxtField() {
        return confirmPasswordTxtField;
    }

    public JDialog getjDialog2() {
        return DoiMatKhau;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public JPanel getjPanel2() {
        return jPanel2;
    }

    public JLabel getNewPasswordLabel() {
        return newPasswordLabel;
    }

    public JPanel getNewPasswordPanel() {
        return newPasswordPanel;
    }

    public JTextField getNewPasswordTxtField() {
        return newPasswordTxtField;
    }

    public JLabel getOldPasswordLabel() {
        return oldPasswordLabel;
    }

    public JPanel getOldPasswordPanel() {
        return oldPasswordPanel;
    }

    public JTextField getOldPasswordTxtField() {
        return oldPasswordTxtField;
    }

    public JButton getSaveBtn() {
        return saveBtn;
    }

    public JLabel getUpdateTitleLabel1() {
        return updateTitleLabel1;
    }

    public void setChangePasswordBtn(JButton changePasswordBtn) {
        this.changePasswordBtn = changePasswordBtn;
    }

    public void setConfirmPasswordLabel(JLabel confirmPasswordLabel) {
        this.confirmPasswordLabel = confirmPasswordLabel;
    }

    public void setConfirmPasswordPanel(JPanel confirmPasswordPanel) {
        this.confirmPasswordPanel = confirmPasswordPanel;
    }

    public void setConfirmPasswordTxtField(JTextField confirmPasswordTxtField) {
        this.confirmPasswordTxtField = confirmPasswordTxtField;
    }

    public void setjDialog2(JDialog jDialog2) {
        this.DoiMatKhau = jDialog2;
    }

    public void setjPanel1(JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public void setjPanel2(JPanel jPanel2) {
        this.jPanel2 = jPanel2;
    }

    public void setNewPasswordLabel(JLabel newPasswordLabel) {
        this.newPasswordLabel = newPasswordLabel;
    }

    public void setNewPasswordPanel(JPanel newPasswordPanel) {
        this.newPasswordPanel = newPasswordPanel;
    }

    public void setNewPasswordTxtField(JTextField newPasswordTxtField) {
        this.newPasswordTxtField = newPasswordTxtField;
    }

    public void setOldPasswordLabel(JLabel oldPasswordLabel) {
        this.oldPasswordLabel = oldPasswordLabel;
    }

    public void setOldPasswordPanel(JPanel oldPasswordPanel) {
        this.oldPasswordPanel = oldPasswordPanel;
    }

    public void setOldPasswordTxtField(JTextField oldPasswordTxtField) {
        this.oldPasswordTxtField = oldPasswordTxtField;
    }

    public void setSaveBtn(JButton saveBtn) {
        this.saveBtn = saveBtn;
    }

    public void setUpdateTitleLabel1(JLabel updateTitleLabel1) {
        this.updateTitleLabel1 = updateTitleLabel1;
    }

    public JDialog getDoiMatKhau() {
        return DoiMatKhau;
    }

    public void setDoiMatKhau(JDialog DoiMatKhau) {
        this.DoiMatKhau = DoiMatKhau;
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DoiMatKhau = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        updateTitleLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        oldPasswordPanel = new javax.swing.JPanel();
        oldPasswordLabel = new javax.swing.JLabel();
        oldPasswordTxtField = new javax.swing.JTextField();
        newPasswordPanel = new javax.swing.JPanel();
        newPasswordLabel = new javax.swing.JLabel();
        newPasswordTxtField = new javax.swing.JTextField();
        confirmPasswordPanel = new javax.swing.JPanel();
        confirmPasswordLabel = new javax.swing.JLabel();
        confirmPasswordTxtField = new javax.swing.JTextField();
        cancelBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        updateUserPanel = new javax.swing.JPanel();
        updateTitleLabel = new javax.swing.JLabel();
        userInfoPanel = new javax.swing.JPanel();
        userNameInputPanel = new javax.swing.JPanel();
        userNameLabel = new javax.swing.JLabel();
        userNameTxtField = new javax.swing.JTextField();
        salaryInputPanel = new javax.swing.JPanel();
        salaryLabel = new javax.swing.JLabel();
        salaryTxtField = new javax.swing.JTextField();
        emailInputPanel = new javax.swing.JPanel();
        emailLabel = new javax.swing.JLabel();
        emailTxtField = new javax.swing.JTextField();
        genderInputPanel = new javax.swing.JPanel();
        genderLabel = new javax.swing.JLabel();
        genderComboBox = new javax.swing.JComboBox<>();
        birthdateInputPanel = new javax.swing.JPanel();
        birthdateLabel = new javax.swing.JLabel();
        ngaySinhChooser = new com.toedter.calendar.JDateChooser();
        startingDateInputPanel = new javax.swing.JPanel();
        startingDateLabel = new javax.swing.JLabel();
        startingDateTxtField = new javax.swing.JTextField();
        statusInputPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox<>();
        btnPanel = new javax.swing.JPanel();
        phoneNumberInputPanel = new javax.swing.JPanel();
        phoneNumberLabel = new javax.swing.JLabel();
        phoneNumberTxtField = new javax.swing.JTextField();
        changePasswordBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();

        DoiMatKhau.setMinimumSize(new java.awt.Dimension(600, 500));
        DoiMatKhau.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        updateTitleLabel1.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        updateTitleLabel1.setForeground(new java.awt.Color(132, 94, 194));
        updateTitleLabel1.setText("Đổi mật khẩu");
        jPanel1.add(updateTitleLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 220, 50));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        oldPasswordPanel.setBackground(new java.awt.Color(255, 255, 255));
        oldPasswordPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        oldPasswordLabel.setBackground(new java.awt.Color(255, 255, 255));
        oldPasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        oldPasswordLabel.setForeground(new java.awt.Color(111, 121, 121));
        oldPasswordLabel.setText("Mật khẩu cũ");
        oldPasswordLabel.setPreferredSize(new java.awt.Dimension(56, 28));
        oldPasswordPanel.add(oldPasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, -1));

        oldPasswordTxtField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        oldPasswordTxtField.setPreferredSize(new java.awt.Dimension(370, 36));
        oldPasswordTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oldPasswordTxtFieldActionPerformed(evt);
            }
        });
        oldPasswordPanel.add(oldPasswordTxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 28, 430, -1));

        jPanel2.add(oldPasswordPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, -1));

        newPasswordPanel.setBackground(new java.awt.Color(255, 255, 255));
        newPasswordPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        newPasswordLabel.setBackground(new java.awt.Color(255, 255, 255));
        newPasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        newPasswordLabel.setForeground(new java.awt.Color(111, 121, 121));
        newPasswordLabel.setText("Mật khẩu mới");
        newPasswordLabel.setPreferredSize(new java.awt.Dimension(56, 28));
        newPasswordPanel.add(newPasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, -1));

        newPasswordTxtField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        newPasswordTxtField.setPreferredSize(new java.awt.Dimension(370, 36));
        newPasswordTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPasswordTxtFieldActionPerformed(evt);
            }
        });
        newPasswordPanel.add(newPasswordTxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 28, 430, -1));

        jPanel2.add(newPasswordPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 430, -1));

        confirmPasswordPanel.setBackground(new java.awt.Color(255, 255, 255));
        confirmPasswordPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        confirmPasswordLabel.setBackground(new java.awt.Color(255, 255, 255));
        confirmPasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        confirmPasswordLabel.setForeground(new java.awt.Color(111, 121, 121));
        confirmPasswordLabel.setText("Xác nhận mật khẩu");
        confirmPasswordLabel.setPreferredSize(new java.awt.Dimension(56, 28));
        confirmPasswordPanel.add(confirmPasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, -1));

        confirmPasswordTxtField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        confirmPasswordTxtField.setPreferredSize(new java.awt.Dimension(370, 36));
        confirmPasswordTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPasswordTxtFieldActionPerformed(evt);
            }
        });
        confirmPasswordPanel.add(confirmPasswordTxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 28, 430, -1));

        jPanel2.add(confirmPasswordPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 430, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, 250));

        cancelBtn.setBackground(new java.awt.Color(132, 94, 194));
        cancelBtn.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        cancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        cancelBtn.setText("Thoát");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        jPanel1.add(cancelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 400, 130, 50));

        saveBtn.setBackground(new java.awt.Color(0, 201, 167));
        saveBtn.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(255, 255, 255));
        saveBtn.setText("Lưu");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        jPanel1.add(saveBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 400, 130, 50));

        DoiMatKhau.getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 500));

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        updateUserPanel.setBackground(new java.awt.Color(255, 255, 255));
        updateUserPanel.setPreferredSize(new java.awt.Dimension(810, 671));
        updateUserPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        updateTitleLabel.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        updateTitleLabel.setForeground(new java.awt.Color(132, 94, 194));
        updateTitleLabel.setText("Thông tin tài khoản");
        updateUserPanel.add(updateTitleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 310, 50));

        userInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        userInfoPanel.setPreferredSize(new java.awt.Dimension(762, 356));
        userInfoPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameInputPanel.setBackground(new java.awt.Color(255, 255, 255));
        userNameInputPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userNameLabel.setBackground(new java.awt.Color(255, 255, 255));
        userNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        userNameLabel.setForeground(new java.awt.Color(111, 121, 121));
        userNameLabel.setText("Họ và tên");
        userNameLabel.setPreferredSize(new java.awt.Dimension(56, 28));
        userNameInputPanel.add(userNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, -1));

        userNameTxtField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        userNameTxtField.setPreferredSize(new java.awt.Dimension(370, 36));
        userNameTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameTxtFieldActionPerformed(evt);
            }
        });
        userNameInputPanel.add(userNameTxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 28, 430, -1));

        userInfoPanel.add(userNameInputPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, -1));

        salaryInputPanel.setBackground(new java.awt.Color(255, 255, 255));
        salaryInputPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        salaryLabel.setBackground(new java.awt.Color(255, 255, 255));
        salaryLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        salaryLabel.setForeground(new java.awt.Color(111, 121, 121));
        salaryLabel.setText("Lương");
        salaryLabel.setPreferredSize(new java.awt.Dimension(56, 28));
        salaryInputPanel.add(salaryLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        salaryTxtField.setEditable(false);
        salaryTxtField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        salaryTxtField.setEnabled(false);
        salaryTxtField.setPreferredSize(new java.awt.Dimension(370, 36));
        salaryTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salaryTxtFieldActionPerformed(evt);
            }
        });
        salaryInputPanel.add(salaryTxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 28, 430, -1));

        userInfoPanel.add(salaryInputPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 430, -1));

        emailInputPanel.setBackground(new java.awt.Color(255, 255, 255));
        emailInputPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        emailLabel.setBackground(new java.awt.Color(255, 255, 255));
        emailLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(111, 121, 121));
        emailLabel.setText("Email");
        emailLabel.setPreferredSize(new java.awt.Dimension(56, 28));
        emailInputPanel.add(emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        emailTxtField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        emailTxtField.setPreferredSize(new java.awt.Dimension(370, 36));
        emailTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTxtFieldActionPerformed(evt);
            }
        });
        emailInputPanel.add(emailTxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 28, 430, 40));

        userInfoPanel.add(emailInputPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 430, 70));

        genderInputPanel.setBackground(new java.awt.Color(255, 255, 255));
        genderInputPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        genderLabel.setBackground(new java.awt.Color(255, 255, 255));
        genderLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        genderLabel.setForeground(new java.awt.Color(111, 121, 121));
        genderLabel.setText("Giới tính");
        genderLabel.setPreferredSize(new java.awt.Dimension(56, 28));
        genderInputPanel.add(genderLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, -1));

        genderComboBox.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        genderComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        genderInputPanel.add(genderComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 28, 430, 40));

        userInfoPanel.add(genderInputPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 430, 70));

        birthdateInputPanel.setBackground(new java.awt.Color(255, 255, 255));
        birthdateInputPanel.setPreferredSize(new java.awt.Dimension(370, 64));
        birthdateInputPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        birthdateLabel.setBackground(new java.awt.Color(255, 255, 255));
        birthdateLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        birthdateLabel.setForeground(new java.awt.Color(111, 121, 121));
        birthdateLabel.setText("Ngày sinh");
        birthdateLabel.setPreferredSize(new java.awt.Dimension(56, 28));
        birthdateInputPanel.add(birthdateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 71, -1));
        birthdateInputPanel.add(ngaySinhChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 430, 40));

        userInfoPanel.add(birthdateInputPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 430, 70));

        startingDateInputPanel.setBackground(new java.awt.Color(255, 255, 255));
        startingDateInputPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        startingDateLabel.setBackground(new java.awt.Color(255, 255, 255));
        startingDateLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        startingDateLabel.setForeground(new java.awt.Color(111, 121, 121));
        startingDateLabel.setText("Ngày bắt đầu");
        startingDateLabel.setPreferredSize(new java.awt.Dimension(56, 28));
        startingDateInputPanel.add(startingDateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, -1));

        startingDateTxtField.setEditable(false);
        startingDateTxtField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        startingDateTxtField.setEnabled(false);
        startingDateTxtField.setPreferredSize(new java.awt.Dimension(370, 36));
        startingDateTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startingDateTxtFieldActionPerformed(evt);
            }
        });
        startingDateInputPanel.add(startingDateTxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 28, 430, 40));

        userInfoPanel.add(startingDateInputPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 430, 70));

        statusInputPanel.setBackground(new java.awt.Color(255, 255, 255));
        statusInputPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        statusLabel.setBackground(new java.awt.Color(255, 255, 255));
        statusLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(111, 121, 121));
        statusLabel.setText("Trạng thái");
        statusLabel.setPreferredSize(new java.awt.Dimension(56, 28));
        statusInputPanel.add(statusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, -1));

        statusComboBox.setEditable(true);
        statusComboBox.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang làm", "Nghỉ làm" }));
        statusInputPanel.add(statusComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 28, 430, 40));

        userInfoPanel.add(statusInputPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, 430, 70));

        btnPanel.setBackground(new java.awt.Color(255, 255, 255));
        btnPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        userInfoPanel.add(btnPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, -1, 40));

        phoneNumberInputPanel.setBackground(new java.awt.Color(255, 255, 255));
        phoneNumberInputPanel.setPreferredSize(new java.awt.Dimension(370, 64));
        phoneNumberInputPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        phoneNumberLabel.setBackground(new java.awt.Color(255, 255, 255));
        phoneNumberLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        phoneNumberLabel.setForeground(new java.awt.Color(111, 121, 121));
        phoneNumberLabel.setText("Số điện thoại");
        phoneNumberLabel.setPreferredSize(new java.awt.Dimension(56, 28));
        phoneNumberInputPanel.add(phoneNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 94, -1));

        phoneNumberTxtField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        phoneNumberTxtField.setPreferredSize(new java.awt.Dimension(370, 36));
        phoneNumberTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumberTxtFieldActionPerformed(evt);
            }
        });
        phoneNumberInputPanel.add(phoneNumberTxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 430, 40));

        userInfoPanel.add(phoneNumberInputPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 430, 70));

        updateUserPanel.add(userInfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 920, 420));

        changePasswordBtn.setBackground(new java.awt.Color(102, 102, 255));
        changePasswordBtn.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        changePasswordBtn.setForeground(new java.awt.Color(255, 255, 255));
        changePasswordBtn.setText("Đổi mật khẩu");
        changePasswordBtn.setPreferredSize(new java.awt.Dimension(70, 34));
        changePasswordBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordBtnActionPerformed(evt);
            }
        });
        updateUserPanel.add(changePasswordBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 580, 160, 50));

        updateBtn.setBackground(new java.awt.Color(0, 201, 167));
        updateBtn.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        updateBtn.setForeground(new java.awt.Color(255, 255, 255));
        updateBtn.setText("Cập nhật");
        updateBtn.setPreferredSize(new java.awt.Dimension(80, 34));
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        updateUserPanel.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 580, 150, 50));

        add(updateUserPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 730));
    }// </editor-fold>//GEN-END:initComponents

    private void userNameTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameTxtFieldActionPerformed

    private void salaryTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salaryTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salaryTxtFieldActionPerformed

    private void emailTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTxtFieldActionPerformed

    private void startingDateTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startingDateTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startingDateTxtFieldActionPerformed

    private void changePasswordBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_changePasswordBtnActionPerformed

    private void phoneNumberTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNumberTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNumberTxtFieldActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateBtnActionPerformed

    private void oldPasswordTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oldPasswordTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oldPasswordTxtFieldActionPerformed

    private void newPasswordTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPasswordTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newPasswordTxtFieldActionPerformed

    private void confirmPasswordTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPasswordTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmPasswordTxtFieldActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DoiMatKhau;
    private javax.swing.JPanel birthdateInputPanel;
    private javax.swing.JLabel birthdateLabel;
    private javax.swing.JPanel btnPanel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton changePasswordBtn;
    private javax.swing.JLabel confirmPasswordLabel;
    private javax.swing.JPanel confirmPasswordPanel;
    private javax.swing.JTextField confirmPasswordTxtField;
    private javax.swing.JPanel emailInputPanel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTxtField;
    private javax.swing.JComboBox<String> genderComboBox;
    private javax.swing.JPanel genderInputPanel;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel newPasswordLabel;
    private javax.swing.JPanel newPasswordPanel;
    private javax.swing.JTextField newPasswordTxtField;
    private com.toedter.calendar.JDateChooser ngaySinhChooser;
    private javax.swing.JLabel oldPasswordLabel;
    private javax.swing.JPanel oldPasswordPanel;
    private javax.swing.JTextField oldPasswordTxtField;
    private javax.swing.JPanel phoneNumberInputPanel;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JTextField phoneNumberTxtField;
    private javax.swing.JPanel salaryInputPanel;
    private javax.swing.JLabel salaryLabel;
    private javax.swing.JTextField salaryTxtField;
    private javax.swing.JButton saveBtn;
    private javax.swing.JPanel startingDateInputPanel;
    private javax.swing.JLabel startingDateLabel;
    private javax.swing.JTextField startingDateTxtField;
    private javax.swing.JComboBox<String> statusComboBox;
    private javax.swing.JPanel statusInputPanel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JButton updateBtn;
    private javax.swing.JLabel updateTitleLabel;
    private javax.swing.JLabel updateTitleLabel1;
    private javax.swing.JPanel updateUserPanel;
    private javax.swing.JPanel userInfoPanel;
    private javax.swing.JPanel userNameInputPanel;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JTextField userNameTxtField;
    // End of variables declaration//GEN-END:variables
}
