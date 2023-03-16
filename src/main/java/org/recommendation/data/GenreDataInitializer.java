package org.recommendation.data;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GenreDataInitializer extends DataInitializer{
    public static final String genreFilePath = "src/main/java/org/recommendation/data/raw/genre.csv";
    public static HashMap<String,String>GenereMap = new HashMap<>();
    @Override
    public Map<String, String[]> readAndCleanData(BufferedReader br) {
        String line;
        try {
            while ((line = br.readLine()) != null){
                String[] data = line.split("\\|");
                if(data.length>1){
                    GenereMap.put(data[1],data[0]);
                }
            }
        }catch (IOException ioException){
            System.out.println("Exception while cleaning genre data: "+ ioException.toString());
        }
        return null;
    }

    @Override
    public void writeData(XSSFWorkbook workbook, Map<String, String[]> dataStore) {

    }
}
