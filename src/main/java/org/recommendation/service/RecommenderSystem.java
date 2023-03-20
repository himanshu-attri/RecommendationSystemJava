package org.recommendation.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import org.recommendation.data.DataInitializer;
import org.recommendation.data.MovieDataInitializer;
import org.recommendation.data.UserDataInitilizer;
import org.recommendation.data.GenreDataInitializer;
import org.recommendation.data.helper.MovieDataHelper;
import org.recommendation.data.helper.UserDataHelper;
import org.recommendation.log.Logger;
import org.recommendation.log.SoutLogger;
import org.recommendation.model.Movie;
import org.recommendation.model.Rating;
import org.recommendation.model.User;

public class RecommenderSystem {
    // using singleton design pattern
    private static RecommenderSystem recommenderSystem;
    private RecommenderSystem(){}
    private static final String MOVIEFILEPATH = "src/main/java/org/recommendation/data/raw/movie.csv";
    private static final String GENREFILEPATH = "src/main/java/org/recommendation/data/raw/genre.csv";
    private static final String RATINGFILEPATH = "src/main/java/org/recommendation/data/raw/ratings.csv";


    public static RecommenderSystem getRecommenderSystem(){
        if(Objects.isNull(recommenderSystem)){
            recommenderSystem = new RecommenderSystem();
        }
        return recommenderSystem;
    }
    protected void printRecommendationForUser(final String userInput) {
        Logger logger = new SoutLogger();
        logger.info("Hold on for a while, starting recommendation process for your inputs");
        Scanner scanner = new Scanner(System.in);
        DataInitializer dataInitializer;
        dataInitializer = new GenreDataInitializer();
        initializer(dataInitializer, GENREFILEPATH);
        dataInitializer = new MovieDataInitializer();
        initializer(dataInitializer, MOVIEFILEPATH);
        dataInitializer = new UserDataInitilizer();
        initializer(dataInitializer, RATINGFILEPATH);
        ClientQueryHelper clientQueryHelper = new ClientQueryHelper();
        Movie result = null;
        String input = userInput;
        switch (userInput) {
            case "1":
                logger.info("Enter Genre");
                input = scanner.nextLine();
                result = clientQueryHelper.getTopMovieByGenre(input);
                break;
            case "2":
                logger.info("Enter Year");
                input = scanner.nextLine();
                result = clientQueryHelper.getTopRatedMovieByYear(input);
                break;
            case "3":
                logger.info("Enter genre and year");
                input = scanner.nextLine();
                String[] inputs = input.split("\\s+");
                result = clientQueryHelper.getTopMovieByYearAndGenre(inputs[0],inputs[1]);
                break;
            case "4":
                logger.info("Most watched Movie");
                result = clientQueryHelper.getMostWatchedMovie();
                break;
            case "5":
                logger.info("Most watched genre");
                logger.info(clientQueryHelper.getMostwatchedGenre());
                break;
            case "6":
                logger.info("Highest Rated Genre");
                logger.info(clientQueryHelper.getHighestRatedGenre());
                break;
            case "7":
                logger.info("Most active user");
                logger.info(clientQueryHelper.getMostActiveUser());
                break;
            default:
                logger.info("Processing userid for top5 recommendation");
                clientQueryHelper.printTop5RecommendationForUser(input);


        }
        if (Objects.nonNull(result))
            logger.info(result.toString());
    }

    private static void initializer(final DataInitializer dataInitializer, final String filePath) {
        BufferedReader br = dataInitializer.dataReader(filePath);
        Map<String, String[]> data = dataInitializer.readAndCleanData(br);
        XSSFWorkbook workbook = new XSSFWorkbook();
        dataInitializer.writeData(workbook, data);
    }

}
