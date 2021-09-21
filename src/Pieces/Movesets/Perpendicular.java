package Pieces.Movesets;

import Game.Board;
import Game.Move;
import Game.Spot;

public class Perpendicular implements MoveSet {
    private int maxDistance;

    public Perpendicular() {
        maxDistance = 100;
    }

    public Perpendicular(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean isValidMove(Board board, Move move) {
        if (exceededDistance(move, maxDistance)) {
            return false;
        }

        if (!isMovePerpendicular(move)) {
            return false;
        }

        if (thereIsCollision(board, move)) {
            return false;
        }

        if (targetIsSameColor(board, move)) {
            // todo castling?
            return false;
        }

        return true;
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

    private boolean moveIsHorizontal(Move move) {
        return move.to().row() == move.from().row();
    }

    private boolean moveIsVertical(Move move) {
        return move.to().col() == move.from().col();
    }

    private boolean isMovePerpendicular(Move move) {
        return moveIsHorizontal(move) || moveIsVertical(move);
    }

    private boolean targetIsSameColor(Board board, Move move) {
        Spot startSpot = board.getSpot(move.from());
        Spot targetSpot = board.getSpot(move.to());
        if (!targetSpot.hasPiece()) {
            return false;
        }

        if (targetSpot.getPiece().isWhite() == startSpot.getPiece().isWhite()) {
            return true;
        }

        return false;
    }

    private boolean exceededDistance(Move move, int maxDistance) {
        return Math.abs(move.to().row() - move.from().row()) > maxDistance
            || Math.abs(move.to().col() - move.from().col()) > maxDistance;
    }
}
