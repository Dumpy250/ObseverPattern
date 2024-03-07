import java.util.Random;

class PredictionObserver implements BasketballObserver {
    private static int predictedTeamAScore;
    private static int predictedTeamBScore;
    private static int correctPredictions;
    private static int totalGames;
    private static int totalPointError;
    private static final Random random = new Random();
    private static boolean predictionGenerated = false;

    @Override
    public void update(int teamAScore, int teamBScore) {
        // No need to generate new random values in the update method
        // Print the previously generated prediction
        System.out.println("Updating Prediction...");
        System.out.println("Predicted Final Score: Team A " + predictedTeamAScore + " - Team B " + predictedTeamBScore);

        // Check if the prediction was correct
        if (teamAScore == predictedTeamAScore && teamBScore == predictedTeamBScore) {
            correctPredictions++;
        }

        // Calculate point error and update total point error
        int pointError = Math.abs(teamAScore - predictedTeamAScore) + Math.abs(teamBScore - predictedTeamBScore);
        totalPointError += pointError;
    }

    // New method to generate random values once at the start of the game
    public static void generateRandomPrediction() {
        if (!predictionGenerated) {
            predictedTeamAScore = random.nextInt(101); // Generate a random integer between 0 and 100
            predictedTeamBScore = random.nextInt(101); // Generate a random integer between 0 and 100
            predictionGenerated = true;
        }
    }

    // New method to print the prediction
    public void printPrediction() {
        System.out.println("Current Prediction: Team A " + predictedTeamAScore + " - Team B " + predictedTeamBScore);
    }

    // New method to print prediction stats
    public static void printPredictionStats() {
        System.out.println("Prediction Stats:");
        System.out.println("Total Games: " + totalGames);
        System.out.println("Correct Predictions: " + correctPredictions);
        System.out.println("Average Point Error Rate: " + (totalGames == 0 ? 0 : totalPointError / totalGames));
    }

    // New method to reset prediction stats
    public static void resetPredictionStats() {
        correctPredictions = 0;
        totalGames = 0;
        totalPointError = 0;
    }

    // New method to increment total games
    public static void incrementTotalGames() {
        totalGames++;
    }
}

