/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.model;

/**
 *
 * @author HOANG
 */
public class BaoCaoCongNo {
        private int maBaoCaoCongNo;
        private String tenKhachHang; // Tên khách hàng lấy qua MaKhachHang
        private int noDau;
        private int noCuoi;
        private int phatSinh;
        private int thang;
        private int nam;
    
        public BaoCaoCongNo(int maBaoCaoCongNo, String tenKhachHang, int noDau, int noCuoi, int phatSinh, int thang, int nam) {
            this.maBaoCaoCongNo = maBaoCaoCongNo;
            this.tenKhachHang = tenKhachHang;
            this.noDau = noDau;
            this.noCuoi = noCuoi;
            this.phatSinh = phatSinh;
            this.thang = thang;
            this.nam = nam;
        }
    
        // Getters và setters
        public int getMaBaoCaoCongNo() { return maBaoCaoCongNo; }
        public void setMaBaoCaoCongNo(int maBaoCaoCongNo) { this.maBaoCaoCongNo = maBaoCaoCongNo; }
        public String getTenKhachHang() { return tenKhachHang; }
        public void setTenKhachHang(String tenKhachHang) { this.tenKhachHang = tenKhachHang; }
        public int getNoDau() { return noDau; }
        public void setNoDau(int noDau) { this.noDau = noDau; }
        public int getNoCuoi() { return noCuoi; }
        public void setNoCuoi(int noCuoi) { this.noCuoi = noCuoi; }
        public int getPhatSinh() { return phatSinh; }
        public void setPhatSinh(int phatSinh) { this.phatSinh = phatSinh; }
        public int getThang() { return thang; }
        public void setThang(int thang) { this.thang = thang; }
        public int getNam() { return nam; }
        public void setNam(int nam) { this.nam = nam; }
    }    
