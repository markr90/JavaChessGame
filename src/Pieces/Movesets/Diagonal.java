package Pieces.Movesets;

import Game.Board;
import Game.Coordinate;
import Game.Move;
import Game.Spot;

import java.util.ArrayList;

public class Diagonal implements MoveSet {
    private int maxDistance;

    public Diagonal() {
        maxDistance = 100;
    }

    public Diagonal(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean isValidMove(Board board, Move move) {
        if (exceededDistance(move, maxDistance)) {
            return false;
        }

        if (!isMoveDiagonal(move)) {
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

    @Override
    public ArrayList<Move> generateAllValidMoves(Board board, Coordinate coord, boolean isWhite) {
        return new ArrayList<>();
    }

    private boolean thereIsCollision(Board board, Move move) {
        // move is already verified diagonal when using this method

        // y = x diagonal
        if (move.to().row() - move.from().row() == move.to().col() - move.from().col()) {
            int rowMin = Math.min(move.to().row(), move.from().row());
            int colMin = Math.min(move.to().col(), move.from().col());
            int iMax = Math.abs(move.to().row() - move.from().row());
            for (int i = 1; i < iMax; i++) {
                int row = rowMin + i;
                int col = colMin + i;
                if (board.getSpot(row, col).hasPiece()){
                    return true;
                }
            }
        } else {
            int rowMax = Math.max(move.to().row(), move.from().row());
            int colMin = Math.min(move.to().col(), move.from().col());
            int iMax = Math.abs(move.to().row() - move.from().row());
            for (int i = 1; i < iMax; i++) {
                int row = rowMax - i;
                int col = colMin + i;
                if (board.getSpot(row, col).hasPiece()){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isMoveDiagonal(Move move) {
        return Math.abs(move.to().col() - move.from().col()) == Math.abs(move.to().row() - move.from().row());
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
