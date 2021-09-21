package Pieces.Movesets;

import Game.Board;
import Game.Coordinate;
import Game.Move;

public class PawnForward implements MoveSet {
    @Override
    public boolean isValidMove(Board board, Move move) {
        boolean isWhite = board.getSpot(move.from()).getPiece().isWhite();
        int maxDistance = isFirstMove(move, isWhite) ? 2 : 1;
        Coordinate t = move.to();
        Coordinate f = move.from();
        if (exceededDistance(move, maxDistance)) {
            return false;
        }

        if (f.col() != t.col()) {
            // can only move up or down direction;
            return false;
        }

        if (checkCollision(board, move)) {
            return false;
        }

        if (t.row() == 0 || t.row() == 7) {
            move.flagAsPawnPromotionMove();
        }

        if (isWhite) {
            return t.row() < f.row();
        } else {
            return t.row() > f.row();
        }
    }

    private boolean checkCollision(Board board, Move move) {
        if (board.getSpot(move.to()).hasPiece()) {
            // collision with target
            return true;
        }

        int rowMax = Math.max(move.to().row(), move.from().row());
        int rowMin = Math.min(move.to().row(), move.from().row());
        int col = move.from().col();
        for (int r = rowMin + 1; r < rowMax; r++) {
            if (board.getSpot(r, col).hasPiece()) {
                return true;
            }
        }

        return false;
    }

    private boolean isFirstMove(Move move, boolean isWhite) {
        if (isWhite) {
            return move.from().row() == 6;
        } else {
            return move.from().row() == 1;
        }
    }

    private boolean exceededDistance(Move move, int maxDistance) {
        return Math.abs(move.to().row() - move.from().row()) > maxDistance;
    }
}
