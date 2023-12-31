import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public record Player(int id, ArrayDeque<Card> hand, List<RoundResult> results) {

    public Player(int playerId) {
        this(playerId, new ArrayDeque<>(), new ArrayList<>());
    }

    public void give(Card card) {
        hand.add(card);
    }

    public void drop(Card card) {
        hand.remove(card);
    }

    public void dropHand() {
        hand.clear();
    }

    public void addResult(RoundResult result) {
        results.add(result);
    }

    public int getFinalScore() {
        try {
            return results.get(results.size()-1).accumulatedPoints();
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Player { id: " + id + ", hand: + " + hand + "}";
    }
}
