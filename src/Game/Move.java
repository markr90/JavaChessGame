package Game;

import Game.Undo.UndoAction;

import java.util.ArrayList;

public class Move {
    private Coordinate from;
    private Coordinate to;
    private boolean castlingMove = false;
    private boolean pawnPromotionMove = false;
    private ArrayList<UndoAction> undoActions = new ArrayList<>();

    public Move(String move) {
        char[] _move = move.toLowerCase().toCharArray();
        from = new Coordinate(ColToIndex(_move[0]), RowToIndex(_move[1]));
        to = new Coordinate(ColToIndex(_move[2]), RowToIndex(_move[3]));
    }

    public Move(Coordinate from, Coordinate to) {
        this.from = from;
        this.to = to;
    }

    public void resetUndoInstructions() {
        this.undoActions = new ArrayList<>();
    }

    public void addUndoInstruction(UndoAction undoAction) {
        this.undoActions.add(undoAction);
    }

    public void undo() {
        for (UndoAction action: undoActions) {
            action.execute();
        }
    }

    public Coordinate from() {
        return from;
    }

    public Coordinate to() {
        return to;
    }

    public void flagAsCastlingMove() {
        castlingMove = true;
    }

    public boolean isCastlingMove() {
        return castlingMove;
    }

    public void flagAsPawnPromotionMove() {
        pawnPromotionMove = true;
    }

    public boolean isPawnPromotionMove() {
        return pawnPromotionMove;
    }

    private int ColToIndex(char col) {
        int temp = (int)col;
        int temp_integer = 96;
        return temp - temp_integer - 1;
    }

    private int RowToIndex(char row) {
        int fromChar = row - '0' - 1;
        return 7 - fromChar;
    }
}
