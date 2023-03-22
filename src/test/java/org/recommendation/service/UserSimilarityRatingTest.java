package org.recommendation.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.recommendation.data.helper.MovieDataHelper;
import org.recommendation.data.helper.UserDataHelper;
import org.recommendation.model.Movie;
import org.recommendation.model.User;

import java.util.ArrayList;
import java.util.HashMap;


@RunWith(MockitoJUnitRunner.class)
public class UserSimilarityRatingTest {
    private UserSimilarityRating userSimilarityRating;

    @Test
    public void testgetSimilarRatings(){
        MovieDataHelper.movieMap = new HashMap<>();
        UserDataHelper.userMap = new HashMap<>();
        User user1 = new User("123");
        user1.addRating("2323",4);
        user1.addRating("1222",5);

        User user2 = new User("1234");
        user2.addRating("2323",4);
        user2.addRating("1222",3);
        user2.addRating("1223",3);


        UserDataHelper.userMap.put("123",user1);
        UserDataHelper.userMap.put("1234",user2);

        MovieDataHelper.movieMap.put("2323",new Movie("2323","hi","1234",new ArrayList<>()));
        MovieDataHelper.movieMap.put("1222",new Movie("1222","hi","2020",new ArrayList<>()));
        MovieDataHelper.movieMap.put("1223",new Movie("1223","hi","2020",new ArrayList<>()));


        Filter filter = new UnWatchedMovieFilter();
        userSimilarityRating = new UserSimilarityRating("123",5,1,filter);
        Assert.assertNotNull(userSimilarityRating.getSimilarRatings());
    }
    @Test
    public void testgetSimilarRatingsWithInsufficientData(){
        MovieDataHelper.movieMap = new HashMap<>();
        UserDataHelper.userMap = new HashMap<>();
        User user1 = new User("123");
        user1.addRating("2323",4);

        User user2 = new User("1234");
        user2.addRating("2323",4);
        user2.addRating("1222",3);


        UserDataHelper.userMap.put("123",user1);
        UserDataHelper.userMap.put("1234",user2);

        MovieDataHelper.movieMap.put("2323",new Movie("2323","hi","1234",new ArrayList<>()));
        MovieDataHelper.movieMap.put("1222",new Movie("1222","hi","2020",new ArrayList<>()));


        Filter filter = new UnWatchedMovieFilter();
        userSimilarityRating = new UserSimilarityRating("123",5,1,filter);
        Assert.assertTrue(userSimilarityRating.getSimilarRatings().isEmpty());
    }
}