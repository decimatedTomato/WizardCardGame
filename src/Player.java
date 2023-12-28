import java.util.ArrayDeque;

public class Player {
    int id;
    ArrayDeque<Card> hand;

    public Player(int playerId) {
        id = playerId;
        hand = new ArrayDeque<>();
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

    @Override
    public String toString() {
        return "Player { id: " + id + ", hand: + " + hand + "}";
    }
}
