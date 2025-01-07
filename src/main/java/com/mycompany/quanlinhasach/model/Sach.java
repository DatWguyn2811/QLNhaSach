/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.model;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class Sach {
    private int maSach;
    private String tenSach;
    private String tenTacGia;
    private String tenNXB;
    private int namXuatBan;
    private int donGia;
    private int soLuong;
    
    public Sach(){
        
    }
    // Constructor đầy đủ
    public Sach(int maSach, String tenSach, String tenTacGia, String tenNXB, int namXuatBan, int donGia, int soLuong) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tenTacGia = tenTacGia;
        this.tenNXB = tenNXB;
        this.namXuatBan = namXuatBan;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }
    // Constructor đầy đủ
    public Sach( String tenSach, String tenTacGia, String tenNXB, int namXuatBan, int donGia, int soLuong) {
        this.tenSach = tenSach;
        this.tenTacGia = tenTacGia;
        this.tenNXB = tenNXB;
        this.namXuatBan = namXuatBan;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    // Getters và Setters
    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "Sach{" +
                "maSach=" + maSach +
                ", tenSach='" + tenSach + '\'' +
                ", tenTacGia='" + tenTacGia + '\'' +
                ", tenNXB='" + tenNXB + '\'' +
                ", namXuatBan=" + namXuatBan +
                ", donGia=" + donGia +
                ", soLuong=" + soLuong +
                '}';
    }


}


