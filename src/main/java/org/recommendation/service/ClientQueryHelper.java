package org.recommendation.service;

import org.recommendation.model.Movie;
import org.recommendation.model.User;

import java.util.ArrayList;
import java.util.HashMap;

import static org.recommendation.data.helper.MovieDataHelper.movieMap;
import static org.recommendation.data.helper.UserDataHelper.userMap;

public class ClientQueryHelper {
    private ArrayList<Movie> queryReelatedMovies =  new ArrayList<>();
    private HashMap<String, Double> movieScore =  new HashMap<>();
    private HashMap<String, Integer> movieRaters =  new HashMap<>();
    public Movie getTopMovieByGenre(final String genre){
        for(String key: movieMap.keySet()){
            if(checkGenre(genre,movieMap.get(key))){
                queryReelatedMovies.add(movieMap.get(key));
            }
        }
        if(queryReelatedMovies.size()==0){
            System.out.println("No such movie exist in dataset");
            return null;
        }
        Movie maxRatedMovie = getMaxRatedMovie();
        System.out.println("Max Rate movie is: "+ maxRatedMovie.getId()+" "+ maxRatedMovie.getTitle()+" "+ maxRatedMovie.getGenres());
        return maxRatedMovie;
    }
    public Movie getTopRatedMovieByYear(final String year){
        for(String key: movieMap.keySet()){
            if(checkYear(year,movieMap.get(key))){
                queryReelatedMovies.add(movieMap.get(key));
            }
        }
        return getMaxRatedMovie();
    }
    public Movie getMaxRatedMovie(){
        for(User user: userMap.values()){
            for(Movie movie: queryReelatedMovies){
                if(user.getMovieRatingForUser(movie.getId())!=-1){
                    movieScore.merge(movie.getId(),user.getMovieRatingForUser(movie.getId()),Double::sum);
                    movieRaters.merge(movie.getId(),1,Integer::sum);
                }
            }
        }
        Double maxRating =0.0;
        String maxRatedMovieId = null;
        for(String movieId:movieScore.keySet()){
            Double rating =movieScore.get(movieId)/ movieRaters.get(movieId);
            System.out.println(movieMap.get(movieId).getTitle()+" "+movieMap.get(movieId).getYear()+" "+ rating);
            if(rating>maxRating){
                maxRating = rating;
                maxRatedMovieId = movieId;
            }
        }
       return movieMap.get(maxRatedMovieId);

    }
    boolean checkGenre(final String genre, final Movie movie){
        return movie.getGenres().contains(genre);
    }
    boolean checkYear(final String year, final Movie movie){
        return movie.getYear().equals(year);
    }
}
