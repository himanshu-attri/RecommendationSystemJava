package org.recommendation.service;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Stating our movie recommendation system");
        System.out.println("========================================");

        System.out.println("Please enter input \n" +
                "1 for Top Movie By Genre\n" +
                "2 for Top Movie By Year\n" +
                "3 for Top Movie By Year & Genre\n" +
                "4 for Most watched Movie\n" +
                "5 for Most watched Genre\n" +
                "6 for Highest rated Genre\n" +
                "7 for Most Active User: \n" +
                "UserId for Top5 recommendation");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        MovieRecommenderSystem.getRecommenderSystem().printRecommendations(userInput);
    }
}
