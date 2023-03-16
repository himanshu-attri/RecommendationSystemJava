package org.recommendation.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.recommendation.data.helper.UserDataHelper;
import org.recommendation.model.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import static org.recommendation.data.helper.UserDataHelper.userMap;

public class UserDataInitilizer extends DataInitializer{
    public static final String ratingFilePath = "src/main/java/org/recommendation/data/raw/ratings.csv";

    @Override
    public Map<String, String[]> readAndCleanData(final BufferedReader br) {
        Map<String,String[]> dataStore = new TreeMap<>();
        String line;
        try {
            Integer counter=0;
            while ((line = br.readLine()) != null){
                String[] data = line.split("\\s+");
//                for(String item:data)
//                    System.out.print(item);
                int totalItems = data.length;
                if(totalItems>1){
                    dataStore.put(counter.toString(),data);
                    populateUserData(data);
                }
//                String[] objects = dataStore.get(counter.toString());
//                System.out.println();
//                for(String object: objects)
//                    System.out.print(object+ " ");
//                System.out.println();

                counter++;
            }
        }catch (IOException ioException){
            System.out.println("Exception while cleaning data: "+ ioException.toString());
        }
        return dataStore;
    }
    private void populateUserData(final String [] filteredData) {
        String userId = filteredData[0];
        String movieId = filteredData[1];
        Double rating = Double.parseDouble(filteredData[2]);
        if (!userMap.containsKey(userId)){
            userMap.put(userId, new User(userId));
        }
        userMap.get(userId).addRating(movieId, rating);
    }


        @Override
    public void writeData(final XSSFWorkbook workbook, final Map<String, String[]> dataStore) {
        int rownum = 0;
        XSSFSheet sheet = workbook.createSheet("ratings");
        System.out.println("Total Items received for writing in rating.xlxs: "+dataStore.size());
        for (String key:dataStore.keySet()){
            Row row = sheet.createRow(rownum++);
            String[] objArr = dataStore.get(key);
            Cell cell1 = row.createCell(0);
            int cellnum = 1;
            cell1.setCellValue(key);
            for(String obj: objArr){
                Cell cell = row.createCell(cellnum++);
                cell.setCellValue(obj);
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(new File("src/main/java/org/recommendation/data/processed/ratings.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("ratings.csv written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
