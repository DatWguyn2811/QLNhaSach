/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.model;

/**
 *
 * @author TienDat
 */
public class KhachHang {
    private int maKhachHang;
    private String tenKhachHang;
    private String diaChi;
    private String soDienThoai;
    private String email;
    private int tongNo;

    public KhachHang() {
    }
    
    public KhachHang(int maKhachHang, String tenKhachHang, String diaChi, String soDienThoai, String email, int tongNo) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.tongNo = tongNo;
    }
    
    public KhachHang(int maKhachHang, String tenKhachHang, String diaChi, String soDienThoai, String email) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    public KhachHang(String tenKhachHang, String diaChi, String soDienThoai, String email) {
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.tongNo = 0;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTongNo() {
        return tongNo;
    }

    public void setTongNo(int tongNo) {
        this.tongNo = tongNo;
    }   
}
