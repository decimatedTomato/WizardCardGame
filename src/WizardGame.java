
public interface WizardGame {
    public int getTurnCounter();
    public int getMaxTurnCount();
    public void dealCards();
    public Card drawTrump();
    public void setRoundData(RoundData roundData);
    public void evaluateStrike();
    public void setChosenCards(int[] chosenCardInput);
    public void evaluateRound();
    public void incrementTurnCounter();
    public void shuffleDeck();
    public void validateCardChoice(int i, int position, int[] chosenCards);
}
