import java.util.ArrayDeque;
import java.util.Deque;

public class WizardGame {
    private int _playerCount;
    private State _state;
    
    private Deque<Player> _players;

    public WizardGame(int playerCount) {
        _playerCount = playerCount;
        _players = new ArrayDeque<>();
        for (int i = 0; i < playerCount; i++) {
            _players.add(new Player(i));
        }
    }

    public String getPlayerCount() {
        return null;
    }

    public String getTurnCounter() {
        return null;
    }

    // ArrayList<Card> cards = new ArrayList<>();
    //     for (Value val : Value.values()) {
    //         for (Color col : Color.values()) {
    //             cards.add(new Card(val, col));
    //         }
    //     }
    //     _cards = (Card[])cards.toArray();

    //     public void shuffleDeck() {
    //     
    // }

    // @Override
    // public void dealCards() {
    //     for (int i = 0; i < _turnCounter; i++) {
    //         for (Player player : _players) {
    //             player.giveCard(_deck.poll());
    //         }
    //     }
    // }
}
