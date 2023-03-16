package org.recommendation.service;

public interface Filter {
    public boolean satisfy(final String movieId);
}
