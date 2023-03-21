package org.recommendation.data;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.recommendation.log.Logger;
import org.recommendation.log.SoutLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GenreDataInitializer extends DataInitializer {
    public static HashMap<String, String> GenereMap = new HashMap<>();
    private Logger logger = new SoutLogger();

    @Override
    public Map<String, String[]> readAndCleanData(final BufferedReader br) {
        String line;
        try {
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (data.length > 1) {
                    GenereMap.put(data[1], data[0]);
                }
            }
        } catch (Exception exception) {
            logger.error("GenreDataInitializer readAndCleanData()", exception);
        }
        return null;
    }

    @Override
    public void writeData(final XSSFWorkbook workbook, final Map<String, String[]> dataStore) {

    }
}
