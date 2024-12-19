/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.model;

import java.util.Objects;

/**
 *
 * @author TienDat
 */
public class NhanVien {
    private int maNhanVien;
    private String tenNhanVien;
    private String gioiTinh;
    private String ngaySinh;
    private String soDienThoai;
    private String email;
    private int luong;
    private int trangThai;
    private String ngayVaoLam;
    private String tenDangNhap;
    private String matkhau;
    private String vaiTro;
    private int maQuyen;

    public NhanVien() {}

    public NhanVien(int maNhanVien, String tenNhanVien, String gioiTinh, String ngaySinh, String soDienThoai, String email, int luong, int trangThai, String ngayVaoLam, String tenDangNhap, String matkhau, String vaiTro, int maQuyen) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.luong = luong;
        this.trangThai = trangThai;
        this.ngayVaoLam = ngayVaoLam;
        this.tenDangNhap = tenDangNhap;
        this.matkhau = matkhau;
        this.vaiTro = vaiTro;
        this.maQuyen = maQuyen;
    }
    

    public NhanVien(String tenNhanVien, String gioiTinh, String ngaySinh, String soDienThoai, String email, int luong, int trangThai, String ngayVaoLam, String tenDangNhap, String matkhau, String vaiTro, int maQuyen) {
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.luong = luong;
        this.trangThai = trangThai;
        this.ngayVaoLam = ngayVaoLam;
        this.tenDangNhap = tenDangNhap;
        this.matkhau = matkhau;
        this.vaiTro = vaiTro;
        this.maQuyen = maQuyen;
    }

    

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
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

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(String ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public int getMaQuyen() {
        return maQuyen;
    }

    public void setMaQuyen(int maQuyen) {
        this.maQuyen = maQuyen;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
    
    public String getMatKhau() {
        return matkhau;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }
    
    
    
    
    @Override
    public String toString() {
        return "NhanVien{" +
                "maNhanVien=" + maNhanVien +
                ", tenNhanVien='" + tenNhanVien + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", email='" + email + '\'' +
                ", luong=" + luong +
                ", trangThai=" + trangThai +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NhanVien other = (NhanVien) obj;
        return maNhanVien == other.maNhanVien &&
                luong == other.luong &&
                trangThai == other.trangThai &&
                Objects.equals(tenNhanVien, other.tenNhanVien) &&
                Objects.equals(gioiTinh, other.gioiTinh) &&
                Objects.equals(ngaySinh, other.ngaySinh) &&
                Objects.equals(soDienThoai, other.soDienThoai) &&
                Objects.equals(email, other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, soDienThoai, email, luong, trangThai);
    }
    
}
    