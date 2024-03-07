interface BasketballSubject {
    void registerObserver(BasketballObserver o);

    void unregisterObserver(BasketballObserver o);

    void notifyObservers();

    void startNewGame();

    void simulateQuarter();

    void printCurrentScore();

    void printPrediction();

    void printPredictionStats();

    void printScoreTable();

}
