import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class WizardGame {
    private int _turnCounter;
    private Deque<Player> _players; // order of players in terms of playing cards (starting strikes)
    private Deque<Player> _dealers; // order of players in terms of dealing (starting rounds)

    final Card[] CARDS;

    public WizardGame(int playerCount) {
        _turnCounter = 1;
        _players = new ArrayDeque<>();
        for (int i = 0; i < playerCount; i++) {
            _players.add(new Player(i));
        }
        _dealers = new ArrayDeque<>(_players);
        ArrayList<Card> cards = new ArrayList<>();
        for (CardValue val : CardValue.values()) {
            for (CardColor col : CardColor.values()) {
                cards.add(new Card(val, col));
            }
        }
        CARDS = (Card[])cards.toArray();
    }

    public Deque<Player> getPlayers() {
        return _players;
    }

    public int getPlayerCount() {
        return _players.size();
    }

    public int getActivePlayersID(int previousPlays) {
        return ((Player[])_players.toArray())[previousPlays % _players.size()].id;
    }

    public boolean isPlayerHandsEmpty() {
        for (Player player : _players) {
            if (!player.hand.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public int getTurnCounter() {
        return _turnCounter;
    }

    public void incrementTurnCounter() {
        _turnCounter++;
    }

    public boolean isValidCardChoice(Player player, Card cardToPlay, Card startingCard) {
        Deque<Card> hand = player.hand;
        if (cardToPlay.value() == CardValue.JESTER || cardToPlay.value() == CardValue.WIZARD) {
            return true;
        } else if (cardToPlay.color() == startingCard.color()) {
            return true;
        } else {
            for (Card card : hand) {
                if (card.color() == startingCard.color()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Player evaluateWinner(List<Play> plays, CardColor trumpColor, CardColor startingColor) {
        return plays.stream().sorted((Play a, Play b) -> {
            return a.getEvaluation(trumpColor, startingColor) - b.getEvaluation(trumpColor, startingColor);
        }).findFirst().get().player();
    }

    public void setStartingPlayer(Player player) {
        if (!_players.contains(player)) {
            throw new RuntimeException("Tried to set starting player to player not participating in game");
        }
        while (_players.peek().id != (player.id)) {
            _players.addLast(_players.poll());
        }
    }

    public void incrementDealer() {
        _dealers.addLast(_dealers.poll());
        setStartingPlayer(_dealers.peek());
    }
}
