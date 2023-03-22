package org.recommendation.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RunWith(MockitoJUnitRunner.class)
public class MovieRecommenderSystemTest {
    @Test
    public void testPrintRecommendationsForInput1(){
        MovieRecommenderSystem movieRecommenderSystem = MovieRecommenderSystem.getRecommenderSystem();
        String input = "Action";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        movieRecommenderSystem.printRecommendations("1");
    }
    @Test
    public void testPrintRecommendationsForInput2(){
        MovieRecommenderSystem movieRecommenderSystem = MovieRecommenderSystem.getRecommenderSystem();
        String input = "1966";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        movieRecommenderSystem.printRecommendations("2");
    }
    @Test
    public void testPrintRecommendationsForInput3(){
        MovieRecommenderSystem movieRecommenderSystem = MovieRecommenderSystem.getRecommenderSystem();
        String input = "Action 1966";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        movieRecommenderSystem.printRecommendations("3");
    }
    @Test
    public void testPrintRecommendationsForInput4(){
        MovieRecommenderSystem movieRecommenderSystem = MovieRecommenderSystem.getRecommenderSystem();
        movieRecommenderSystem.printRecommendations("4");
    }
    @Test
    public void testPrintRecommendationsForInput5(){
        MovieRecommenderSystem movieRecommenderSystem = MovieRecommenderSystem.getRecommenderSystem();
        movieRecommenderSystem.printRecommendations("5");
    }
    @Test
    public void testPrintRecommendationsForInput6(){
        MovieRecommenderSystem movieRecommenderSystem = MovieRecommenderSystem.getRecommenderSystem();
        movieRecommenderSystem.printRecommendations("6");
    }
    @Test
    public void testPrintRecommendationsForInput7(){
        MovieRecommenderSystem movieRecommenderSystem = MovieRecommenderSystem.getRecommenderSystem();
        movieRecommenderSystem.printRecommendations("7");
    }
    @Test
    public void testPrintRecommendationsForInputUserId(){
        MovieRecommenderSystem movieRecommenderSystem = MovieRecommenderSystem.getRecommenderSystem();
        movieRecommenderSystem.printRecommendations("456");
    }
}