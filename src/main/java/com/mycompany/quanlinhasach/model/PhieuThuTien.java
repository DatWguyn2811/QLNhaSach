/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.model;

/**
 *
 * @author TienDat
 */
public class PhieuThuTien {
    private int maPhieuThuTien;
    private int maKhachhang;
    private int soTienThu;
    private String ngayThuTien;
    private int maNhanVien;
    
    public PhieuThuTien(int maPhieuThuTien, int maKhachHang, int soTienThu, String ngayThuTien, int maNhanVien) {
        this.maPhieuThuTien = maPhieuThuTien;
        this.maKhachhang = maKhachHang;
        this.soTienThu = soTienThu;
        this.ngayThuTien = ngayThuTien;
        this.maNhanVien = maNhanVien;
    }

    public PhieuThuTien(int maKhachhang, int soTienThu, String ngayThuTien, int maNhanVien) {
        this.maKhachhang = maKhachhang;
        this.soTienThu = soTienThu;
        this.ngayThuTien = ngayThuTien;
        this.maNhanVien = maNhanVien;
    }

    public int getMaPhieuThuTien() {
        return maPhieuThuTien;
    }

    public void setMaPhieuThuTien(int maPhieuThuTien) {
        this.maPhieuThuTien = maPhieuThuTien;
    }

    public int getMaKhachhang() {
        return maKhachhang;
    }

    public void setMaKhachhang(int maKhachhang) {
        this.maKhachhang = maKhachhang;
    }

    public int getSoTienThu() {
        return soTienThu;
    }

    public void setSoTienThu(int soTienThu) {
        this.soTienThu = soTienThu;
    }

    public String getNgayThuTien() {
        return ngayThuTien;
    }

    public void setNgayThuTien(String ngayThuTien) {
        this.ngayThuTien = ngayThuTien;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
    
    
}
