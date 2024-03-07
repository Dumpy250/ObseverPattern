import java.util.Scanner;


public class BasketballGameSimulation {
    public static void main(String[] args) {
        BasketballScoring basketballScoring = new BasketballScoring();

        // Register observers
        basketballScoring.registerObserver(new PredictionObserver());
        basketballScoring.registerObserver(new ScoreTrackingObserver());
        basketballScoring.registerObserver(new NewsGenerationObserver());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Start a new game");
            System.out.println("2. Simulate Quarter");
            System.out.println("3. Print Current Score");
            System.out.println("4. Print Prediction");
            System.out.println("5. Print Prediction Stats");
            System.out.println("6. Print Score Table");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    basketballScoring.startNewGame();
                    break;
                case 2:
                    basketballScoring.simulateQuarter();
                    break;
                case 3:
                    basketballScoring.printCurrentScore();
                    break;
                case 4:
                    basketballScoring.printPrediction();
                    break;
                case 5:
                    basketballScoring.printPredictionStats();
                    break;
                case 6:
                    basketballScoring.printScoreTable();
                    break;

                case 7:
                    ScoreTrackingObserver.printStats();
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
