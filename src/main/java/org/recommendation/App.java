package org.recommendation;

import org.recommendation.service.RecommenderSystem;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println("========================================");
        System.out.println( "Stating our movie recommendation system" );
        System.out.println("========================================");

        System.out.println("Please enter userId: ");
//        Scanner scanner = new Scanner(System.in);
//        String userId = scanner.nextLine();
        String userId = "880";
        RecommenderSystem.printRecommendationForUser(userId);
    }
}
