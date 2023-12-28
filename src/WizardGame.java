import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class WizardGame {
    private State _state;
    private int _turnCounter;
    private Deque<Player> _players;

    final Card[] _cards;

    public WizardGame(int playerCount) {
        _turnCounter = 0;
        _state = new PredictState(this);
        _players = new ArrayDeque<>();
        for (int i = 0; i < playerCount; i++) {
            _players.add(new Player(i));
        }
        ArrayList<Card> cards = new ArrayList<>();
        for (CardValue val : CardValue.values()) {
            for (CardColor col : CardColor.values()) {
                cards.add(new Card(val, col));
            }
        }
        _cards = (Card[])cards.toArray();
    }

    public int getPlayerCount() {
        return _players.size();
    }

    public int getTurnCounter() {
        return _turnCounter;
    }

    public Set<Integer> getPlayerIDs() {
        Set<Integer> playerIDs = new HashSet<>();
        for (Player player : _players) {
            playerIDs.add(player.id);
        }
        return playerIDs;
    }
}
