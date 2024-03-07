import java.util.ArrayList;
import java.util.Scanner;

class BasketballScoring implements BasketballSubject {
    private int teamAScore;
    private int teamBScore;
    private ArrayList<BasketballObserver> observerList;
    private ArrayList<int[]> gameHistory;

    private int quarter;

    public BasketballScoring() {
        observerList = new ArrayList<>();
        gameHistory = new ArrayList<>();
        quarter = 1;
    }

    @Override
    public void registerObserver(BasketballObserver o) {
        observerList.add(o);
    }

    @Override
    public void unregisterObserver(BasketballObserver o) {
        observerList.remove(observerList.indexOf(o));
    }

    @Override
    public void notifyObservers() {
        for (BasketballObserver o : observerList) {
            o.update(teamAScore, teamBScore);
        }
    }

    @Override
    public void startNewGame() {
        System.out.println("Starting a new game!");
        if (teamAScore != 0 || teamBScore != 0) {
            gameHistory.add(new int[]{teamAScore, teamBScore});
        }
        teamAScore = 0;
        teamBScore = 0;
        quarter = 1;
        notifyObservers();
        for (BasketballObserver observer : observerList) {
            if (observer instanceof PredictionObserver) {
                ((PredictionObserver) observer).generateRandomPrediction();
            }
        }
        PredictionObserver.incrementTotalGames();  // Increment totalGames count
    }

    @Override
    public void simulateQuarter() {
        Scanner scanner = new Scanner(System.in);
        int pointDifference = 0;
        for (int i = 1; i <= 4; i++) {
            System.out.println("Start of Quarter " + i + ". Enter 'end' to finish the quarter: ");

            // Generate random prediction once per quarter
            for (BasketballObserver observer : observerList) {
                if (observer instanceof PredictionObserver) {
                    ((PredictionObserver) observer).generateRandomPrediction();
                }
            }

            while (!scanner.nextLine().equalsIgnoreCase("end")) {
                // Simulate gameplay logic (you can generate random scores or use stored results here)
                teamAScore += (int) (Math.random() * 20);
                teamBScore += (int) (Math.random() * 20);

                pointDifference = teamAScore - teamBScore;
                notifyObservers(); // Notify observers after each score update
                printCurrentScore(); // Print the current score after each update
            }
        }
        PredictionObserver.incrementTotalGames();
        System.out.println("End of the game!");
        if (pointDifference > 20) {
            System.out.println("landslide");
        } else if (pointDifference > 5) {
            System.out.println("contested");
        } else {
            System.out.println("tightly contested");
        }
    }

    @Override
    public void printCurrentScore() {
        System.out.println("Current Score: Team A " + teamAScore + " - Team B " + teamBScore);
    }

    @Override
    public void printPrediction() {
        System.out.println("Printing Prediction...");
        for (BasketballObserver observer : observerList) {
            if (observer instanceof PredictionObserver) {
                ((PredictionObserver) observer).printPrediction();
            }
        }
    }

    @Override
    public void printPredictionStats() {
        for (BasketballObserver observer : observerList) {
            if (observer instanceof PredictionObserver) {
                PredictionObserver.printPredictionStats();
                break;  // Assuming there's only one PredictionObserver
            }
        }
    }

    @Override
    public void printScoreTable() {
        System.out.println("Game History Table:");
        System.out.printf("%-15s %-15s %-15s\n", "Game", "Team A Score", "Team B Score");

        for (int i = 0; i < gameHistory.size(); i++) {
            int[] scores = gameHistory.get(i);
            System.out.printf("%-15d %-15d %-15d\n", i + 1, scores[0], scores[1]);
        }
    }
}