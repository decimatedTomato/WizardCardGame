package main.java.org.tomato.Wizard;

public abstract class State {
    WizardGame game;

    public State(WizardGame wizardGame) {
        game = wizardGame;
    }

    public void printStatus() {
        System.out.println("It is turn " + game.getTurnCounter());
    }
    public abstract void onEnter();
    public abstract State transitionNext() throws TransitionCriteriaFailedException;
}
