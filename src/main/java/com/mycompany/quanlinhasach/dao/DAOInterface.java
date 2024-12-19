/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.quanlinhasach.dao;

import java.util.ArrayList;

/**
 *
 * @author TienDat
 * @param <T>
 */
public interface DAOInterface<T> {    
    public boolean insert(T t);
    
    public int update(T t);
    
    public boolean delete(T t);
    
    public ArrayList<T> selectAll(); 
    
    public T selectById(T t);
    
    public ArrayList<T> selectByCondition(String condition);
}
