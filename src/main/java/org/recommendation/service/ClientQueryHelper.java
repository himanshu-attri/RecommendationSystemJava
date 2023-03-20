package org.recommendation.service;

import org.recommendation.data.helper.MovieDataHelper;
import org.recommendation.data.helper.UserDataHelper;
import org.recommendation.log.Logger;
import org.recommendation.log.SoutLogger;
import org.recommendation.model.Genre;
import org.recommendation.model.Movie;
import org.recommendation.model.Rating;
import org.recommendation.model.User;

import java.util.*;
import java.util.stream.Collectors;

import static org.recommendation.data.helper.MovieDataHelper.movieMap;
import static org.recommendation.data.helper.UserDataHelper.userMap;

public class ClientQueryHelper {
    private final Logger logger = new SoutLogger();
    private List<Movie> queryReelatedMovies = new ArrayList<>();
    private HashMap<String, Double> movieScore = new HashMap<>();
    private HashMap<String, Integer> movieRaters = new HashMap<>();

    public Movie getTopMovieByGenre(final String genre) {
        queryReelatedMovies = movieMap.values().stream().filter(movie -> movie.getGenres().contains(genre)).collect(Collectors.toList());
        if (queryReelatedMovies.isEmpty()) {
            logger.warn("No such movie exist in dataset");
            return null;
        }
        return getMaxRatedMovie();
    }

    public Movie getTopMovieByYearAndGenre(final String genre, final String year) {
        logger.info("running getTopMovieByYearAndGenre with input "+genre+" "+year);
        queryReelatedMovies = movieMap.values().stream().filter(movie -> movie.getGenres().contains(Genre.valueOfLabel(genre)) && movie.getYear().equals(year)).
                collect(Collectors.toList());
        return getMaxRatedMovie();
    }
    public String getHighestRatedGenre(){
        HashMap<String, Double> genreScore = new HashMap<>();
        HashMap<String, Integer> genreRaters = new HashMap<>();
        for(User user:userMap.values()){
            for(String movie:user.getMoviesRated()){
                for (Genre genre:movieMap.get(movie).getGenres()){
                    genreScore.merge(genre.label,user.getMovieRatingForUser(movie),Double::sum);
                    genreRaters.merge(genre.label,1,Integer::sum);
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

    public Movie getMostWatchedMovie() {
        HashMap<String, Integer> movieToUserCount = new HashMap<>();
        for (User user : userMap.values()) {
            for (String movie : user.getMoviesRated()) {
                movieToUserCount.merge(movie, 1, Integer::sum);
            }
        }
        String mostWatchedMovieId = Collections.max(movieToUserCount.entrySet(), HashMap.Entry.comparingByValue()).getKey();
        logger.info(movieMap.get(mostWatchedMovieId).toString());
        return movieMap.get(mostWatchedMovieId);
    }

    public String getMostwatchedGenre() {
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

    public String getMostActiveUser() {
        Optional<User> user = userMap.values().stream().sorted(Comparator.comparingInt(User::numOfMoviesRated).reversed()).findFirst();
        return user.get().getUserId();
    }

    public Movie getTopRatedMovieByYear(final String year) {
        queryReelatedMovies = movieMap.values().stream().filter(movie -> year.equals(movie.getYear())).collect(Collectors.toList());
        return getMaxRatedMovie();
    }

    private Movie getMaxRatedMovie() {
        for (User user : userMap.values()) {
            for (Movie movie : queryReelatedMovies) {
                if (user.getMovieRatingForUser(movie.getId()) != -1) {
                    movieScore.merge(movie.getId(), user.getMovieRatingForUser(movie.getId()), Double::sum);
                    movieRaters.merge(movie.getId(), 1, Integer::sum);
                }
            }
        }
        Double maxRating = 0.0;
        String maxRatedMovieId = null;
        for (String movieId : movieScore.keySet()) {
            Double rating = movieScore.get(movieId) / movieRaters.get(movieId);
            logger.info(movieMap.get(movieId).getTitle() + " " + rating);
            if (rating > maxRating) {
                maxRating = rating;
                maxRatedMovieId = movieId;
            }
        }
        return movieMap.get(maxRatedMovieId);

    }

    public void printTop5RecommendationForUser(final String userId) {
        User newUser = UserDataHelper.getUser(userId);
        UnWatchedMovieFilter filter = new UnWatchedMovieFilter();
        UserSimilarityRating userSimilarityRating = new UserSimilarityRating(userId, 50, 5, filter);
        List<Rating> userRec = userSimilarityRating.getSimilarRatings();
        System.out.println("========== Final Result ==============");
        int counter = 0;
        for (Rating rating : userRec) {
            if (newUser.getMoviesRated().contains(rating.getMovieId())) {
                System.out.println("Mismatch found: " + rating.getMovieId());
            } else {
                logger.info(MovieDataHelper.movieMap.get(rating.getMovieId()).toString());
                counter++;
            }

            if (counter >= 5)
                break;
        }
    }
}
