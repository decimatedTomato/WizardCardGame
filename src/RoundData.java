/*
This class serves to contain the data for a single round
This includes:
- Predictions made by each player
- Number of strikes won by each player
- Tump card
*/
// It was tempting to make this into a record but I feel like the constant reallocation is bad since number of strikes is updated frequently
public class RoundData {
    final int[] predictions;
    final int[] strikesWon;
    final Card trump;

    public RoundData(int[] playerPredictions, Card trumpCard) {
        predictions = playerPredictions;
        strikesWon = new int[playerPredictions.length];
        for (int i = 0; i < strikesWon.length; i++) {
            strikesWon[i] = 0;
        }
        trump = trumpCard;
    }

    public void incrementStrikes(int strikeWinner) {
        strikesWon[strikeWinner]++;
    }
}
