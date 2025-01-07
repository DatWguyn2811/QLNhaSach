/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.quanlinhasach.controller;

import java.io.File;

/**
 *
 * @author TienDat
 * @param <T>
 */
public interface ControllerInterface<T> {
    void add(T t);
    void update(T t);
    void delete(T t);
    void loadTableData();
    void importFromExcel(File excelFile);
    void exportFromExcel(File destinationFile);
}
