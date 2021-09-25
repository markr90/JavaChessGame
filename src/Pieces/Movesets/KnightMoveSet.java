package Pieces.Movesets;

import Game.Board;
import Game.Coordinate;
import Game.Move;
import Game.Spot;

import java.util.ArrayList;

public class KnightMoveSet implements MoveSet {
    @Override
    public boolean isValidMove(Board board, Move move) {
        if (!isKnightMove(move)) {
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

    private boolean isKnightMove(Move move) {
        // can either be row +- 2 col +- 1 or row +- 1 col +- 2
        boolean isJumpType1 = Math.abs(move.to().row() - move.from().row()) == 2 && Math.abs(move.to().col() - move.from().col()) == 1;
        boolean isJumpType2 = Math.abs(move.to().row() - move.from().row()) == 1 && Math.abs(move.to().col() - move.from().col()) == 2;
        return isJumpType1 || isJumpType2;
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

}
