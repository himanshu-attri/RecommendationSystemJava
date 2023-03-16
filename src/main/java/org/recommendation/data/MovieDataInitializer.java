package org.recommendation.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.recommendation.data.helper.MovieDataHelper;
import org.recommendation.model.Movie;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class MovieDataInitializer extends DataInitializer{

    public static final String movieFilePath = "src/main/java/org/recommendation/data/raw/movie.csv";

    @Override
    public Map<String, String[]> readAndCleanData(final BufferedReader br) {
        Map<String,String[]> dataStore = new TreeMap<>();
        String line;
        try {
            while ((line = br.readLine()) != null){
                String[] data = line.split("\\|");
                String [] filteredData = new String[data.length];
                int counter=0;
                for(int i=0;i<data.length;i++){
                    if(data[i].equals("")){
                        continue;
                    } else if(i==1){
                        int index = data[i].indexOf('(');
                        String title = index!=-1 ?data[i].substring(0,index):data[i];
                        String year = index!=-1? data[i].substring(index+1,index+5):data[i];
//                        System.out.println(title+" "+year);
                        filteredData[counter++]=title;
                        filteredData[counter++]=year;
                    }else{
                        filteredData[counter++]=data[i];
                    }
                }
                int totalItems = filteredData.length;
                if(totalItems>2){
                    dataStore.put(filteredData[0], Arrays.copyOfRange(filteredData, 1, totalItems));
                    populateMovieData(filteredData);
                }
//                String[] objects = dataStore.get(filteredData[0]);
//                System.out.println();
//                for(String object: objects)
//                    System.out.print(object);
//                System.out.println();
            }
        }catch (IOException ioException){
            System.out.println("Exception while cleaning data: "+ ioException.toString());
        }
        return dataStore;
    }
    private void populateMovieData(final String [] filteredData){
        if(filteredData.length<5){
            System.out.println("Insufficient data to map, skipping this row "+ filteredData[0]);
            return;
        }
        String movieId = filteredData[0];
        String title = filteredData[1];
        String year = filteredData[2];
        List<String> genres = new ArrayList<>();
        for (int i=5;i<= Math.min(23,filteredData.length);i++){
            if(Objects.nonNull(filteredData[i]) &&!filteredData[i].isEmpty()  &&Integer.parseInt(filteredData[i])==1)
                genres.add(GenreDataInitializer.GenereMap.get(Integer.toString(i-5)));
        }
//        System.out.println(movieId+" "+title+" "+ year+" "+ genres.toString());
        MovieDataHelper.movieMap.put(movieId, new Movie(movieId,title,year,genres));
    }

    @Override
    public void writeData(final XSSFWorkbook workbook, final Map<String, String[]> dataStore) {
        int rownum = 0;
        XSSFSheet sheet = workbook.createSheet("movie");
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
            FileOutputStream out = new FileOutputStream("src/main/java/org/recommendation/data/processed/movie.xlsx",false);
            workbook.write(out);
            workbook.close();
            out.close();
            System.out.println("movie.csv written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
