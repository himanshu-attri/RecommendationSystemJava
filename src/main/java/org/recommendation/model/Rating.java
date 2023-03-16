package org.recommendation.model;

public class Rating {
    private String userId;
    private String movieId;
    private double ratingValue;
    private Long timestamp;
    public Rating(final String userId, final String movieId, final double ratingValue, final Long timestamp) {
        this.userId = userId;
        this.movieId = movieId;
        this.ratingValue = ratingValue;
        this.timestamp = timestamp;
    }
    public Rating(String userId, String movieId, double ratingValue) {
        this.userId = userId;
        this.movieId = movieId;
        this.ratingValue = ratingValue;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public double getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(double ratingValue) {
        this.ratingValue = ratingValue;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
