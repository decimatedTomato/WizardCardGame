import java.util.ArrayDeque;

public class Player {
    int id;
    ArrayDeque<Card> hand;

    public Player(int playerId) {
        id = playerId;
        hand = new ArrayDeque<>();
    }
}
