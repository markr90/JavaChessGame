package Render;

import Game.Game;
import Game.Spot;

public final class Printer implements IDisplay {
    private static String[] columnIndices = new String[] {"A", "B", "C", "D", "E", "F", "G", "H"};
    private static String[] rowIndices = new String[] {"1", "2", "3", "4", "5", "6", "7", "8"};
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private Game game;

    public Printer(Game game) {
        this.game = game;
    }

    @Override
    public void Show() {
        Update();
    }

    @Override
    public void Update() {
        Spot[][] state = game.Board().getCurrentState();
        for (int i = 7; i >= 0; i--) {
            System.out.print(rowIndices[i]);
            printRow(state[i]);
        }
        printColumnIndices();
    }


    private static void printRow(Spot[] spots) {
        for (int j = 0; j < spots.length; j++) {
            String symbol = spots[j].hasPiece() ? spots[j].getPiece().Symbol() : ".";
            if (spots[j].hasPiece() && spots[j].getPiece().IsWhite()) {
                System.out.print(ANSI_YELLOW + " " + symbol + " " + ANSI_RESET);
            } else if (spots[j].hasPiece() && !spots[j].getPiece().IsWhite()) {
                System.out.print(ANSI_BLACK + " " + symbol + " " + ANSI_RESET);
            } else {
                System.out.print(" " + symbol + " ");
            }
        }
        System.out.print("\n");
    }

    private static void printColumnIndices() {
        System.out.print(" ");
        for (int j = 0; j < 8; j++) {
            System.out.print(" " + columnIndices[j] + " ");
        }
        System.out.print("\n");
    }
}
