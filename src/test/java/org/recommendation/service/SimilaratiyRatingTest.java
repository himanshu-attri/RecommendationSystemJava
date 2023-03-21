package org.recommendation.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SimilaratiyRatingTest {
    @Mock
    private SimilaratiyRating similaratiyRating;

    @Test
    public void testSimilarityRating(){
        Filter f = new UnWatchedMovieFilter();
        SimilaratiyRating similaratiyRating1 =  new UserSimilarityRating("123",23,4,f);
        assertNotNull(similaratiyRating1.getSimilarRatings());
        assertNotNull(similaratiyRating1.getUserId());
        assertNotNull(similaratiyRating1.getSimilarityNum());
        assertNotNull(similaratiyRating1.getFilter());
    }

}