import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class PredictState extends State {
    private Card _trumpCard;
    private Map<Integer, Integer> _predictions;

    public PredictState(WizardGame wizardGame) {
        super(wizardGame);
        _predictions = new HashMap<>();
    }

    @Override
    public void printStatus() {
        super.printStatus();
        System.out.println("Cards have been dealt to the " + game.getPlayerCount() + " players.");
    }

    private void prepareRound() {
        Deque deck = 
        // deal cards

        // select trumpcard
    }

    @Override
    public void onEnter() {
        prepareRound();
    }

    @Override
    public State transitionNext() throws TransitionCriteriaFailedException {
        if (!_predictions.keySet().equals(game.getPlayerIDs())) {
            throw new TransitionCriteriaFailedException();
        }
        // return new StrikeState(super.game, _trumpCard, _predictions); 
    }
    
}
