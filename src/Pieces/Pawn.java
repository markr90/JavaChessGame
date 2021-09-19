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
            (move.to().row() - move.from().row() == -1)
            && (move.to().col() - move.from().col() == 0);
    }

    private boolean isOneStepDiagonalForward(Move move) {
        return
                (move.to().row() - move.from().row() == -1)
                        && (Math.abs(move.to().col() - move.from().col()) == 1);
    }

    private boolean isOneStepBackward(Move move) {
        return
                (move.to().row() - move.from().row() == 1)
                        && (move.to().col() - move.from().col() == 0);
    }

    private boolean isOneStepDiagonalBackward(Move move) {
        return
                (move.to().row() - move.from().row() == 1)
                        && (Math.abs(move.to().col() - move.from().col()) == 1);
    }
}
