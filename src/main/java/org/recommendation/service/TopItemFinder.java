package org.recommendation.service;

import org.recommendation.log.Logger;
import org.recommendation.log.SoutLogger;
import org.recommendation.model.Movie;
import org.recommendation.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.recommendation.data.helper.MovieDataHelper.movieMap;
import static org.recommendation.data.helper.UserDataHelper.userMap;

public abstract class TopItemFinder {
    protected final Logger logger = new SoutLogger();
    protected List<Movie> queryRelatedMovies = new ArrayList<>();
    protected HashMap<String, Double> movieScore = new HashMap<>();
    protected HashMap<String, Integer> movieRaters = new HashMap<>();

    abstract String getTopItem(final String input);

    protected Movie filterMaxRatedMovieOnScoreAndRaters() {
        for (User user : userMap.values()) {
            for (Movie movie : queryRelatedMovies) {
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
