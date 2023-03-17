package org.recommendation.data;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.recommendation.log.Logger;
import org.recommendation.log.SoutLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

public abstract class DataInitializer {
    private final Logger logger = new SoutLogger();

    public BufferedReader dataReader(final String fileName) {
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br;
        } catch (Exception e) {
            logger.error("Exception while reading file: " + fileName, e);
        }
        return null;
    }

    abstract public Map<String, String[]> readAndCleanData(final BufferedReader br);

    abstract public void writeData(final XSSFWorkbook workbook, final Map<String, String[]> dataStore);
}