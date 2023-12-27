import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> _hand;
    private int _id;

    public Player(int id) {
        _hand = new ArrayList<>();
        _id = id;
    }

    public void giveCard(Card card) {
        _hand.add(card);
    }

    public Card takeCard(int handPosition) throws IndexOutOfBoundsException {
        return _hand.remove(handPosition);
    } 
}
