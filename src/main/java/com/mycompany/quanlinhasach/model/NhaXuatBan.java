/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.DefaultComboBoxModel;

public class NhaXuatBan {
    private int maNhaXuatBan;
    private String tenNhaXuatBan;

    public NhaXuatBan(int maNhaXuatBan, String tenNhaXuatBan) {
        this.maNhaXuatBan = maNhaXuatBan;
        this.tenNhaXuatBan = tenNhaXuatBan;
    }

    public int getMaNhaXuatBan() {
        return maNhaXuatBan;
    }

    public String getTenNhaXuatBan() {
        return tenNhaXuatBan;
    }

    @Override
    public String toString() {
        return this.tenNhaXuatBan;  // Chỉ trả về tên nhà xuất bản
    }
}


