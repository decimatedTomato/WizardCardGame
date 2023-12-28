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
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Action to perform:
                    0. Exit
                    1. PrintStatus
                    2. Begin prediction
                    """);
            switch (s.nextLine()) {
                case "0":
                    System.out.println("Exiting");
                    System.exit(0);
                case "1":
                case "2":

            }
        }
    }

}
