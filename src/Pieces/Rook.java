package Pieces;

import Game.Board;
import Game.Move;
import Game.Spot;

import java.io.IOException;

public class Rook extends Piece {
    public Rook(boolean isWhite) throws IOException {
        super("R", isWhite);
    }

    @Override
    public boolean IsMoveLegal(Board board, Move move) {
        if (!isMovePerpendicular(move)) {
            return false;
        }

        if (thereIsCollision(board, move)) {
            return false;
        }

        if (targetIsSameColor(board, move)) {
            return false;
        }

        return true;
    }

    private boolean moveIsHorizontal(Move move) {
        return (move.to().row() - move.from().row() == 0) && Math.abs(move.to().col() - move.from().col()) > 0;
    }

    private boolean moveIsVertical(Move move) {
        return (move.to().col() - move.from().col() == 0) && Math.abs(move.to().row() - move.from().row()) > 0;
    }

    private boolean isMovePerpendicular(Move move) {
        return moveIsHorizontal(move) || moveIsVertical(move);
    }

    private boolean thereIsCollision(Board board, Move move) {
        if (moveIsVertical(move)) {
            int rowMax = Math.max(move.from().row(), move.to().row());
            int rowMin = Math.min(move.from().row(), move.to().row());
            for (int row = rowMin + 1; row < rowMax; row++) {
                if (board.getSpot(row, move.from().col()).hasPiece()){
                    return true;
                }
            }
        }

        if (moveIsHorizontal(move)) {
            int colMax = Math.max(move.from().col(), move.to().col());
            int colMin = Math.min(move.from().col(), move.to().col());
            for (int col = colMin + 1; col < colMax; col++) {
                if (board.getSpot(move.from().row(), col).hasPiece()){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean targetIsSameColor(Board board, Move move) {
        Spot spot = board.getSpot(move.to().row(), move.to().col());
        if (!spot.hasPiece()) {
            return false;
        }

        if (spot.getPiece().IsWhite() == this.IsWhite()) {
            return true;
        }

        return false;
    }
}
