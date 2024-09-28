/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usb.planificador;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Lenovo
 */
public class gestor_csv {
    private List<Map<String, Object>> dataframe;
    private String[] headers;

    public gestor_csv() {
        dataframe = new ArrayList<>();
    }

    // Método para leer un archivo CSV y cargarlo en el dataframe
    public void readCSV(String filePath) throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            boolean isHeader = true;
            while ((nextLine = reader.readNext()) != null) {
                if (isHeader) {
                    headers = nextLine;  // Almacena los nombres de las columnas
                    isHeader = false;
                } else {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 0; i < nextLine.length; i++) {
                        row.put(headers[i], castValue(nextLine[i]));  // Almacena el valor con su tipo
                    }
                    dataframe.add(row);
                }
            }
        }
    }

    // Método para escribir en un archivo CSV desde el dataframe
    public void writeCSV(String filePath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Escribe las cabeceras
            writer.writeNext(headers);

            // Escribe las filas
            for (Map<String, Object> row : dataframe) {
                String[] rowData = new String[headers.length];
                for (int i = 0; i < headers.length; i++) {
                    rowData[i] = row.get(headers[i]).toString();
                }
                writer.writeNext(rowData);
            }
        }
    }

    // Método para actualizar una fila en el dataframe
    public void updateRow(int rowIndex, String columnName, Object newValue) {
        if (rowIndex >= 0 && rowIndex < dataframe.size()) {
            dataframe.get(rowIndex).put(columnName, newValue);
        } else {
            throw new IndexOutOfBoundsException("Índice de fila fuera de los límites.");
        }
    }

    // Método para mostrar el dataframe en consola
    public void printDataFrame() {
        //System.out.println(Arrays.toString(headers));
        for(String header : headers){
            System.out.print(header + "\t");
        }
        System.out.println();
        for (Map<String, Object> row : dataframe) {
            for (String header : headers) {
                System.out.print(row.get(header) + "\t");
            }
            System.out.println();
        }
    }

    // Método para castear valores a tipos adecuados (Integer, Double o String)
    private Object castValue(String value) {
        try {
            if (value.contains(".")) {
                return Double.parseDouble(value);
            } else {
                return Integer.parseInt(value);
            }
        } catch (NumberFormatException e) {
            return value;  // Si no es número, lo devuelve como String
        }
    }

        // Método para ordenar el dataframe en función de una columna específica
    public void sortDataFrameByColumn(String columnName) {
        Collections.sort(dataframe, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> row1, Map<String, Object> row2) {
                Object value1 = row1.get(columnName);
                Object value2 = row2.get(columnName);

                // Compara diferentes tipos de datos
                if (value1 instanceof Comparable && value2 instanceof Comparable) {
                    return ((Comparable) value1).compareTo(value2);
                }
                return 0;
            }
        });
    }

    
    // Método para obtener el dataframe como List<Map<String, Object>>
    public List<Map<String, Object>> getDataFrame() {
        return dataframe;
    }
}


