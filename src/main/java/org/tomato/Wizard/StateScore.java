package main.java.org.tomato.Wizard;

import java.util.Iterator;

public class StateScore extends State {

    public StateScore(WizardGame wizardGame) {
        super(wizardGame);
    }

    public Iterator<Player> getOrderedPlayers() {
        return game.getPlayers().stream().sorted((p1, p2) -> p1.getFinalScore() - p2.getFinalScore()).iterator();
    }

    @Override
    public void printStatus() {
        super.printStatus();;
        Iterator<Player> orderedPlayers = getOrderedPlayers();
        while (orderedPlayers.hasNext()) {
            System.out.println(orderedPlayers.next());
        }
    }

    @Override
    public void onEnter() {
    }

    @Override
    public State transitionNext() throws TransitionCriteriaFailedException {
        if (game.getActivePlayer(0).results().size() == game.getMaxTurns()) {
            return null;    // Game over
        }
        game.incrementTurnCounter();
        game.incrementDealer();
        return new StatePredict(game);
    }
}
