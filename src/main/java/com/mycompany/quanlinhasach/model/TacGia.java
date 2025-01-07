/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlinhasach.model;

/**
 *
 * @author NGUYEN MY NGAN
 */
public class TacGia {
    private int maTacGia;
    private String tenTacGia;
    

    public TacGia(int maTacGia, String tenTacGia) {
        this.maTacGia = maTacGia;
        this.tenTacGia = tenTacGia;
    }


    
  
    public int getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(int maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    
}