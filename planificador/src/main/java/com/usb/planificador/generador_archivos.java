/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usb.planificador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class generador_archivos {
    
    private List<String> gestor_carpeta_procesos(){
        
        List<String> listaCarpetas = new ArrayList<>();
        File carpeta = new File(".");        
        File[] listaArchivos = carpeta.listFiles();

        if (listaArchivos != null) {
            for (File archivo : listaArchivos) {
                if (archivo.isDirectory()) {
                    listaCarpetas.add(archivo.getName());
                    System.out.println("Carpeta: " + archivo.getName());
                } else {
                    System.out.println("Archivo: " + archivo.getName());
                }
            }
        } else {
            System.out.println("La carpeta está vacía o no existe.");
        }
        
        return listaCarpetas;
    }
    
    public int test(){
        gestor_carpeta_procesos();
        return 0;
    }
}
