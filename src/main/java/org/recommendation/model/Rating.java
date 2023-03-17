package org.recommendation.model;

public class Rating extends BaseModel {
    private String movieId;
    private double ratingValue;
    private Long timestamp;

    public Rating(final String userId, final String movieId, final double ratingValue, final Long timestamp) {
        super(userId);
        this.movieId = movieId;
        this.ratingValue = ratingValue;
        this.timestamp = timestamp;
    }

    public Rating(final String userId, final String movieId, final double ratingValue) {
        super(userId);
        this.movieId = movieId;
        this.ratingValue = ratingValue;
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

    @Override
    public String toString() {
        return "Rating{" +
                "UserId='" + super.getUserId() + '\'' +
                "movieId='" + this.movieId + '\'' +
                ", ratingValue=" + this.ratingValue +
                ", timestamp=" + this.timestamp +
                '}';
    }
}
