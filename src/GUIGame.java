
public class GUIGame implements CardGame {
    private BaseWizardGame _game;
    
    public GUIGame(int playerCount) {
        _game = new BaseWizardGame(playerCount);
    }

    @Override
    public void start() {
        System.out.println(_game);
    }
}
