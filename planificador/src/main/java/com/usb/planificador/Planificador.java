/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.usb.planificador;

import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 *
 * @author Lenovo
 */
public class Planificador {

    public static void main(String[] args) throws IOException, FileNotFoundException, CsvValidationException {
        //System.out.println("Hello World!");
        
        //generador_archivos instancia = new generador_archivos();
        //instancia.test();
        gestor_csv bash = new gestor_csv();
        bash.readCSV(".\\bash.txt");
        //bash.printDataFrame();
        bash.sortDataFrameByColumn("inicio");
        bash.printDataFrame();
    }
}
