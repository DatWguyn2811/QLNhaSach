/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.model;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class DauSachModel {
    private int maDauSach;        // Mã đầu sách - Khóa chính
    private String tenDauSach;    // Tên đầu sách
    private String tenTheLoai;    // Tên thể loại
    private String tenTacGia;     // Tên tác giả

    // Constructor không tham số
    public DauSachModel() {
    }
    public DauSachModel( String tenDauSach, String tenTheLoai, String tenTacGia) {
        this.tenDauSach = tenDauSach;
        this.tenTheLoai = tenTheLoai;
        this.tenTacGia = tenTacGia;
    }
    // Constructor có tham số
    public DauSachModel(int maDauSach, String tenDauSach, String tenTheLoai, String tenTacGia) {
        this.maDauSach = maDauSach;
        this.tenDauSach = tenDauSach;
        this.tenTheLoai = tenTheLoai;
        this.tenTacGia = tenTacGia;
    }

    // Getter và Setter cho từng thuộc tính
    public int getMaDauSach() {
        return maDauSach;
    }

    public void setMaDauSach(int maDauSach) {
        this.maDauSach = maDauSach;
    }

    public String getTenDauSach() {
        return tenDauSach;
    }

    public void setTenDauSach(String tenDauSach) {
        this.tenDauSach = tenDauSach;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    // Override phương thức toString() để hiển thị thông tin đầu sách
    @Override
    public String toString() {
        return "DauSach{" +
                "maDauSach=" + maDauSach +
                ", tenDauSach='" + tenDauSach + '\'' +
                ", tenTheLoai='" + tenTheLoai + '\'' +
                ", tenTacGia='" + tenTacGia + '\'' +
                '}';
    }
}


