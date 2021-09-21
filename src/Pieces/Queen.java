package Pieces;

import Game.Board;
import Game.Move;
import Pieces.Movesets.Diagonal;
import Pieces.Movesets.MoveSet;
import Pieces.Movesets.Perpendicular;

public class Queen extends Piece {
    private static MoveSet[] moveSets = {new Perpendicular(), new Diagonal()};

    public Queen(boolean isWhite) {
        super("K", isWhite);
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
        return "queen_" + colorSubstring() + ".png";
    }
}
