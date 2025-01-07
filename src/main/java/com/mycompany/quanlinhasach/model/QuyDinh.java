/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.model;

/**
 *
 * @author TienDat
 */
public class QuyDinh {
    private int soLuongNhapToiThieu;
    private int soLuongTonToiThieuDeNhap;
    private int monNoKhachHangToiDaDeBan;
    private int soLuongTonSauKhiBanToiThieu;
    private int apDungChinhSachThuNo;
    private double heSoGiaBan;

    public QuyDinh() {
    }
    
    
    public QuyDinh(int soLuongNhapToiThieu, int soLuongTonToiThieuDeNhap, int monNoKhachHangToiDaDeBan, int soLuongTonSauKhiBanToiThieu, int apDungChinhSachThuNo, double heSoGiaBan) {
        this.soLuongNhapToiThieu = soLuongNhapToiThieu;
        this.soLuongTonToiThieuDeNhap = soLuongTonToiThieuDeNhap;
        this.monNoKhachHangToiDaDeBan = monNoKhachHangToiDaDeBan;
        this.soLuongTonSauKhiBanToiThieu = soLuongTonSauKhiBanToiThieu;
        this.apDungChinhSachThuNo = apDungChinhSachThuNo;
        this.heSoGiaBan = heSoGiaBan; 
    }
    
    public int getSoLuongNhapToiThieu() {
        return soLuongNhapToiThieu;
    }

    public void setSoLuongNhapToiThieu(int soLuongNhapToiThieu) {
        this.soLuongNhapToiThieu = soLuongNhapToiThieu;
    }

    public int getSoLuongTonToiThieuDeNhap() {
        return soLuongTonToiThieuDeNhap;
    }

    public void setSoLuongTonToiThieuDeNhap(int soLuongTonToiThieuDeNhap) {
        this.soLuongTonToiThieuDeNhap = soLuongTonToiThieuDeNhap;
    }

    public int getMonNoKhachHangToiDaDeBan() {
        return monNoKhachHangToiDaDeBan;
    }

    public void setMonNoKhachHangToiDaDeBan(int monNoKhachHangToiDaDeBan) {
        this.monNoKhachHangToiDaDeBan = monNoKhachHangToiDaDeBan;
    }

    public int getSoLuongTonSauKhiBanToiThieu() {
        return soLuongTonSauKhiBanToiThieu;
    }

    public void setSoLuongTonSauKhiBanToiThieu(int soLuongTonSauKhiBanToiThieu) {
        this.soLuongTonSauKhiBanToiThieu = soLuongTonSauKhiBanToiThieu;
    }

    public int getApDungChinhSachThuNo() {
        return apDungChinhSachThuNo;
    }

    public void setApDungChinhSachThuNo(int apDungChinhSachThuNo) {
        this.apDungChinhSachThuNo = apDungChinhSachThuNo;
    }

    public double getHeSoGiaBan() {
        return heSoGiaBan;
    }

    public void setHeSoGiaBan(float heSoGiaBan) {
        this.heSoGiaBan = heSoGiaBan;
    }
    
    
    
}
