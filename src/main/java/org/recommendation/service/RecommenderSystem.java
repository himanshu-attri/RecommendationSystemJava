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
import org.recommendation.log.Logger;
import org.recommendation.log.SoutLogger;
import org.recommendation.model.Movie;
import org.recommendation.model.User;

public class RecommenderSystem {
    private static final String MOVIEFILEPATH = "src/main/java/org/recommendation/data/raw/movie.csv";
    private static final String GENREFILEPATH = "src/main/java/org/recommendation/data/raw/genre.csv";
    public static final String RATINGFILEPATH = "src/main/java/org/recommendation/data/raw/ratings.csv";



    public static void printRecommendationForUser(final String userId) {
        Logger logger = new SoutLogger();
        logger.info("Starting recommendation process for user : "+userId);

        DataInitializer dataInitializer ;
        dataInitializer = new GenreDataInitializer();
        initializer(dataInitializer,GENREFILEPATH);
        dataInitializer = new MovieDataInitializer();
        initializer(dataInitializer,MOVIEFILEPATH);
        dataInitializer = new UserDataInitilizer();
        initializer(dataInitializer, RATINGFILEPATH);
        ClientQueryHelper clientQueryHelper = new ClientQueryHelper();
//        Movie movie= clientQueryHelper.getTopMovieByGenre("Action");
        Movie yearBestMovie = clientQueryHelper.getTopRatedMovieByYear("1990");
        logger.warn("Year Best Movie "+yearBestMovie.toString());
    }

    private static void initializer(final DataInitializer dataInitializer,final String filePath){
        BufferedReader br = dataInitializer.dataReader(filePath);
        Map<String,String[]> data = dataInitializer.readAndCleanData(br);
        XSSFWorkbook workbook = new XSSFWorkbook();
        dataInitializer.writeData(workbook,data);
    }

}
