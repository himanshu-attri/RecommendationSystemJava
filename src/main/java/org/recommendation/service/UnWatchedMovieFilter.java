package org.recommendation.service;

import org.recommendation.model.User;

import java.util.ArrayList;

public class UnWatchedMovieFilter implements Filter{
    private ArrayList<String> watched;
    @Override
    public boolean satisfy(final String movieId) {
        for (String movie : watched) {
            if (movie.equals(movieId)) {
                return false;
            }
        }

        return true;
    }

    public UnWatchedMovieFilter(final User user) {
        watched = user.getMoviesRated();
    }
}
