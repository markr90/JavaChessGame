package Pieces;

import Game.Board;
import Game.Move;
import java.io.IOException;

public class Pawn extends Piece {
    public Pawn(boolean isWhite) throws IOException {
        super("P", isWhite);
    }

    @Override
    public boolean IsMoveLegal(Board board, Move move) {
        if (IsWhite()) {
            // 1 step forward
            if (isOneStepForward(move)) {
                // if occupied return false
                return !board.getSpot(move.to()).hasPiece();
            } else if (isOneStepDiagonalForward(move)) {
                return board.getSpot(move.to()).hasPiece() && !board.getSpot(move.to()).getPiece().IsWhite();
            } else {
                return false;
            }
        }
        else {
            // 1 step backward
            if (isOneStepBackward(move)) {
                // if occupied return false
                return !board.getSpot(move.to()).hasPiece();
            } else if (isOneStepDiagonalBackward(move)) {
                return board.getSpot(move.to()).hasPiece() && board.getSpot(move.to()).getPiece().IsWhite();
            } else {
                return false;
            }
        }
    }

    private boolean isOneStepForward(Move move) {
        return
            (move.to().Y() - move.from().Y() == -1)
            && (move.to().X() - move.from().X() == 0);
    }

    private boolean isOneStepDiagonalForward(Move move) {
        return
                (move.to().Y() - move.from().Y() == -1)
                        && (Math.abs(move.to().X() - move.from().X()) == 1);
    }

    private boolean isOneStepBackward(Move move) {
        return
                (move.to().Y() - move.from().Y() == 1)
                        && (move.to().X() - move.from().X() == 0);
    }

    private boolean isOneStepDiagonalBackward(Move move) {
        return
                (move.to().Y() - move.from().Y() == 1)
                        && (Math.abs(move.to().X() - move.from().X()) == 1);
    }
}
