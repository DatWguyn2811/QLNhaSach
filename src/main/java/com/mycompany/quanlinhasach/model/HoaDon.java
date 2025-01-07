/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.model;

import java.util.Date;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class HoaDon {
    private int maHoaDon;
    private int maKhachHang;
    private String ngayLap;
    private int tongTien;
    private int soTienThu;
    private int conLai;
    private int maNhanVien;
    
    public HoaDon(){
        
    }
    // Constructor
    public HoaDon(int maHoaDon, int maKhachHang, String ngayLap, int tongTien, int soTienThu, int conLai, int maNhanVien) {
        this.maHoaDon = maHoaDon;
        this.maKhachHang = maKhachHang;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.soTienThu = soTienThu;
        this.conLai = conLai;
        this.maNhanVien = maNhanVien;
    }

    // Getter và Setter
    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public int getSoTienThu() {
        return soTienThu;
    }

    public void setSoTienThu(int soTienThu) {
        this.soTienThu = soTienThu;
    }

    public int getConLai() {
        return conLai;
    }

    public void setConLai(int conLai) {
        this.conLai = conLai;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
}
