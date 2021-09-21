package Pieces;

import Game.Board;
import Game.Move;
import Pieces.Movesets.MoveSet;
import Pieces.Movesets.PawnForward;
import Pieces.Movesets.PawnCapture;

public class Pawn extends Piece {
    private static MoveSet[] moveSets = {new PawnForward(), new PawnCapture()};

    public Pawn(boolean isWhite) {
        super("P", isWhite);
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
}
