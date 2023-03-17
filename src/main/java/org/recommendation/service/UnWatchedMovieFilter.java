package org.recommendation.service;

import org.recommendation.model.User;

import java.util.ArrayList;

public class UnWatchedMovieFilter implements Filter{
    @Override
    public boolean satisfy(final String movieId,final User user) {
        return user.checkIfMovieRatedByUser(movieId);
    }
}
