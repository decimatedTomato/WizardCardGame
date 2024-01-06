package main.java.org.tomato.Wizard;

public class GUIGame implements CardGame {
    private WizardGame _game;
    
    public GUIGame(int playerCount) {
        _game = new WizardGame(playerCount);
    }

    @Override
    public void start() {
        System.out.println(_game);
    }
}
