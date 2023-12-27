import java.util.Scanner;

public class TUIGame implements CardGame {
    private WizardGame _game;
    private final int _playerCount;
    
    public TUIGame(int playerCount) {
        _game = new BaseWizardGame(playerCount);
        _playerCount = playerCount;
    }

    @Override
    public void start() {
        final Scanner s = new Scanner(System.in);
        
        while (_game.getTurnCounter() < _game.getMaxTurnCount()) {
            System.out.println("START OF TURN " + _game.getTurnCounter());
            _game.shuffleDeck();
            _game.dealCards();
            Card trump = _game.drawTrump();
            printTrump(trump);
            printPlayerCards();
            _game.setRoundData(new RoundData(getPredictionInput(s), trump));
            for (int i = 0; i < _game.getTurnCounter(); i++) {
                _game.setChosenCards(getChosenCardInput(s, i));
                _game.evaluateStrike();
            }
            _game.evaluateRound();
            _game.incrementTurnCounter();
        }
        printWinner();
        s.close();
    }
    
    private void printWinner() {
    }
    
    private void printPlayerCards() {
    }
    
    private void printTrump(Card trump) {
    }

    private int[] getChosenCardInput(Scanner s, int strikeCount) {
        final int[] chosenCards = new int[_playerCount];
        for (int i = 0; i < chosenCards.length; i++) {
            final int playerHandSize = _game.getTurnCounter() - strikeCount;
            System.out.println("Player " + (i + 1) + " card to play (as position in hand)?");
            int position = 0;
            boolean validPosition = false;
            while (!validPosition) {
                try {
                    position = Integer.parseInt(s.nextLine());
                } catch (NumberFormatException e) {
                    System.err.println("That was not a number, try again");
                    continue;
                }
                if (position < 0) {
                    System.err.println("Position cannot be negative");
                    continue;
                } else if (position > playerHandSize-1) {
                    System.err.println("Positin cannot be larger than hand size (max " + (playerHandSize-1) + "): " + position);
                    continue;
                } else {
                    _game.validateCardChoice(i, position, chosenCards);
                }
                validPosition = true;
            }
            chosenCards[i] = position;
        }
        return chosenCards;
    }

    private int[] getPredictionInput(final Scanner s) {
        final int[] predictions = new int[_playerCount];
        for (int i = 0; i < predictions.length; i++) {
            System.out.println("Player " + (i + 1) + " how many strikes will you win this turn?");
            int prediction = 0;
            boolean validPrediction = false;
            while (!validPrediction) {
                try {
                    prediction = Integer.parseInt(s.nextLine());
                } catch (NumberFormatException e) {
                    System.err.println("That was not a number, try again");
                    continue;
                }
                if (prediction < 0) {
                    System.err.println("Too few strikes predicted (min " + 0 + "): " + prediction);
                    continue;
                } else if (prediction > _game.getTurnCounter()) {
                    System.err.println("Too many strikes predicted (max " + _game.getTurnCounter() + "): " + prediction);
                    continue;
                }
                validPrediction = true;
            }
            predictions[i] = prediction;
        }
        return predictions;
    }
}
