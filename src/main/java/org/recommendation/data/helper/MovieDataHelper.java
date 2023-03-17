package org.recommendation.data.helper;

import org.recommendation.model.Movie;
import org.recommendation.model.User;
import org.recommendation.service.Filter;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieDataHelper {
    public static HashMap<String, Movie> movieMap = new HashMap<>();
    public static ArrayList<Movie> filterBy(final Filter f,final User user) {
        ArrayList<Movie> list = new ArrayList<Movie>();
        for (String movieId : movieMap.keySet()) {
            if (f.satisfy(movieId,user)) {
                list.add(movieMap.get(movieId));
            }
        }
        return list;
    }
}
