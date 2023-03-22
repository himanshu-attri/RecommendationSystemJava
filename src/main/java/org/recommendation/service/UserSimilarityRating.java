package org.recommendation.service;


import org.recommendation.model.Movie;
import org.recommendation.model.User;
import org.recommendation.model.Rating;
import org.recommendation.data.helper.UserDataHelper;
import org.recommendation.data.helper.MovieDataHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserSimilarityRating extends SimilaratiyRating {

    public UserSimilarityRating(final String id, final int neighborSize, final int minRater,final Filter f) {
        super(id, neighborSize, minRater, f);
    }

    private double getUserSimScore(final User user, final User other) {
        double similarityScore = 0.0;
        double similaritySquareSum = 0.0;
        double similaritySquareSumOther = 0.0;
        List<String> userMovies = user.getMoviesRated();
        double userAvg = user.getAvgRating();
        List<String> otherMovies = other.getMoviesRated();
        double otherAvg = other.getAvgRating();

        List<String>commonMovies = userMovies.stream().filter(otherMovies::contains).toList();
        int minNumCommon = commonMovies.size();
        for (String movie : commonMovies) {
            double userScore = user.getMovieRatingForUser(movie) - userAvg;
            double otherScore = other.getMovieRatingForUser(movie) - otherAvg;
            similarityScore += (userScore) * (otherScore);
            similaritySquareSum += Math.pow(userScore, 2);
            similaritySquareSumOther += Math.pow(otherScore, 2);
        }
        if (minNumCommon >= 2 && similarityScore != 0.0 && similaritySquareSum != 0.0 && similaritySquareSumOther != 0.0) {
            return similarityScore / (Math.sqrt(similaritySquareSum * similaritySquareSumOther));
        }
        return -100.0;
    }


    private List<Rating> getUserSimilarityScore() {
        List<Rating> similarityScore = new ArrayList<>();
        User user = UserDataHelper.getUser(super.getUserId());
        for (User other : UserDataHelper.getUsers()) {
            String otherId = other.getUserId();
            if (!otherId.equals(super.getUserId())) {
                double cosineScore = getUserSimScore(user, other);
                if (cosineScore != -100.0) {
                    similarityScore.add(new Rating(otherId, null, cosineScore));
                }
            }
        }
        Collections.sort(similarityScore, Collections.reverseOrder());
        return similarityScore;
    }

    @Override
    public List<Rating> getSimilarRatings() {
        List<Rating> similarityScore = getUserSimilarityScore();
        int numNeighors = Math.min(similarityScore.size(), super.getSimilarityNum());
        List<Rating> similarityRatingList = new ArrayList<>();
        List<Movie> movieList = MovieDataHelper.filterBy(super.getFilter(), UserDataHelper.getUser(super.getUserId()));
        Double userAvg = UserDataHelper.getUser(super.getUserId()).getAvgRating();
        for (Movie movie : movieList) {
            String movieId = movie.getId();
            int counter = 0;
            double norm = 0.0;
            double total = 0.0;
            for (int i = 0; i < numNeighors; i++) {
                Rating userRating = similarityScore.get(i);
                Double cosineScore = userRating.getRatingValue();
                String otherId = userRating.getUserId();
                User userOther = UserDataHelper.getUser(otherId);
                double otherAvg = userOther.getAvgRating();
                double rating = userOther.getMovieRatingForUser(movieId);
                if (rating != -1) {
                    counter++;
                    norm += Math.abs(cosineScore);
                    total += (rating - otherAvg) * cosineScore;
                }
            }
            if (counter >= super.getMinimalRater()) {
                double predRating = userAvg + (total / norm);
                similarityRatingList.add(new Rating(null, movieId, predRating));
            }
        }
        Collections.sort(similarityRatingList, Collections.reverseOrder());
        return similarityRatingList;
    }

}
