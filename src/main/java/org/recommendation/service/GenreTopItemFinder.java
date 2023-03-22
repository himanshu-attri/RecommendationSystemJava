package org.recommendation.service;

import org.recommendation.model.Genre;
import org.recommendation.model.Movie;
import org.recommendation.model.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.recommendation.data.helper.MovieDataHelper.movieMap;
import static org.recommendation.data.helper.UserDataHelper.userMap;

public class GenreTopItemFinder extends TopItemFinder {
    //most rated genre
    @Override
    String getTopItem(final String genre) {
        queryRelatedMovies = movieMap.values().stream().filter(movie -> movie.getGenres().contains(Genre.valueOfLabel(genre))).collect(Collectors.toList());
        if (queryRelatedMovies.isEmpty()) {
            logger.warn("No such movie exist in dataset");
            return null;
        }
        Movie movie = filterMaxRatedMovieOnScoreAndRaters();
        return Objects.nonNull(movie) ? movie.toString() : "No such movie exist";
    }

    public String getMostWatchedGenre() {
        HashMap<String, Integer> movieToGenreCount = new HashMap<>();
        for (User user : userMap.values()) {
            for (String movie : user.getMoviesRated()) {
                for (Genre genre : movieMap.get(movie).getGenres()) {
                    movieToGenreCount.merge(genre.label, 1, Integer::sum);
                }
            }
        }
        String mostWatchedGenre = Collections.max(movieToGenreCount.entrySet(), Map.Entry.comparingByValue()).getKey();
        logger.info(movieToGenreCount.entrySet().toString());
        return mostWatchedGenre;
    }

    public String getTopMovieByYearAndGenre(final String genre, final String year) {
        logger.info("running getTopMovieByYearAndGenre with input " + genre + " " + year);
        queryRelatedMovies = movieMap.values().stream().filter(movie -> movie.getGenres().contains(Genre.valueOfLabel(genre)) && movie.getYear().equals(year)).
                collect(Collectors.toList());
        Movie movie = filterMaxRatedMovieOnScoreAndRaters();
        return Objects.nonNull(movie) ? movie.toString() : "No such movie exist";
    }

    public String getHighestRatedGenre() {
        HashMap<String, Double> genreScore = new HashMap<>();
        HashMap<String, Integer> genreRaters = new HashMap<>();
        for (User user : userMap.values()) {
            for (String movie : user.getMoviesRated()) {
                for (Genre genre : movieMap.get(movie).getGenres()) {
                    genreScore.merge(genre.label, user.getMovieRatingForUser(movie), Double::sum);
                    genreRaters.merge(genre.label, 1, Integer::sum);
                }
            }
        }
        Double maxRating = 0.0;
        String maxRatedGenre = null;
        for (String genre : genreScore.keySet()) {
            Double rating = genreScore.get(genre) / genreRaters.get(genre);
            if (rating > maxRating) {
                maxRating = rating;
                maxRatedGenre = genre;
            }
        }
        return maxRatedGenre;
    }

}
