/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.model;

/**
 *
 * @author TienDat
 */
public class TheLoai {
    private int maTheLoai;
    private String tenTheLoai;
    private int soLuong;
    
    public TheLoai(int maTheLoai, String tenTheLoai, int soLuong) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.soLuong = soLuong;
    }
    
    public TheLoai(int maTheLoai, String tenTheLoai) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
    }

    public TheLoai(String tenTheLoai, int soLuong) {
        this.tenTheLoai = tenTheLoai;
        this.soLuong = soLuong;
    }
    
    public TheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
        this.soLuong = 0;
    } 
    

    public int getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    
}
