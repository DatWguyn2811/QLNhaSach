/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.model;

/**
 *
 * @author HOANG
 */
public class BaoCaoTonKho {
    private int maBaoCaoTonKho;
    private int maSach;
    private String tenSach;
    private int tonDau;
    private int tonCuoi;
    private int phatSinh;
    private int thang;
    private int nam;

    public BaoCaoTonKho(int maBaoCaoTonKho, int maSach, String tenSach, int tonDau, int tonCuoi, int phatSinh, int thang, int nam) {
        this.maBaoCaoTonKho = maBaoCaoTonKho;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tonDau = tonDau;
        this.tonCuoi = tonCuoi;
        this.phatSinh = phatSinh;
        this.thang = thang;
        this.nam = nam;
    }

    public int getMaBaoCaoTonKho() {
        return maBaoCaoTonKho;
    }

    public int getMaSach() {
        return maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public int getTonDau() {
        return tonDau;
    }

    public int getTonCuoi() {
        return tonCuoi;
    }

    public int getPhatSinh() {
        return phatSinh;
    }

    public int getThang() {
        return thang;
    }

    public int getNam() {
        return nam;
    }
}