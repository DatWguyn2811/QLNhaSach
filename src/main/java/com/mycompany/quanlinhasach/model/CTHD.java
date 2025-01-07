/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.model;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class CTHD {
    private int maHoaDon;
    private int maSach;
    private int thanhTien;
    private int soLuong;

    public CTHD(){
        
    }
    // Constructor
    public CTHD(int maHoaDon, int maSach, int thanhTien, int soLuong) {
        this.maHoaDon = maHoaDon;
        this.maSach = maSach;
        this.thanhTien = thanhTien;
        this.soLuong = soLuong;
    }

    // Getter và Setter
    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
