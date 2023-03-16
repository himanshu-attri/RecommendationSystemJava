package org.recommendation.data;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

public abstract class DataInitializer {
    public BufferedReader dataReader(String fileName) {
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br;
        } catch (Exception e) {
            System.out.println("Exception while reading file: " + fileName);
        }
        return null;
    }

    abstract public Map<String, String[]> readAndCleanData(BufferedReader br);

    abstract public void writeData(XSSFWorkbook workbook, Map<String, String[]> dataStore);
}