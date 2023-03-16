package org.recommendation.service;

public interface Filter {
    public boolean satisfy(String movieId);
}
