package org.recommendation.service;

import org.recommendation.model.Movie;

import java.util.Objects;
import java.util.stream.Collectors;

import static org.recommendation.data.helper.MovieDataHelper.movieMap;

public class YearTopItemFinder extends TopItemFinder {
    @Override
    String getTopItem(final String year) {
        queryRelatedMovies = movieMap.values().stream().filter(movie -> year.equals(movie.getYear())).collect(Collectors.toList());
        Movie movie = filterMaxRatedMovieOnScoreAndRaters();
        return Objects.nonNull(movie) ? movie.toString() : "No such movie exist";
    }
}
