package org.recommendation.service;

import org.recommendation.model.User;

public interface Filter {
    public boolean satisfy(final String movieId, final User user);
}
