/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usb.planificador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class generador_archivos {
    
    String carpetaProcesos = ".\\procesos";
    
    private String[] listar_archivos(String nombre){
        File carpeta = new File(nombre);
        return carpeta.list();
    }
    
    private void generar_procesos(int cantidad) throws IOException{
        String prefijo = carpetaProcesos + "\\proceso";
        String extension = ".txt";
        Random aleatorio = new Random();
        for(int i = 0; i<cantidad; i++){
            String nombreProceso = prefijo + String.valueOf(i) + extension;
            File proceso = new File(nombreProceso);
            FileWriter escritor = new FileWriter(proceso);
            BufferedWriter buffer = new BufferedWriter(escritor);
            int cantidadInstrucciones = aleatorio.nextInt(11) + 10;
            for(int j = 0; j < cantidadInstrucciones; j++){
                int flag = aleatorio.nextInt(4);
                if(flag > 0){
                    buffer.write("EXEC\n");
                }else{
                    buffer.write("WAIT\n");
                }
            }
            buffer.close();
            escritor.close();
        }
    }

    private void generar_bash(int cantidad) throws IOException{
        String[] procesos = listar_archivos(carpetaProcesos);
        int cantidadProcesos = procesos.length;
        File bash = new File(".\\bash.txt");
        FileWriter escritor = new FileWriter(bash);
        BufferedWriter buffer = new BufferedWriter(escritor);
        Random aleatorio = new Random();
        for(int i = 0; i<cantidad; i++){
            buffer.write(procesos[aleatorio.nextInt(cantidadProcesos)]);
            buffer.write(",");
            buffer.write(String.valueOf(aleatorio.nextInt(10) + 1));
            buffer.write(",");
            buffer.write(String.valueOf(aleatorio.nextInt(3) + 1));
            buffer.newLine();
        }
        buffer.close();
        escritor.close();
    }
    
    private boolean crear_carpeta(String nombre){
        File carpeta = new File(nombre);
        if(carpeta.exists() && carpeta.isDirectory()){
            System.out.println("La carpeta existe.");
            return false;
        }else{
            System.out.println("La carpeta no existe.");
            return carpeta.mkdir();
        }
    }
    
    
    
    public boolean test(){
        crear_carpeta(carpetaProcesos);
        try {
            generar_procesos(6);
            generar_bash(10);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(generador_archivos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }        
    }
}
