class ScoreTrackingObserver implements BasketballObserver {
    private static int totalGames = 0;

    @Override
    public void update(int teamAScore, int teamBScore) {
        // Update score tracking logic
        totalGames++;
        System.out.println("Updating Score Tracking...");
    }

    public static void printStats() {
        System.out.println("Total Games: " + totalGames);
    }
}
