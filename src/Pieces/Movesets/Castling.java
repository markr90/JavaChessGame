package Pieces.Movesets;

import Game.Board;
import Game.Move;
import Pieces.IPiece;

public class Castling implements MoveSet {

    @Override
    public boolean isValidMove(Board board, Move move) {
        IPiece sourcePiece = board.getSpot(move.from()).getPiece();

        IPiece targetPiece = board.getSpot(move.to()).getPiece();

        if (targetPiece == null) {
            return false;
        }

        if (!involvesAKingAndRook(sourcePiece, targetPiece)) {
            return false;
        }

        // can only castle on same rank
        if (move.to().row() != move.from().row()) {
            return false;
        }

        if (sourcePiece.isWhite() != targetPiece.isWhite()) {
            return false;
        }

        if (sourcePiece.numberOfTimesMoved() != 0 || targetPiece.numberOfTimesMoved() != 0) {
            return false;
        }

        // is king in check?

        if (collisionDetected(board, move)) {
            return false;
        }

        // passes through a check?


        move.flagAsCastlingMove();
        return true;
    }

    private boolean involvesAKingAndRook(IPiece sourcePiece, IPiece targetPiece) {
        return sourcePiece.symbol() == "R" && targetPiece.symbol() == "K"
                || sourcePiece.symbol() == "K" && targetPiece.symbol() == "R";
    }

    private boolean collisionDetected(Board board, Move move) {
        int colMin = Math.min(move.to().col(), move.from().col());
        int colMax = Math.max(move.to().col(), move.from().col());

        for (int col = colMin + 1; col < colMax; col++) {
            if (board.getSpot(move.from().row(), col).hasPiece()) {
                return true;
            }
        }

        return false;
    }
}
