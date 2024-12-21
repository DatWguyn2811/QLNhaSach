/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class DauSach {
    private int maDauSach;        // MaDauSach - Khóa chính
    private String tenDauSach;    // Tên đầu sách
    private int maTheLoai;        // Mã thể loại
    private int maTacGia;         // Mã tác giả

    // Constructor không tham số
    public DauSach() {
    }

    // Constructor có tham số
    public DauSach(int maDauSach, String tenDauSach, int maTheLoai, int maTacGia) {
        this.maDauSach = maDauSach;
        this.tenDauSach = tenDauSach;
        this.maTheLoai = maTheLoai;
        this.maTacGia = maTacGia;
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

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public int getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(int maTacGia) {
        this.maTacGia = maTacGia;
    }

    // Override phương thức toString() để hiển thị thông tin đầu sách
    @Override
    public String toString() {
        return "DauSach{" +
                "maDauSach=" + maDauSach +
                ", tenDauSach='" + tenDauSach + '\'' +
                ", maTheLoai=" + maTheLoai +
                ", maTacGia=" + maTacGia +
                '}';
    }
}

