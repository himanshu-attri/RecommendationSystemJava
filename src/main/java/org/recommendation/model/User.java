package org.recommendation.model;

import java.util.ArrayList;
import java.util.HashMap;

import static java.util.stream.Collectors.toCollection;

public class User extends BaseModel{
    private HashMap<String, Rating> movieMap; // it will store all movies rate by user with along with rating
    public User(final String userId){
        super(userId);
        movieMap = new HashMap<>();
    }
    public void addRating(final String movieId,final double rating){
        movieMap.put(movieId, new Rating(super.getUserId(),movieId, rating));
    }
    public ArrayList<String> getMoviesRated(){
        return movieMap.keySet().stream().collect(toCollection(ArrayList::new));
    }
    public int numOfMoviesRated(){
        return movieMap.size();
    }
    // this mehtod will be required in recommendation algorithm
    public double getAvgRating(){
        int num = numOfMoviesRated();
        if (num > 0) {
            double total = 0.0;
            for (Rating rating : movieMap.values()) {
                total += rating.getRatingValue();
            }
            return (double)(total /num);
        }
        return 0.0;
    }
    // this method will be used inside recommendation algorithm to get individual rating of a movie by user
    public double getMovieRatingForUser(final String movieId){
        if (movieMap.containsKey(movieId)){
            return movieMap.get(movieId).getRatingValue();
        }
        return -1;
    }
}
