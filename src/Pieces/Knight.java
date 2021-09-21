package Pieces;

import Game.Board;
import Game.Move;
import Pieces.Movesets.KnightMoveSet;
import Pieces.Movesets.MoveSet;

public class Knight extends Piece {
    private static MoveSet[] moveSets = {new KnightMoveSet()};

    public Knight(boolean isWhite) {
        super("N", isWhite);
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
        return "knight_" + colorSubstring() + ".png";
    }
}
