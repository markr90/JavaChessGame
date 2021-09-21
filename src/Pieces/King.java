package Pieces;

import Game.Board;
import Game.Move;
import Pieces.Movesets.Castling;
import Pieces.Movesets.Diagonal;
import Pieces.Movesets.MoveSet;
import Pieces.Movesets.Perpendicular;

public class King extends Piece {
    private static MoveSet[] moveSets = {new Perpendicular(1), new Diagonal(1), new Castling()};

    public King(boolean isWhite) {
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
        return "king_" + colorSubstring() + ".png";
    }
}
