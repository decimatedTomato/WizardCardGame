package main.java.org.tomato.Wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StateStrike extends State {

    private Card _trumpCard;
    private Card _startingCard;
    private Map<Player, Integer> _predictions;
    private Map<Player, Integer> _results;

    private List<Play> _plays;

    public StateStrike(WizardGame wizardGame, Card trumpCard, Map<Player, Integer> predictions) {
        super(wizardGame);
        _trumpCard = trumpCard;
        _predictions = predictions;
        _results = new HashMap<>();
        for (Player player : game.getPlayers()) {
            _results.put(player, 0);
        }
        onEnter();
    }
    
    public StateStrike(WizardGame wizardGame, Card trumpCard, Map<Player, Integer> predictions, Map<Player, Integer> results) {
        super(wizardGame);
        _trumpCard = trumpCard;
        _predictions = predictions;
        _results = results;
        onEnter();
    }

    public void playCard(Player player, Card cardToPlay) throws InvalidPlayException {
        Player activePlayer = game.getActivePlayer(_predictions.size());
        if (!game.getPlayers().contains(player)) {
            throw new InvalidPlayException("Player is not participating in game");
        } else if (!game.isValidCardChoice(player, cardToPlay, _startingCard)) {
            throw new InvalidPlayException("Card is not allowed due to your other options");
        } else if (!player.equals(activePlayer)) {
            throw new InvalidPlayException("Active player has ID " + activePlayer.id() + " not " + player.id());
        }
        if (_startingCard == null) {
            _startingCard = cardToPlay;
        }
        _plays.add(new Play(player, cardToPlay));
    }

    public List<Card> getHand(Player player) {
        return player.hand().stream().toList();
    }

    private int computeResult(Player player) {
        final int predictedStrikes = _predictions.get(player);
        final int predictionError = Math.abs(predictedStrikes - _results.get(player));
        if (predictionError == 0) {
            return 20 + 10 * predictedStrikes;
        }
        return -10 * predictionError;
    }

    public Iterator<Player> getPlayers() {
        Collection<Player> playersNotPlayedYet = new ArrayList<>();
        for (Player player : game.getPlayers()) {
            boolean hasPlayed = false;
            for (Play play : _plays) {
                if (play.player().equals(player)) {
                    hasPlayed = true;
                    break;
                }
            }
            if (!hasPlayed) {
                playersNotPlayedYet.add(player);
            }
        }
        return playersNotPlayedYet.iterator();
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
            // add scores to players
            for (Player player : game.getPlayers()) {
                player.addResult(new RoundResult(_predictions.get(player), computeResult(player)));
            }
            return new StateScore(game);
        } else {
            Player winner = game.evaluateWinner(_plays, _trumpCard.color(), _startingCard.color());
            _results.merge(winner, 1, Integer::sum);
            // rotate players so that winning player starts
            game.setStartingPlayer(winner);
            return new StateStrike(game, _trumpCard, _predictions, _results);
        }
    }

}
