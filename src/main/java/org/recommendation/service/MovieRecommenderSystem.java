package org.recommendation.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import org.recommendation.data.DataInitializer;
import org.recommendation.data.MovieDataInitializer;
import org.recommendation.data.UserDataInitilizer;
import org.recommendation.data.GenreDataInitializer;
import org.recommendation.log.Logger;
import org.recommendation.log.SoutLogger;

public class MovieRecommenderSystem implements RecommenderSystem {
    // using singleton design pattern
    private static MovieRecommenderSystem recommenderSystem;

    private MovieRecommenderSystem() {
    }

    private static final String MOVIEFILEPATH = "src/main/java/org/recommendation/data/raw/movie.csv";
    private static final String GENREFILEPATH = "src/main/java/org/recommendation/data/raw/genre.csv";
    private static final String RATINGFILEPATH = "src/main/java/org/recommendation/data/raw/ratings.csv";


    public static MovieRecommenderSystem getRecommenderSystem() {
        if (Objects.isNull(recommenderSystem)) {
            recommenderSystem = new MovieRecommenderSystem();
        }
        return recommenderSystem;
    }

    public void printRecommendations(final String userInput) {
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
        String result = null;
        String input = userInput;
        TopItemFinder topItemFinder;
        switch (userInput) {
            case "1":
                logger.info("Enter Genre");
                input = scanner.nextLine();
                topItemFinder = TopItemFinderFactory.getTopItemFinder(AppConstant.GENRE);
                result = topItemFinder.getTopItem(input);
                break;
            case "2":
                logger.info("Enter Year");
                input = scanner.nextLine();
                topItemFinder = TopItemFinderFactory.getTopItemFinder(AppConstant.YEAR);
                result = topItemFinder.getTopItem(input);
                break;
            case "3":
                logger.info("Enter genre and year");
                input = scanner.nextLine();
                String[] inputs = input.split("\\s+");
                GenreTopItemFinder genreTopItem = new GenreTopItemFinder();
                result = genreTopItem.getTopMovieByYearAndGenre(inputs[0], inputs[1]);
                break;
            case "4":
                logger.info("Most watched Movie");
                MovieTopItemFinder movieTopItems = new MovieTopItemFinder();
                result = movieTopItems.getMostWatchedMovie();
                break;
            case "5":
                logger.info("Most watched genre");
                GenreTopItemFinder genreTopItem1 = new GenreTopItemFinder();
                logger.info(genreTopItem1.getMostWatchedGenre());
                break;
            case "6":
                logger.info("Highest Rated Genre");
                GenreTopItemFinder genreTopItem2 = new GenreTopItemFinder();
                logger.info(genreTopItem2.getHighestRatedGenre());
                break;
            case "7":
                logger.info("Most active user");
                topItemFinder = TopItemFinderFactory.getTopItemFinder(AppConstant.USER);
                logger.info(topItemFinder.getTopItem(null));
                break;
            default:
                logger.info("Processing userid for top5 recommendation");
                MovieTopItemFinder movieTopItems2 = new MovieTopItemFinder();
                movieTopItems2.printTop5RecommendationForUser(input);


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
