import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class PredictState extends State {
    private Card _trumpCard;
    private Deque<Player> _player;
    private Map<Integer, Deque<Card>> _hands;

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

    }

    @Override
    public void onEnter() {
        prepareRound();
    }

    @Override
    public State transitionNext() {
        if ()
        return new 
    }
    
}
