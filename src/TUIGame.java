import java.util.Scanner;

public class TUIGame implements CardGame {
    private State _game;

    public TUIGame(int playerCount) {
        _game = new StatePredict(new WizardGame(playerCount));
    }

    private void predict(Scanner s) throws InvalidPredictionException {
        
        ((StatePredict) _game).predict(null, -1);   //TODO get input
    }

    @Override
    public void start() {
        Scanner s = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("""
                        Action to perform:
                        0. Exit
                        1. PrintStatus
                        2. Predict
                        3. Play card
                        4. Continue
                        """);
                switch (s.nextLine()) {
                    case "0":
                        System.out.println("Exiting");
                        System.exit(0);
                    case "1":
                        _game.printStatus();
                        break;
                    case "2":
                        if (_game instanceof StatePredict) {
                            predict(s);
                        } else {
                            System.err.println("ERROR: Not in prediction state");
                        }
                        break;
                    case "3":
                        break;
                    case "4":
                        break;
                    default:
                        System.err.println("Choice not recognized");
                }
            } catch (InvalidPredictionException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
