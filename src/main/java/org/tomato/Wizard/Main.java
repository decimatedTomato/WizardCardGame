package main.java.org.tomato.Wizard;

import org.apache.commons.cli.*;

public class Main {
    final static String USAGE = """
            WizardGame -p[PLAYERS] [-t/g]
            """;

    public static void main(String args[]) throws IllegalArgumentException {
        Option players = new Option("p", "players", true, "number of players");
        players.setRequired(true);
        Option tuiMode = new Option("t", "TUIMode", false, "The interface is textual");
        Option guiMode = new Option("g", "GUIMode", false, "The interface is visual");
        Options options = new Options();
        options.addOption(players);
        options.addOption(tuiMode);
        options.addOption(guiMode);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp(USAGE, options);
            System.exit(1);
        }

        int playerCount = 0;
        try {
            playerCount = Integer.parseInt(cmd.getOptionValue("players"));
        } catch (NumberFormatException e) {
            System.err.println("Invalid player count: " + cmd.getOptionValue("players"));
            System.exit(-1);
        }
        if (playerCount < 3) {
            System.err.println("Too few players (min 3): " + playerCount);
            System.exit(-1);
        } else if (playerCount > 6) {
            System.err.println("Too many players (max 6):" + playerCount);
            System.exit(-1);
        }
        boolean guimode = cmd.hasOption("GUIMode");

        CardGame game = null;
        if (guimode) {
            game = new GUIGame(playerCount);
        } else {
            game = new TUIGame(playerCount);
        }
        game.start();
    }
}
