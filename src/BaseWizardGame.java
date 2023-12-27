import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BaseWizardGame implements WizardGame, Cloneable {

    private int _turnCounter;
    private final Deque<Player> _players;
    private final Score[][] _scoreSheet;
    private final Card[] _cards;

    private RoundData _roundData;
    private Queue<Card> _deck;

    public BaseWizardGame(int playerCount) {
        _turnCounter = 1;
        _players = new ArrayDeque<>();
        for (int i = 0; i < playerCount; i++) {
            _players.add(new Player(i));
        }

        final int cardCount = Color.values().length * Value.values().length;    // 60 unique cards
        final int totalTurns = cardCount / playerCount;
        _scoreSheet = new Score[totalTurns][playerCount];
        
        ArrayList<Card> cards = new ArrayList<>();
        for (Value val : Value.values()) {
            for (Color col : Color.values()) {
                cards.add(new Card(val, col));
            }
        }
        _cards = (Card[])cards.toArray();
    }

    @Override
    public int getTurnCounter() {
        return _turnCounter;
    }

    @Override
    public void incrementTurnCounter() {
        _turnCounter++;
    }

    @Override
    public int getMaxTurnCount() {
        return _scoreSheet.length;
    }

    @Override
    public void shuffleDeck() {
        List<Card> cardList = Arrays.asList(_cards);
        Collections.shuffle(cardList);
        _deck = new ArrayDeque<Card>(cardList);
    }

    @Override
    public void dealCards() {
        for (int i = 0; i < _turnCounter; i++) {
            for (Player player : _players) {
                player.giveCard(_deck.poll());
            }
        }
    }

    @Override
    public Card drawTrump() {
        return _deck.poll();
    }

    @Override
    public void setRoundData(RoundData roundData) {
        _roundData = roundData;
    }

    @Override
    public void evaluateStrike() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'evaluateStrike'");
    }

    @Override
    public void evaluateRound() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'evaluateRound'");
    }

    @Override
    public void setChosenCards(int[] chosenCardInput) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setChosenCards'");
    }
}
