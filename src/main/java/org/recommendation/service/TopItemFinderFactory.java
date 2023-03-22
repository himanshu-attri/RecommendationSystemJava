package org.recommendation.service;

public class TopItemFinderFactory {
    public static TopItemFinder getTopItemFinder(final String attribute) {
        if(AppConstant.GENRE.equals(attribute)){
            return new GenreTopItemFinder();
        }
        else if (AppConstant.YEAR.equals(attribute)) {
            return new YearTopItemFinder();
        }
        else if (AppConstant.MOVIE.equals(attribute)){
            return new MovieTopItemFinder();
        }
        else {
            return new UserTopItemFinder();
        }
    }
}
