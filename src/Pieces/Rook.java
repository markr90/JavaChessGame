package Pieces;

import Game.Board;
import Game.Move;
import Pieces.Movesets.MoveSet;
import Pieces.Movesets.Perpendicular;

public class Rook extends Piece {
    private static MoveSet[] moveSets = {new Perpendicular()};

    public Rook(boolean isWhite) {
        super("R", isWhite);
    }

    @Override
    public boolean isMoveLegal(Board board, Move move) {
        for (MoveSet moveSet: moveSets) {
            if (moveSet.isValidMove(board, move)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String fileName() {
        return "rook_" + colorSubstring() + ".png";
    }
}
