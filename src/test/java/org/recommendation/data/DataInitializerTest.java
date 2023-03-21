package org.recommendation.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;

@RunWith(MockitoJUnitRunner.class)
public class DataInitializerTest {
    @Test
    public void testDataReader() {
        DataInitializer dataInitializer = new MovieDataInitializer();
        BufferedReader br = dataInitializer.dataReader("src/main/java/org/recommendation/data/raw/movie.csv");
        Assert.assertNotNull(br);
    }
    @Test
    public void testDataReaderWithException() {
        DataInitializer dataInitializer = new MovieDataInitializer();
        BufferedReader br = dataInitializer.dataReader("invalid/movie.csv");
        Assert.assertNull(br);
    }
}