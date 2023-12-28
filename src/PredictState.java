import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PredictState extends State {
    private Card _trumpCard;
    private Map<Player, Integer> _predictions;

    public PredictState(WizardGame wizardGame) {
        super(wizardGame);
        _predictions = new HashMap<>();
        onEnter();
    }

    public void predict(Player player, int prediction) throws InvalidPredictionException {
        int activePlayerID = game.getActivePlayersID(_predictions.size());
        if (!game.getPlayers().contains(player)) {
            throw new InvalidPredictionException("Player is not participating in game");
        } else if (prediction < 0) {
            throw new InvalidPredictionException("Prediction cannot be negative");
        } else if (prediction > game.getTurnCounter()) {
            throw new InvalidPredictionException("Prediction cannot be greater than number of strikes this round");
        } else if (player.id != activePlayerID) {
            throw new InvalidPredictionException("Active player has ID " + activePlayerID + " not " + player.id);
        }
        _predictions.put(player, prediction);
    }

    @Override
    public void printStatus() {
        super.printStatus();
        System.out.println("Cards have been dealt to the " + game.getPlayerCount() + " players\n"
            + _predictions.size() + " out of " + game.getPlayerCount()
            + " players have made their predictions\n" + "Predictions:\n" + _predictions);
    }

    @Override
    public void onEnter() {
        // Empty player's hands
        for (Player player : game.getPlayers()) {
            player.dropHand();
        }
        // Shuffle deck
        List<Card> cardList = Arrays.asList(game.CARDS);
        Collections.shuffle(cardList);
        Deque<Card> deck = new ArrayDeque<Card>(cardList);
        // Deal cards
        for (int i = 0; i < game.getTurnCounter(); i++) {
            for (Player player : game.getPlayers()) {
                player.give(deck.poll());
            }
        }
        // select trumpcard (can be null)
        _trumpCard = deck.poll();
    }

    @Override
    public State transitionNext() throws TransitionCriteriaFailedException {
        if (_predictions.size() != game.getPlayerCount()) {
            throw new TransitionCriteriaFailedException("Not every player has made predictions");
        }
        return new StrikeState(super.game, _trumpCard, _predictions); 
    }
}
