package Pieces;

import Game.Board;
import Game.Move;
import Pieces.Movesets.Diagonal;
import Pieces.Movesets.MoveSet;

public class Bishop extends Piece {
    private static MoveSet[] moveSets = {new Diagonal()};

    public Bishop(boolean isWhite) {
        super("B", isWhite);
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
        return "bishop_" + colorSubstring() + ".png";
    }
}
