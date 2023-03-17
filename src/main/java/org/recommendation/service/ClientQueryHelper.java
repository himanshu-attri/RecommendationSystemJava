package org.recommendation.service;

import org.recommendation.log.Logger;
import org.recommendation.log.SoutLogger;
import org.recommendation.model.Movie;
import org.recommendation.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.recommendation.data.helper.MovieDataHelper.movieMap;
import static org.recommendation.data.helper.UserDataHelper.userMap;

public class ClientQueryHelper {
    private final Logger logger = new SoutLogger();
    private List<Movie> queryReelatedMovies = new ArrayList<>();
    private HashMap<String, Double> movieScore = new HashMap<>();
    private HashMap<String, Integer> movieRaters = new HashMap<>();

    public Movie getTopMovieByGenre(final String genre) {
        queryReelatedMovies = movieMap.values().stream().filter(movie -> movie.getGenres().contains(genre)).collect(Collectors.toList());
        if (queryReelatedMovies.isEmpty()) {
            logger.warn("No such movie exist in dataset");
            return null;
        }
        return getMaxRatedMovie();
    }

    public Movie getTopRatedMovieByYear(final String year) {
        queryReelatedMovies = movieMap.values().stream().filter(movie -> year.equals(movie.getYear())).collect(Collectors.toList());
        return getMaxRatedMovie();
    }

    public Movie getMaxRatedMovie() {
        for (User user : userMap.values()) {
            for (Movie movie : queryReelatedMovies) {
                if (user.getMovieRatingForUser(movie.getId()) != -1) {
                    movieScore.merge(movie.getId(), user.getMovieRatingForUser(movie.getId()), Double::sum);
                    movieRaters.merge(movie.getId(), 1, Integer::sum);
                }
            }
        }
        Double maxRating = 0.0;
        String maxRatedMovieId = null;
        for (String movieId : movieScore.keySet()) {
            Double rating = movieScore.get(movieId) / movieRaters.get(movieId);
            logger.info(movieMap.get(movieId).getTitle() + " " + rating);
            if (rating > maxRating) {
                maxRating = rating;
                maxRatedMovieId = movieId;
            }
        }
        return movieMap.get(maxRatedMovieId);

    }
}
