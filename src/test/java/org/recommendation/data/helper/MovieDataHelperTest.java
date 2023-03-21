package org.recommendation.data.helper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.recommendation.model.Movie;
import org.recommendation.model.User;
import org.recommendation.service.Filter;
import org.recommendation.service.UnWatchedMovieFilter;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class MovieDataHelperTest {
    @Mock
    private MovieDataHelper movieDataHelper;

    @Test
    public void testFilterBy() {
        final Filter f = new UnWatchedMovieFilter();
        final User user = new User("231");
        ArrayList<Movie> list =MovieDataHelper.filterBy(f, user);
        Assert.assertNotNull(list);
    }

}