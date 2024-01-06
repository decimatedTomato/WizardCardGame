package main.java.org.tomato.Wizard;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TUIGame implements CardGame {
    private State _game;

    public TUIGame(int playerCount) {
        _game = new StatePredict(new WizardGame(playerCount));
    }

    private void predict(Scanner s, StatePredict game) throws InvalidPredictionException {
        Iterator<Player> players = game.getPlayers();
        while (players.hasNext()) {
            Player activePlayer = players.next();
            System.out.println("Player " + activePlayer.id() + ", could you please enter your prediction, [q] to exit");
            while (true) {
                try {
                    String input = s.nextLine();
                    if (input.equals("q")) {
                        return;
                    }
                    int prediction = Integer.parseInt(input);
                    game.predict(activePlayer, prediction);
                } catch (NumberFormatException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    private void printPlayOptions(List<Card> hand) {
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ":\t" + hand.get(i));
        }
    }
    private void play(Scanner s, StateStrike game) {
        Iterator<Player> players = game.getPlayers();
        List<Card> activePlayerHand = null;
        while (players.hasNext()) {
            Player activePlayer = players.next();
            activePlayerHand = game.getHand(activePlayer);
            System.out.println("Player " + activePlayer.id() + ", Choose a card to play."
                + "\np:\tRedisplay hand"
                + "\nq:\tStop playing cards"
                + "\nYour hand contains the following:");
            printPlayOptions(activePlayerHand);
            while (true) {
                try {
                    String input = s.nextLine();
                    if (input.equals("q")) {
                        return;
                    } else if (input.equals("p")) {
                        printPlayOptions(activePlayerHand);
                    }
                    int prediction = Integer.parseInt(input);
                    game.playCard(activePlayer, activePlayerHand.get(prediction));
                } catch (NumberFormatException | InvalidPlayException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void start() {
        Scanner s = new Scanner(System.in);
        boolean gameHasEnded = false;
        while (!gameHasEnded) {
            try {
                System.out.println("""
                        Action to perform:
                        0.\tExit
                        1.\tPrint Status
                        2.\tPredict
                        3.\tPlay Card
                        4.\tContinue
                        """);
                switch (s.nextLine()) {
                    case "0":   // Exit
                        System.out.println("Exiting");
                        System.exit(0);
                    case "1":   // Print Status
                        _game.printStatus();
                        break;
                    case "2":   // Predict
                        if (_game instanceof StatePredict) {
                            predict(s, (StatePredict)_game);
                        } else {
                            System.err.println("ERROR: Not in prediction state");
                        }
                        break;
                    case "3":   // Play Card
                        if (_game instanceof StateStrike) {
                            play(s, (StateStrike)_game);
                        } else {
                            System.err.println("ERROR: Not in playing state");
                        }
                        break;
                    case "4":   // Continue
                        _game = _game.transitionNext();
                        if (_game == null) {
                            gameHasEnded = true;
                        }
                        break;
                    default:
                        System.err.println("Choice not recognized");
                }
            } catch (InvalidPredictionException | TransitionCriteriaFailedException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("The game has ended");
    }
}
