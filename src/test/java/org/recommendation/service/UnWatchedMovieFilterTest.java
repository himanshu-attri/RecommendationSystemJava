package org.recommendation.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.recommendation.model.User;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class UnWatchedMovieFilterTest {

    @Mock
    private UnWatchedMovieFilter unWatchedMovieFilter;
    @Test
    public void satisfy() {
        User user = new User("123");
        assertFalse(unWatchedMovieFilter.satisfy("123",user));
    }
}