import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrikeState extends State {

    private Card _trumpCard;
    private Card _startingCard;
    private Map<Player, Integer> _predictions;
    private Map<Player, Integer> _results;

    private List<Play> _plays;

    public StrikeState(WizardGame wizardGame, Card trumpCard, Map<Player, Integer> predictions) {
        super(wizardGame);
        _trumpCard = trumpCard;
        _predictions = predictions;
        _results = new HashMap<>();
        for (Player player : game.getPlayers()) {
            _results.put(player, 0);
        }
        onEnter();
    }
    
    public StrikeState(WizardGame wizardGame, Card trumpCard, Map<Player, Integer> predictions, Map<Player, Integer> results) {
        super(wizardGame);
        _trumpCard = trumpCard;
        _predictions = predictions;
        _results = results;
        onEnter();
    }

    public void playCard(Player player, Card cardToPlay) throws InvalidPlayException {
        int activePlayerID = game.getActivePlayersID(_predictions.size());
        if (!game.getPlayers().contains(player)) {
            throw new InvalidPlayException("Player is not participating in game");
        } else if (!game.isValidCardChoice(player, cardToPlay, _startingCard)) {
            throw new InvalidPlayException("Card is not allowed due to your other options");
        } else if (player.id != activePlayerID) {
            throw new InvalidPlayException("Active player has ID " + activePlayerID + " not " + player.id);
        }
        if (_startingCard == null) {
            _startingCard = cardToPlay;
        }
        _plays.add(new Play(player, cardToPlay));
    }

    @Override
    public void printStatus() {
        super.printStatus();
        System.out.println(_plays.size() + " out of " + game.getPlayerCount()
            + " players have played their cards\n" + "Cards:\n" + _plays);
    }

    @Override
    public void onEnter() {
        _startingCard = null;
        _plays = new ArrayList<>();
    }

    @Override
    public State transitionNext() throws TransitionCriteriaFailedException{
        if (_plays.size() != game.getPlayerCount()) {
            throw new TransitionCriteriaFailedException("Not every player has played a card");
        }

        if (game.isPlayerHandsEmpty()) {
            return new EvaluateScoresState(game, _predictions, _results);
        } else {
            Player winner = game.evaluateWinner(_plays, _trumpCard.color(), _startingCard.color());
            _results.merge(winner, 1, Integer::sum);
            // rotate players so that winning player starts
            game.setStartingPlayer(winner);
            return new StrikeState(game, _trumpCard, _predictions, _results);
        }
    }

}
