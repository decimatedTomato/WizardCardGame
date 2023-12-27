import java.util.Scanner;

public class TUIGame implements CardGame {
    private WizardGame _game;
    private final int _playerCount;

    public TUIGame(int playerCount) {
        _game = new WizardGame(playerCount);
        _playerCount = playerCount;
    }

    @Override
    public void start() {
    }

}
