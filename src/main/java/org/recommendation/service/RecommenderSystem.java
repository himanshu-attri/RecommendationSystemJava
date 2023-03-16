package org.recommendation.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.util.Map;
import org.recommendation.data.DataInitializer;
import org.recommendation.data.MovieDataInitializer;
import org.recommendation.data.UserDataInitilizer;
import org.recommendation.data.GenreDataInitializer;
import org.recommendation.data.helper.MovieDataHelper;
import org.recommendation.data.helper.UserDataHelper;
import org.recommendation.model.Movie;
import org.recommendation.model.User;

public class RecommenderSystem {
    public static void printRecommendationForUser(final String userId) {

        System.out.println("Starting recommendation process for user : "+userId);

        DataInitializer dataInitializer ;
        dataInitializer = new GenreDataInitializer();
        initializer(dataInitializer,GenreDataInitializer.genreFilePath);
        dataInitializer = new MovieDataInitializer();
        initializer(dataInitializer,MovieDataInitializer.movieFilePath);
        dataInitializer = new UserDataInitilizer();
        initializer(dataInitializer, UserDataInitilizer.ratingFilePath);
        ClientQueryHelper clientQueryHelper = new ClientQueryHelper();
//        Movie movie= clientQueryHelper.getTopMovieByGenre("Action");
        Movie yearBestMovie = clientQueryHelper.getTopRatedMovieByYear("1990");
        System.out.println(yearBestMovie.getId()+" " +yearBestMovie.getTitle()+" "+ yearBestMovie.getYear());
    }

    private static void initializer(final DataInitializer dataInitializer,final String filePath){
        BufferedReader br = dataInitializer.dataReader(filePath);
        Map<String,String[]> data = dataInitializer.readAndCleanData(br);
        XSSFWorkbook workbook = new XSSFWorkbook();
        dataInitializer.writeData(workbook,data);
    }

}
